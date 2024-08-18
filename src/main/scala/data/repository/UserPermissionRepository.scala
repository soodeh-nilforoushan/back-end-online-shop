package data.repository

import contract.callback.user.UserPermissionCallback
import domain.user.UserPermission
import modules.DatabaseModule

import scala.concurrent.Future

class UserPermissionRepository extends UserPermissionCallback with DatabaseModule{

  override def get(userID: Long, permission: Long): Future[Option[UserPermission]] = ???

  override def addBatch(userID: Long, permissionIDs: Vector[Long]): Future[Int] = ???

  override def add(userID: Long): Future[Int] = ???
}


