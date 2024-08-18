package contract.service.user

import contract.Service
import domain.store.Item
import domain.user.User

abstract class GetUserService extends Service[GetUserService.Request, User]

object GetUserService{
  case class Request(id: Long, username: String, password: String, email: String ,role: User.Role)
}
