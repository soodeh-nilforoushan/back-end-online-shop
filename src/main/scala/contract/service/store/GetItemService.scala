package contract.service
import contract.Service
import domain.store.Item


abstract class GetItemService extends Service[GetItemService.Request, Item]

  object GetItemService{
         case class Request(id: Long, name: String, description: String, price: Double, sellerId: Long, stock:Long)
  }
