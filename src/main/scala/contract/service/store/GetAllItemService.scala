package contract.service.store

import contract.Service
import domain.store.Item

abstract class GetAllItemService extends Service[GetAllItemService.Request, List[Item]]

object GetAllItemService{
  case class Request()
}
