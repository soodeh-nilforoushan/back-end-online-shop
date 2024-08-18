package data.repository

import contract.callback.store.ItemCallback
import data.adapter.ItemFactory
import domain.store.Item
import modules.DatabaseModule
import modules.DatabaseModule.onlineShop
import scalikejdbc.{NamedDB, scalikejdbcSQLInterpolationImplicitDef}

import scala.concurrent.Future

class ItemRepository extends ItemCallback with DatabaseModule {
  //factory is databse migire be biroon namyesh mide
  //dto factory as biroon(masalan json) migire be system namayesh mide
  override def get(id: Long): Future[Option[Item]] = Future {
    NamedDB(onlineShop) readOnly { implicit session =>
      sql"""
        SELECT *
        FROM items
        WHERE id = $id
      """.map(ItemFactory.item).single()
    }
  }
//
//  override def addItem(itemID: Long, sellerID: Long, name: String, stock: Long, price: Long, description: String): Future[Long] = Future {
//    NamedDB(onlineShop) localTx { implicit session =>
//      sql"""
//        INSERT INTO items(id,name,description, price, sellerId, stock)
//           |values ($itemID,$name ,$description, $price, $sellerID, $stock)
//           |""".updateAndReturnGeneratedKey()
//    }
//  }
//
//  override def update( itemID: Long,
//                       name: Option[String] ,
//                       stock: Option[String] ,
//                       price: Option[Double] ,
//                       description: Option[String]): Future[Unit] = Future {
//    NamedDB(onlineShop) localTx {
//      implicit session =>
//        session
//        sql"""
//             UPDATE items
//              WHERE itemID=${itemID}
//             SET
//              name=${name}
//              stock=${stock}
//              price=${price}
//              description=${description}
//
//           """.update()
//    }
//  }

//  override def remove(id: Long): Future[Unit] = Future {
//    NamedDB(onlineShop) localTx { implicit session =>
//      sql"""
//           DELETE FROM items
//           WHERE id = ${id}
//         """.update()
//    }
//  }
//
//  override def getAll: Future[Option[Item]] = Future {
//    NamedDB(onlineShop) localTx { implicit session =>
//      sql"""
//           SELECT * FROM items
//           """.map(ItemFactory.item).single()
//    }
//  }

  override def removeByName(itemName: String): Future[Option[Unit]] = ???

  override def getByName(name: String): Future[Option[Item]] = ???


  override def addItem(name: String, stock: Long, price: Double, description: String): Future[Long] = ???

  override def update(name: String, stock: Option[Long], price: Option[Double], description: Option[String]): Future[Unit] = ???

  override def getAll: Future[Option[Item]] = ???

  override def remove(itemID: Long): Future[Option[Unit]] = ???
}
