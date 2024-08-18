package usecases.user

import com.google.inject.{Inject, Singleton}
import contract.callback.user.SessionCallback
import contract.service.user.SignOutService
import data.repository.UserRepository

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class SignOutUseCase @Inject()(sessionCallback: SessionCallback) extends SignOutService {

  override def call(request: SignOutService.Request)(implicit ec: ExecutionContext): Future[Unit] = {
    sessionCallback remove request.key
  }

}
