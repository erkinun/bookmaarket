package models.sql

import models.Bookmark
import slick.lifted.{Tag, Rep}
import slick.driver.H2Driver.api._

/**
  * Created by eunlu on 11/01/2017.
  */
class Bookmarks(tag: Tag) extends Table[Bookmark](tag, "BOOKMARKS") {
  def id: Rep[Long] = column[Long]("ID", O.PrimaryKey, O.AutoInc)
  def url: Rep[String] = column[String]("URL")
  def description: Rep[String] = column[String]("DESCRIPTION")
  def folderName: Rep[String] = column[String]("FOLDER_NAME")

  def * = (url, description, folderName, id) <> (Bookmark.tupled, Bookmark.unapply)
}
