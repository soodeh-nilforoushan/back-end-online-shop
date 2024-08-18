package contract.service.user.authorization

import contract.Service
import contract.service.user.GetPermissionService
import domain.entities.Permission

abstract class GetUserPermissiomService Service [GetPermissionService.Request, Permission]

object GetUserPermissiomService{
  case class Request(UserID:Long)
}