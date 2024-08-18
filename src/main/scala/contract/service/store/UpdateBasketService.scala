package contract.service.store

import contract.Service
import domain.store.Basket


abstract class UpdateBasketService extends Service[UpdateBasketService.Request, Basket]

object UpdateBasketService{
  case class Request(ItemID:Long,UserID: Long,quantity: Long)
}
