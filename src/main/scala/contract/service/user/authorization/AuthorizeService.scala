package contract.service.user

import contract.Service
import domain.user.Session

abstract class AuthorizeService extends Service[AuthorizeService.Request, Unit]

object AuthorizeService {

  case class Request(session: Session)

}
