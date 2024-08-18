package usecases.user

import com.google.inject.{Inject, Singleton}
import contract.callback.user.{SessionCallback, UserCallback}
import contract.service.user.SignInService
import domain.user.Session
import util.AuthUtils

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

import scala.collection.concurrent.TrieMap

@Singleton
class SignInUseCase @Inject()(sessionCallback: SessionCallback, userCallback: UserCallback) extends SignInService {

  // A concurrent map to store user sessions. The key can be a session key or user ID.
  private val sessionMap: TrieMap[String, Session] = TrieMap.empty

  override def call(request: SignInService.Request)(implicit ec: ExecutionContext): Future[Session] = for {

    userOption <- userCallback getBy request.username
    user <- userOption match {
      case Some(user) => Future successful user
      case None => Future failed new Exception(s"Username or password is not correct!")
    } // Hereafter, we will use user.username instead of request.username.
    hashedPassword = AuthUtils hashPassword request.password
    session <- if (hashedPassword == user.password) {
      val key = AuthUtils.sessionKey(user.id, user.username)
      sessionCallback.add(key, user.id, user.username).map { session =>
        sessionMap.put(key, session) // Store the session in the map
        session
      }
    } else {
      Future failed new Exception(s"Username or password is not correct!") // Show the same error to hide the auth procedure and existence of username
    }
  } yield session

//  // Method to retrieve a session by its key
//  def getSession(key: String): Option[Session] = sessionMap.get(key)
//
//  // Method to remove a session by its key
//  def removeSession(key: String): Option[Session] = sessionMap.remove(key)
}

