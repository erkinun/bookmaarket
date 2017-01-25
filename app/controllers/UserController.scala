package controllers

import javax.inject.{Inject, Singleton}

import models.User
import play.api.data.Form
import play.api.data.Forms.nonEmptyText
import play.api.data.Forms.optional
import play.api.data.Forms.text
import play.api.data.Forms.mapping
import play.api.data.Forms.email
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{Action, Controller}

/**
  * Created by eunlu on 22/01/2017.
  */
@Singleton
class UserController @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {

  val userForm = Form {
    mapping(
      "username" -> nonEmptyText(minLength = 8),
      "realname" -> optional(text),
      "email" -> email,
      "password" -> nonEmptyText(minLength = 8)
    ) (User.apply) (User.unapply)
  }

  def createUser() = Action { implicit request =>
    userForm.bindFromRequest.fold(
      formWithErrors => BadRequest,
      user => NotImplemented
    )
  }

  def getRegisterPage() = Action { implicit request =>
    Ok(views.html.users.register(userForm))
  }
}
