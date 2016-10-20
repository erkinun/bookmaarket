package controllers

import javax.inject.{Inject, Singleton}

import models.Bookmark
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{Action, Controller}

/**
  * Created by eunlu on 19/10/2016.
  */
@Singleton
class BookmarkController @Inject() (val messagesApi: MessagesApi) extends Controller
  with I18nSupport  {
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
}
