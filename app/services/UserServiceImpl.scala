package services
import java.util.UUID

import com.mohiva.play.silhouette.api.LoginInfo
import models.User

import scala.concurrent.Future

class UserServiceImpl extends UserService {
  override def retrieve(id: UUID): Future[Option[User]] = ???

  override def save(user: User): Future[User] = ???

  override def retrieve(loginInfo: LoginInfo): Future[Option[User]] = ???
}
