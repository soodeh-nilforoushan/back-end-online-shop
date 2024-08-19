package entry.rest.finatra.adapter.store

import entry.rest.finatra.RequestWrapper

object ItemFactory {

  def getItemRequest: (RequestWrapper, GetItemRequestDTO)=> getItemRequestService.Request =(rw, _) => getItemService.Request(rw.getKey)

  def addItemRequest: ()

  def removeItemRequest: ()

  def updateItemRequest: ()



}
