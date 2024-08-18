package contract.callback.store

import domain.store.Item
import scala.concurrent.Future

abstract class BasketCallback {
  def addToBasket(ItemID: Long, userID: Long, quantity: Long): Future[Long]


  def removeFromBasket(itemID:Long,UserID: Long):Future[Unit]

  def updateBasket(ItemID:Long,UserID: Long,quantity: Long):Future[Unit]


  def getBasket(userID: Long):Future[Option[Item]]


}
