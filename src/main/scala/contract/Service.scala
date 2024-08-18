package contract


import scala.concurrent.ExecutionContext
import scala.concurrent.Future

abstract class Service[Request, Response] {
  def call(request: Request)(implicit ec: ExecutionContext): Future[Response]

}
