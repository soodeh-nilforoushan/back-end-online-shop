package contract.callback.user

import domain.user.User
import scala.concurrent.Future

abstract class UserCallback {

  def addUser(userName: String, password:String, name: String, email: String, role: User.Role): Future[Long]

  def get(id: Long): Future[Option[User]]

  def update(
              userID: Long,
              userName: Option[String] = None,
              password: Option[String] = None,
              email: Option[String] = None
            ): Future[Unit]

  def remove(id: Long): Future[Unit]

  def getBy(username: String): Future[Option[User]]
}
