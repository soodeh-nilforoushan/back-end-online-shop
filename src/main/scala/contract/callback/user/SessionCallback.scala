package contract.callback.user

import domain.user.Session
import scala.concurrent.Future

abstract class SessionCallback {

  def add(key: String, userID: Long, username: String): Future[Session]

  def get(key:String, userID: Long): Future[Option[Session]]

  def remove(key: String): Future[Unit]

}
