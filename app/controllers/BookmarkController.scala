package controllers

import javax.inject.{Inject, Singleton}

import models.{BookmarkRepo, Bookmark}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.{Messages, I18nSupport, MessagesApi}
import play.api.mvc.{Action, Controller, Flash}


// TODO add appropriate links to details page
// TODO show more respect to UI
// TODO add other CRUD operations
// TODO persist data content of h2
// TODO show per user or per id their bookmarks
// TODO add authentication
// TODO private bookmarks?
// TODO show per folder
// TODO add import support from bookmark htmls

@Singleton
class BookmarkController @Inject() (val messagesApi: MessagesApi, bookmarkRepo: BookmarkRepo) extends Controller
  with I18nSupport  {

  // TODO put some validation on this form
  private val bookmarkForm: Form[Bookmark] = Form {
    mapping(
      "url" -> nonEmptyText,
      "description" -> nonEmptyText,
      "folderName" -> nonEmptyText
    ) (bookmarkRepo.unsaved)(bookmarkRepo.unapplyShort)
  }

  def list = Action.async { implicit request =>
    val bookmarks = bookmarkRepo.selectAll

    bookmarks.map(allBookmarks => Ok(views.html.bookmarks.list(allBookmarks)))
  }

  def show(id: Long) = Action.async { implicit request =>

    for {
      Some(bookmark) <- bookmarkRepo.findById(id)
    } yield Ok(views.html.bookmarks.details(bookmark))
    //}.getOrElse(NotFound)
  }

  def save = Action { implicit request =>
    val bmForm = bookmarkForm.bindFromRequest()

    bmForm.fold(
      hasErrors = { form =>
        Redirect(routes.BookmarkController.newBookmark()).
          flashing(Flash(form.data) +
            ("error" -> Messages("validation.errors")))
      },

      success = { newBookmark =>
        bookmarkRepo.add(newBookmark)
        val message = Messages("bookmark.new.success", newBookmark.url)
        Redirect(routes.BookmarkController.list()).flashing("success" -> message)
      }
    )
  }

  def newBookmark = Action { implicit request =>
    val form =
      if (request.flash.get("error").isDefined)
        bookmarkForm.bind(request.flash.data)
      else bookmarkForm

    Ok(views.html.bookmarks.add(form))
  }

}
