package models

import javax.inject.Inject

import models.sql.Bookmarks
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import slick.lifted.TableQuery
import slick.driver.H2Driver.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by eunlu on 18/10/2016.
  */
case class Bookmark(url: String, description: String, folderName: String, id: Long)

class BookmarkRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) {

  val dbConfig = dbConfigProvider.get[JdbcProfile]
  val db = dbConfig.db
  //val db = Database.forConfig("h2mem1")
  private val bookmarks: TableQuery[Bookmarks] = TableQuery[Bookmarks]

  // TODO this has to be a data repo
  /*var bookmarks = Set (
    Bookmark("http://ggia.berkeley.edu/practice/meaningful_pictures", "Greater Good link", "GGIA", 1),
    Bookmark("http://ggia.berkeley.edu/practice/finding_silver_linings", "Greater Good link", "GGIA",  2),
    Bookmark("http://www.ybrikman.com/writing/2014/03/10/the-ultimate-guide-to-getting-started/", "Scala", "Scala", 3),
    Bookmark("http://stackoverflow.com/questions/tagged/playframework", "Scala", "Scala", 4)
  )*/

  def selectAll: Future[List[Bookmark]] = db.run(bookmarks.to[List].result)

  def findById(id: Long): Future[Option[Bookmark]] = db.run {
    bookmarks.filter(_.id === id).result.headOption
  }

  // means it is unsaved yet, TODO fix later with Option type
  def unsaved(url: String, description: String, folder: String) = Bookmark(url, description, folder, 0)

  def unapplyShort(bookmark: Bookmark) =
    Option((bookmark.url, bookmark.description, bookmark.folderName))

  def add(bookmark: Bookmark): Future[Long] = db.run {
    bookmarks returning bookmarks.map(_.id) += bookmark
  }
}