package controllers

import javax.inject.{Inject, Singleton}

import com.mohiva.play.silhouette.api.Silhouette
import forms.SignupForm
import play.api.i18n.{I18nSupport, Messages, MessagesApi}
import play.api.mvc.{Action, Controller, Flash}
import utils.auth.DefaultEnv


@Singleton
class SignupController @Inject()
(
  val messagesApi: MessagesApi,
  silhouette: Silhouette[DefaultEnv]
) extends Controller with I18nSupport {

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

  /**
    * views the register page
    * @return ok status with signup form
    */
  def registerPage = silhouette.UnsecuredAction { implicit request =>
    Ok(views.html.users.register(SignupForm.form))
  }
}