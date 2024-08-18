package data.adapter

import domain.store.Item
import scalikejdbc.WrappedResultSet

object ItemFactory {

  def item(dto: WrappedResultSet): Item =
    Item(
      dto int "id", dto string "name", dto string "description", dto double "price", dto int "sellerId", dto int "stock"
    )


}
