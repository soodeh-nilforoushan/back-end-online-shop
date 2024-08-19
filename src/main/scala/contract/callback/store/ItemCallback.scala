package contract.callback.store

import domain.store.Item
import scala.concurrent.Future

abstract class ItemCallback {

  def addItem(name: String, stock: Long, price: Double, description: String): Future[Long]

  def get(itemID: Long): Future[Option[Item]]
  def getByName(name: String): Future[Option[Item]]

  def update(
                    name: String,
                    stock: Option[Long] = None,
                    price: Option[Double] = None,
                    description: Option[String]= None): Future[Unit]

  def removeByName(itemName: String) : Future[Unit]
  def getAll :Future[Option[Item]]

}
