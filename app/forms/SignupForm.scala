package forms

import play.api.data.Form
import play.api.data.Forms._


object SignupForm {
  val form = Form (
    mapping(
      "userName" -> nonEmptyText,
      "firstName" -> optional(text),
      "lastName" -> optional(text),
      "email" -> email,
      "password" -> nonEmptyText(minLength = 8)
    )(SignupData.apply)(SignupData.unapply)
  )
}

case class SignupData(userName: String, firstName: Option[String], lastName: Option[String], email: String, password: String)
