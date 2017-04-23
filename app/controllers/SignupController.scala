package controllers

import javax.inject.{Inject, Singleton}

import forms.SignupForm
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{Action, Controller}


@Singleton
class SignupController @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {


  def createUser() = Action { implicit request =>
    SignupForm.form.bindFromRequest.fold(
      formWithErrors => BadRequest,
      user => NotImplemented
    )
  }

  def getRegisterPage() = Action { implicit request =>
    Ok(views.html.users.register(SignupForm.form))
  }
}
