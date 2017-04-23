package models

case class User(userName: String, realName: Option[String], email: String, password: String)
