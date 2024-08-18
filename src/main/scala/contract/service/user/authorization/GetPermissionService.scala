package contract.service.user

import contract.Service
import domain.user.UserPermission
import domain.store.Item
import domain.user.User

abstract class GetPermissionService extends Service [GetPermissionService.Request, UserPermission]

object GetPermissionService{
  case class Request(UserID:Long)
}
