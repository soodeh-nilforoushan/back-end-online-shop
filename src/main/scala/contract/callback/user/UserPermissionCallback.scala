package contract.callback.user

import domain.user. UserPermission

import scala.concurrent.Future


abstract class UserPermissionCallback {



  def get(userID: Long, permission: Long): Future[Option[UserPermission]]

  def add( userID: Long
            ): Future[Int]

  def addBatch(userID: Long, permissionIDs: Vector[Long]): Future[Int]


}
