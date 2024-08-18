package contract.service.store

import contract.Service
import domain.store.Basket

abstract class RemoveFromBasket extends Service[RemoveFromBasket.Request, Basket]

object RemoveFromBasket{
  case class Request(ItemID:Long,UserID: Long)
}
