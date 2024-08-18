package usecases.store

import com.google.inject.{Inject, Singleton}
import contract.callback.store.ItemCallback
import contract.callback.user.{SessionCallback, UserCallback}
import contract.service.store.AddItemService
import domain.store.Item

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class AddItemUseCase @Inject()(itemCallback: ItemCallback, userCallback: UserCallback, permissionCallback: PermissionCallback, sessionCallback: SessionCallback) extends AddItemService {


  override def call(request: AddItemService.Request)(implicit ec: ExecutionContext): Future[Item] = for {
    // Step 1: Check if the user is logged in and has a valid session
    session <- sessionCallback.get(request.userId)
    _ = session getOrElse {
      Future.failed(new Exception("Invalid session key"))
    }


    // Step 2: Check if the user has the permission to add a product
    hasPermission <- permissionCallback.get(request.userId, Permission.ADD_ITEM)
    _ = hasPermission getOrElse {
      Future.failed(new Exception("User does not have permission to add products"))
    }

    // Step 3: Check if the product name already exists in the database
    itemExists <- itemCallback.getByName(request.name)
      _=itemExists getOrElse{
        Future.failed(new Exception(s"Product with name '${request.name}' already exists"))
      }

    // Step 4: Add the new product to the database
    itemId <- itemCallback.addItem(request.name,  request.stock, request.price, request.description)
    item <-itemCallback.get(itemId)


  } yield item getOrElse(throw ClassNotFoundException)
}
