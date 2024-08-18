package contract.service.store

import contract.Service
import domain.store.{Basket, Item}

abstract class AddToBasketService extends Service[AddToBasketService.Request, Basket]

object AddToBasketService{
  case class Request(userID:Long, orderID: Long, quantity: Long)
}
