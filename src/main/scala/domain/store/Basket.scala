package domain.store

case class Basket(ItemID: Long, userID:Long, Items:List[Item], totalAmount: Double, quantity: Long)
