package usecases.user

import com.google.inject.{Inject, Singleton}
import contract.callback.user.{UserCallback, UserPermissionCallback}
import contract.service.user.SignUpService
import domain.user.{User, UserPermission}
import util.AuthUtils

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class SignUpUseCase @Inject()(userCallback: UserCallback, userPermissionCallback: UserPermissionCallback) extends SignUpService {

  protected override def call(request: SignUpService.Request)(implicit ec: ExecutionContext): Future[User] = {
    // Persist hashed password
    val hashedPassword = AuthUtils hashPassword request.password

    for {
      // Check username availability
      previousUserOption <- userCallback getBy request.username
      _ <- previousUserOption match {
        case None => Future.unit
        case Some(previousUser) => Future failed new Exception(s"This username is taken: ${previousUser.username}")
      }

      // Create user
      userID <- userCallback.addUser(request.username, hashedPassword, request.name, request.eMail, request.role)
      _ <- request.role match {
        case User.Role.SELLER => userPermissionCallback.addBatch(userID, Vector(UserPermission.Permission.ADD_ITEM,
          UserPermission.Permission.REMOVE_ITEM, UserPermission.Permission.UPDATE_ITEM, UserPermission.Permission.GET_ITEM))
        case User.Role.CUSTOMER => userPermissionCallback.addBatch(userID, Vector(UserPermission.Permission.ADD_To_BASKET,
          UserPermission.Permission.REMOVE_FROM_BASKET, UserPermission.Permission.UPDATE_BASKET, UserPermission.Permission.GET_ALL_BASKET))
      }

        createdUser <- userCallback.get(userID)
    } yield createdUser getOrElse (throw ClassNotFoundException)
  }


}