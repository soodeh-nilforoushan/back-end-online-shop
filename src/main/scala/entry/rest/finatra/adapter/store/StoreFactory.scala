package entry.rest.finatra.adapter.store

import contract.service.store.{AddItemService, RemoveItemService}
import entry.rest.finatra.RequestWrapper
import entry.rest.finatra.adapter.store.api.RemoveItemRequestDTO
import entry.rest.finatra.adapter.user.store.api.AddItemRequestDTO

object StoreFactory {

//  def getItemRequest: (RequestWrapper, GetItemRequestDTO)=> getItemRequestService.Request =(rw, _) => getItemService.Request(rw.getKey)

  def addItemRequest: (RequestWrapper,AddItemRequestDTO)=> AddItemService.Request =(rw,dto)=>
    AddItemService.Request(rw.getUserID, dto.name, dto.description,dto.price, dto.stock)

  def removeItemRequest: (RequestWrapper, RemoveItemRequestDTO)=> RemoveItemService.Request=(rw,dto)=>
    RemoveItemService.Request(rw.getUserID, dto.itemName)
//
//  def updateItemRequest: ()



}
