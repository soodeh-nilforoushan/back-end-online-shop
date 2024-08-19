package data.repository
import data.Utils.SQLContext
import contract.callback.user.UserPermissionCallback
import data.adapter.{UserFactory, UserPermissionFactory}
import domain.user.UserPermission
import modules.DatabaseModule
import modules.DatabaseModule.onlineShop

import scalikejdbc.{NamedDB, scalikejdbcSQLInterpolationImplicitDef}

import scala.concurrent.Future

class UserPermissionRepository extends UserPermissionCallback with DatabaseModule {

  override def get(userID: Long, permissionID: Long): Future[Option[UserPermission]] = Future {
    NamedDB(onlineShop) readOnly { implicit session =>
      sql"""
        SELECT *
        FROM user_permission
        WHERE user_id = $userID
      """.map(UserPermissionFactory.userPermission).single()
    }
  }

  override def addBatch(userID: Long, permissionIDs: Vector[Long]): Future[Int] = {
    if (permissionIDs.isEmpty) Future.successful(0) else Future {
      val permissionIDsQL = ql"$permissionIDs"
      NamedDB(onlineShop) localTx {
        implicit session =>
          sql"""
               INSERT INTO user_permission(user_id, permission)
               (
               SELECT permission, $userID
               FROM UNNEST($permissionIDsQL) AS data(permission)
               )
             """.update()
      }
    }
  }


}
