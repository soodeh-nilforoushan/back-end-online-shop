package contract.service.user

import contract.Service
import domain.user.Session

abstract class SignInService extends Service[SignInService.Request, Session]

object SignInService {

  case class Request(username: String, password: String)

}
