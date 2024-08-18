package entry.rest.finatra.filters

import com.google.inject.Inject
import com.twitter.finagle.{Service, SimpleFilter}
import com.twitter.finagle.http.{Request, Response}
import com.twitter.util.Future
import contract.service.user.AuthorizeService
import domain.user.Session

class AuthorizationFilter @Inject()(authorizeService: AuthorizeService) extends SimpleFilter[Request, Response] {

  override def apply(request: Request, service: Service[Request, Response]): Future[Response] = {
    (request.cookies get "key", request.cookies get "userID", request.cookies get "username") match {
      case (Some(key), Some(userID), Some(username)) =>
        for {
          _ <- authorizeService.call(AuthorizeService.Request(Session(key.value, userID.value.toLong, username.value))).toTwitter()
          response <- service(request)
        } yield response
      case _ => Future.exception(new Exception("Login is required!"))
    }
  }

}

