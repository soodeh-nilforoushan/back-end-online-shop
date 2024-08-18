package usecases.user

import com.google.inject.{Inject, Singleton}
import contract.callback.user.SessionCallback
import contract.service.user.AuthorizeService
import modules.ConfigModule
import util.AuthUtils

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class AuthorizeUseCase @Inject()(sessionCallback: SessionCallback) extends AuthorizeService {

  override def call(request: AuthorizeService.Request)(implicit ec: ExecutionContext): Future[Unit] = for {
    // Checking invalid key
    _ <- if (AuthUtils.sessionKey(request.session.userID, request.session.username) != request.session.key) {
      Future failed new Exception("User has not logged in!")
    } else {
      Future.unit
    }

    // Checking logged out session
    sessionOption <- sessionCallback get request.session.key
    _ <- sessionOption match {
      case Some(_) => Future.unit
      case None => Future failed new Exception("User has not logged in!") // Show the same error to hide the auth procedure
    }
  } yield ()

}

