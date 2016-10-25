package controllers

import javax.inject.{Inject, Singleton}

import models.Bookmark
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.{Messages, I18nSupport, MessagesApi}
import play.api.mvc.{Action, Controller, Flash}

/**
  * Created by eunlu on 19/10/2016.
  */

// TODO add appropriate links to details page
@Singleton
class BookmarkController @Inject() (val messagesApi: MessagesApi) extends Controller
  with I18nSupport  {

  // TODO put some validation on this form
  private val bookmarkForm: Form[Bookmark] = Form {
    mapping(
      "url" -> nonEmptyText,
      "description" -> nonEmptyText,
      "folderName" -> nonEmptyText
    ) (Bookmark.unsaved)(Bookmark.unapplyShort)
  }

  def list = Action { implicit request =>
    val bookmarks = Bookmark.findAll

    println(s"bookmark count for now: ${bookmarks.size}")

    Ok(views.html.bookmarks.list(bookmarks))
  }

  def show(id: Long) = Action { implicit request =>
    Bookmark.findById(id).map { bookmark =>
      Ok(views.html.bookmarks.details(bookmark))
    }.getOrElse(NotFound)
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
        Bookmark.add(newBookmark)
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
