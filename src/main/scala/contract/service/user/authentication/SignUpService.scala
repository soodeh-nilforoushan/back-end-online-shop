package contract.service.user

import contract.Service
import domain.user.User

abstract class SignUpService extends Service[SignUpService.Request, User]

object SignUpService {

  case class Request(username: String, password: String, name: String, eMail: String, role: String)

}
