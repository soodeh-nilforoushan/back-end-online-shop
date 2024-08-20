package entry.rest.finatra.controller

import com.twitter.finatra.http.Controller
import contract.service.store.AddItemService
import entry.rest.finatra.RequestWrapper
import entry.rest.finatra.adapter.store.{StoreDTOFactory, StoreFactory}
import entry.rest.finatra.adapter.user.store.api.AddItemRequestDTO
import scala.concurrent.ExecutionContext.Implicits._
import com.google.inject.Inject


class StoreController @Inject()(addItemService: AddItemService) extends Controller {
  prefix("/api/v1") {
    post("/additem", "add item") { requestWrapper: RequestWrapper =>
      val requestDTO = requestWrapper.getRequestDTO[AddItemRequestDTO]
      addItemService.call(StoreFactory.addItemRequest(requestWrapper, requestDTO)) map StoreDTOFactory.item map { responseDTO =>
        response.created(responseDTO)
      }
    }
  }

  prefix("/api/v1"){
    post("removeitem", "remove item"){ requestWrapper:  RequestWrapper =>
    }
  }
}
