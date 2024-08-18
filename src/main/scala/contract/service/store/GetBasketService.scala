package contract.service.store

import contract.Service
import domain.store.Basket


abstract class GetBasketService extends Service[GetBasketService.Request, Basket]

object GetBasketService{
  case class Request()
}

