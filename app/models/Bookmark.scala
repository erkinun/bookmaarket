package models

/**
  * Created by eunlu on 18/10/2016.
  */
case class Bookmark(url: String, description: String, folderName: String, id: Long)

object Bookmark {
  val bookmarks = Set (
    Bookmark("http://ggia.berkeley.edu/practice/meaningful_pictures", "Greater Good link", "GGIA", 1),
    Bookmark("http://ggia.berkeley.edu/practice/finding_silver_linings", "Greater Good link", "GGIA",  2),
    Bookmark("http://www.ybrikman.com/writing/2014/03/10/the-ultimate-guide-to-getting-started/", "Scala", "Scala", 3),
    Bookmark("http://stackoverflow.com/questions/tagged/playframework", "Scala", "Scala", 4)
  )

  def findAll = bookmarks.toList

  def findById(id: Long): Option[Bookmark] = bookmarks find { bm => bm.id == id}
}