package contract.service.store

import contract.Service
import domain.store.Item

abstract class AddItemService extends Service[AddItemService.Request, Item]

object AddItemService{
  case class Request(userId: Long, name: String, description: String, price: Double, stock:Long)
}
