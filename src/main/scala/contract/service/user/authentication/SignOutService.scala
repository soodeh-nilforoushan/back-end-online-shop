package contract.service.user

import contract.Service

abstract class SignOutService extends Service[SignOutService.Request, Unit]

object SignOutService {

  case class Request(key: String)

}
