package contract.service.store

import contract.Service
import domain.store.Item

abstract class RemoveItemService extends Service[RemoveItemService.Request, Unit]

object RemoveItemService{
  case class Request(userID: Long, itemName: String)
}
