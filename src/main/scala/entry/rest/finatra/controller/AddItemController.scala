package entry.rest.finatra.controller

import com.twitter.finagle.http.Cookie
import com.twitter.finatra.http.Controller
import contract.service.store.AddItemService
import entry.rest.finatra.{RequestWrapper, UnauthenticatedRequestWrapper}
import entry.rest.finatra.adapter.user.{UserDTOFactory, UserFactory}
import entry.rest.finatra.adapter.user.api.{SignInRequestDTO, SignUpRequestDTO}

import javax.inject.Inject

class AddItemController @Inject(addItemService: AddItemService) extends Controller{
prefix("api/v1"){
  post("/posts","additem"){ requestWrapper: RequestWrapper =>
    val requestDTO = requestWrapper.getRequestDTO[]

}
}

prefix("/api/v1") {

  post("/posts", "Publish Post") { requestWrapper: RequestWrapper =>
    val requestDTO = requestWrapper.getRequestDTO[PublishPostRequestDTO]
    publishPostService call BlogFactory.publishPostRequest(requestWrapper, requestDTO) map BlogDTOFactory.post map { responseDTO =>
      response created responseDTO
    }
  }

  get("/posts/:post_id", "Publish Post") { requestWrapper: RequestWrapper =>
    val requestDTO = requestWrapper.getRequestDTO[GetPostRequestDTO]
    getPostService call BlogFactory.getPostRequest(requestWrapper, requestDTO) map BlogDTOFactory.post
  }

}


}
