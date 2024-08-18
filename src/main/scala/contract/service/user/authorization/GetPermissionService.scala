package contract.service.user

import contract.Service
import domain.entities.Permission
import domain.store.Item
import domain.user.User

abstract class GetPermissionService extends Service [GetPermissionService.Request, Permission]

object GetPermissionService{
  case class Request(UserID:Long)
}
