package data.repository

import contract.callback.store.ItemCallback
import data.adapter.ItemFactory
import domain.store.Item
import modules.DatabaseModule
import modules.DatabaseModule.onlineShop
import scalikejdbc.{NamedDB, scalikejdbcSQLInterpolationImplicitDef}

import scala.concurrent.Future

class ItemRepository extends ItemCallback with DatabaseModule {
  //factory as system migire be biroon namyesh mide
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

  override def addItem(name: String, stock: Long, price: Double, description: String): Future[Long] = Future {
    NamedDB(onlineShop) localTx { implicit session =>
      sql"""
        INSERT INTO items(name,stock, price, description)
           values ($name,$stock, $price, $description)
           """.updateAndReturnGeneratedKey()
    }
  }

  override def update(name: String, stock: Option[Long], price: Option[Double],
                      description: Option[String]): Future[Unit] = Future {
    NamedDB(onlineShop) localTx {
      implicit session =>
        session
        sql"""
             UPDATE items
              WHERE name=${name}
             SET
              stock=${stock}
              price=${price}
              description=${description}
           """.update()
    }
  }
  //
    override def getAll: Future[Option[Item]] = Future {
      NamedDB(onlineShop) readOnly  { implicit session =>
        sql"""
             SELECT * FROM items
             """.map(ItemFactory.item).single()
      }
    }

  override def removeByName(itemName: String): Future[Unit] = Future{
    NamedDB(onlineShop) localTx(implicit session =>
      sql"""
          DELETE FROM items
          WHERE name = ${itemName}
      """.update())
  }

//  override def getByName(name: String): Future[Option[Item]] = ???
  override def getByName(name: String): Future[Option[Item]] = Future {
    NamedDB(onlineShop) readOnly { implicit session =>
      sql"""
        SELECT *
        FROM items
        WHERE name = $name
      """.map(ItemFactory.item).single()
    }
  }
}
