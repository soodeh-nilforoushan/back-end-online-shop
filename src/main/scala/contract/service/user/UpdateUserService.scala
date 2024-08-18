package contract.service.user

import contract.Service
import domain.store.Item
import domain.user.User


abstract class UpdateUserService extends Service[UpdateUserService.Request, Item]

object UpdateUserService{
  case class Request(id: Long, password: String, email: String)
}
