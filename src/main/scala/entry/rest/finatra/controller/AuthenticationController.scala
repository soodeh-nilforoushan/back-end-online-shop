package entry.rest.finatra.controller

import com.google.inject.Inject
import com.twitter.finagle.http.Cookie
import com.twitter.finatra.http.Controller
import contract.service.user.SignOutService
import entry.rest.finatra.RequestWrapper
import entry.rest.finatra.adapter.user.UserFactory
import entry.rest.finatra.adapter.user.api.SignOutRequestDTO
import com.twitter.util.Duration
import scala.concurrent.ExecutionContext.Implicits._

class AuthenticationController @Inject()(signOutService: SignOutService) extends Controller {

  prefix("/api/v1") {

    delete("/sessions/current", "Sign Out") { requestWrapper: RequestWrapper =>
      val requestDTO = requestWrapper.getRequestDTO[SignOutRequestDTO]
      signOutService call UserFactory.signOuRequest(requestWrapper, requestDTO) map { _ =>
        response.noContent
          .cookie(new Cookie("key", "", path = Some("/"), maxAge = Some(Duration fromSeconds 0)))
          .cookie(new Cookie("userID", "", path = Some("/"), maxAge = Some(Duration fromSeconds 0)))
          .cookie(new Cookie("username", "", path = Some("/"), maxAge = Some(Duration fromSeconds 0)))
      }
    }

  }

}
