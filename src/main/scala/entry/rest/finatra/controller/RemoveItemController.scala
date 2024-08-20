package entry.rest.finatra.controller

class RemoveItemController@(){

  prefix("/api/v1") {
    post("/additem", "add item") { requestWrapper: RequestWrapper =>
      val requestDTO = requestWrapper.getRequestDTO[AddItemRequestDTO]
      addItemService.call(StoreFactory.addItemRequest(requestWrapper, requestDTO)) map StoreDTOFactory.item map { responseDTO =>
        response.created(responseDTO)
      }
    }
  }
}
