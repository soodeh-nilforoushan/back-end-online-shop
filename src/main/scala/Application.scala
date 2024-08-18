

import com.google.inject.Module
import com.twitter.finagle.http.Request
import com.twitter.finagle.http.Response
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters._
import com.twitter.finatra.http.routing.HttpRouter
import entry.rest.finatra.CustomScalaObjectMapperModule
import entry.rest.finatra.controller.{AddItemController, AuthenticationController}
import entry.rest.finatra.filters.AuthorizationFilter
import modules.{CallbackModule, ServiceModule}

import com.google.inject.Module
import com.twitter.finagle.http.Request
import com.twitter.finagle.http.Response
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters._
import com.twitter.finatra.http.routing.HttpRouter


object Application extends HttpServer {


  override protected def defaultHttpPort: String = s":${"8080"}"

  override protected def modules: Seq[Module] = Seq(CallbackModule, ServiceModule)

  override protected def configureHttp(router: HttpRouter): Unit = {
    // Filters
    router.filter[CommonFilters]
    router.filter[LoggingMDCFilter[Request, Response]]
    router.filter[TraceIdMDCFilter[Request, Response]]

    // Controllers
    // // Unauthorized Access
    router.add[entry.rest.finatra.controller.unsafe.AuthenticationController]
    // // Authorized Access
    router.add[AuthorizationFilter, AuthenticationController]
    router.add[AuthorizationFilter, AddItemController]
  }

}
