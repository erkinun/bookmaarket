package models

import java.util.UUID

import com.mohiva.play.silhouette.api.{Identity, LoginInfo}

case class User(
               userId: UUID,
               loginInfo: LoginInfo,
               activated: Boolean,
               userName: String,
               firstName: Option[String],
               lastName: Option[String],
               email: String,
               password: String) extends Identity
