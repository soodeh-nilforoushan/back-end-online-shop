package data.repository
import contract.callback.user.UserCallback
import data.adapter.UserFactory
import domain.store.Item
import domain.user.User
import modules.DatabaseModule
import modules.DatabaseModule.onlineShop
import scalikejdbc.{NamedDB, scalikejdbcSQLInterpolationImplicitDef}


import scala.concurrent.Future


class UserRepository extends UserCallback with DatabaseModule {

  override def get(id: Long): Future[Option[User]] = Future {
    NamedDB(onlineShop) readOnly { implicit session =>
      sql"""
        SELECT *
        FROM "user"
        WHERE id = $id
      """.map(UserFactory.user).single()
    }
  }



  override def getBy(username: String): Future[Option[User]] = Future {
    NamedDB(onlineShop) readOnly { implicit session =>
      sql"""
        SELECT *
        FROM "user"
        WHERE username = $username
      """.map(UserFactory.user).single()
    }
  }

  override def addUser(username: String, password:String, name: String, email: String, role: User.Role): Future[Long] = Future {
    NamedDB(onlineShop) localTx { implicit session =>
      sql"""
        INSERT INTO "user"(username,password, name, email, role)
           values ($username ,$password,$name, $email, $role)
           """.updateAndReturnGeneratedKey()
    }
  }

  override def update( userID: Long,
                       userName: Option[String],
                       password: Option[String] ,
                       email: Option[String]  ): Future[Unit] = Future {
    NamedDB(onlineShop) localTx {
      implicit session =>
        session
        sql"""
             UPDATE "user"
              WHERE id=${userID}
             SET
              userName=${userName}
              password=${password}
              email=${email}
           """.update()
    }
  }

  override def remove(id: Long): Future[Unit] = Future {
    NamedDB(onlineShop) localTx { implicit session =>
      sql"""
           DELETE FROM "user"
           WHERE id = ${id}
         """.update()
    }
  }

}

