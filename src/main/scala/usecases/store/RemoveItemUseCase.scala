package usecases.store

import com.google.inject.{Inject, Singleton}
import contract.callback.store.ItemCallback
import contract.callback.user.{SessionCallback, UserCallback, UserPermissionCallback}
import contract.service.store.{AddItemService, RemoveItemService}
import domain.user.UserPermission
import domain.store.Item

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class RemoveItemUseCase @Inject()(itemCallback: ItemCallback,
                                  userCallback: UserCallback,
                                  userPermissionCallback: UserPermissionCallback,
                                  sessionCallback: SessionCallback)
  extends RemoveItemService {

  override def call(request: RemoveItemService.Request)(implicit ec: ExecutionContext): Future[Unit] = for {
    // Step 1: Check if the user is logged in and has a valid session



    // Step 2: Check if the user has the permission to remove an item
    hasPermission <- userPermissionCallback.get(request.userID, UserPermission.Permission.REMOVE_ITEM)
    _ = hasPermission getOrElse {
      Future.failed(new Exception("User does not have permission to remove items"))
    }

    // Step 3: Check if the item exists in the database

    itemExists <- itemCallback.getByName(request.itemName)
    _ = itemExists getOrElse {
      Future.failed(new Exception(s"Item with ID '${request.itemName}' does not exist"))
    }

    // Step 4: Remove the item from the database
    removed <- itemCallback.removeByName(request.itemName)
    _ =  removed getOrElse {
      Future.failed(new Exception(s"Failed to remove the item with name ${request.itemName}"))
    }
  } yield ()
}
