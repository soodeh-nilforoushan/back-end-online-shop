package entry.rest.finatra.controller.unsafe


import com.google.inject.Inject
import com.twitter.finagle.http.Cookie
import com.twitter.finatra.http.Controller
import contract.service.user.{SignInService, SignUpService}
import entry.rest.finatra.UnauthenticatedRequestWrapper
import entry.rest.finatra.adapter.user.{UserDTOFactory, UserFactory}
import entry.rest.finatra.adapter.user.api.{SignInRequestDTO, SignUpRequestDTO}

import scala.concurrent.ExecutionContext.Implicits._


class AuthenticationController @Inject()(signInService: SignInService, signUpService: SignUpService) extends Controller {

  prefix("/api/v1") {

    post("/users", "Sign Up") { requestWrapper: UnauthenticatedRequestWrapper =>
      val requestDTO = requestWrapper.getRequestDTO[SignUpRequestDTO]
      signUpService call UserFactory.signUpRequest(requestWrapper, requestDTO) map UserDTOFactory.user map { serviceResponseDTO =>
        response created serviceResponseDTO
      }
    }

    post("/users/:username/sessions", "Sign In") { requestWrapper: UnauthenticatedRequestWrapper =>
      val requestDTO = requestWrapper.getRequestDTO[SignInRequestDTO]
      signInService call UserFactory.signInRequest(requestWrapper, requestDTO) map { serviceResponse =>
        response.created()
          .cookie(new Cookie("key", serviceResponse.key, path = Some("/")))
          .cookie(new Cookie("userID", serviceResponse.userID.toString, path = Some("/")))
          .cookie(new Cookie("username", serviceResponse.username, path = Some("/")))
      }
    }

  }

}
