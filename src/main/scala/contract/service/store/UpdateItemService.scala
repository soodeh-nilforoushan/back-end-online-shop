package contract.service.store

import contract.Service
import domain.store.Item

//this is fof seller to update inventory
abstract class UpdateItemService extends Service[UpdateItemService.Request, Unit]

object UpdateItemService{
  case class Request(userID:Long, name: String, stock:Option[Long],price:Option[Double],description: Option[String])
}

