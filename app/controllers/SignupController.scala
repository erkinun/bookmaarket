package controllers

import javax.inject.{Inject, Singleton}

import forms.SignupForm
import play.api.i18n.{I18nSupport, Messages, MessagesApi}
import play.api.mvc.{Action, Controller, Flash}


@Singleton
class SignupController @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {

  def createUser = Action { implicit request =>
    println("trying to create!")
    SignupForm.form.bindFromRequest.fold(
      hasErrors = { form =>
        Redirect(routes.SignupController.registerPage())
          .flashing(Flash(form.data) + ("error" -> Messages("validation.errors")))
      },
      success = { signupData =>
        println(s"creating a new user $signupData")
        Redirect(routes.BookmarkController.list())
      }
    )
  }

  def registerPage = Action { implicit request =>
    println("getting the register page")
    Ok(views.html.users.register(SignupForm.form))
  }
}