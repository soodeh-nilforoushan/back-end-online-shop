package modules

import com.sun.corba.se.impl.orbutil.CorbaResourceUtil.getString
import com.zaxxer.hikari.HikariDataSource
import scalikejdbc.{ConnectionPool, DataSourceConnectionPool}

import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext


trait DatabaseModule {

  implicit val ec: ExecutionContext = DatabaseModule.executionContext

}

object DatabaseModule {

  implicit val executionContext: ExecutionContext = ExecutionContext fromExecutor Executors.newCachedThreadPool()
  private var dataSources = Vector.empty[HikariDataSource]

  def init(name: String): String = {
    val ds = new HikariDataSource()
    ds.setPoolName(name)
    ds.setJdbcUrl("jdbc:postgresql://192.168.98.125:5432/onlineshop")
    ds.setUsername("onlineShop")
    ds.setPassword("onlineShop")
    ds.setDriverClassName("org.postgresql.Driver")
    ds.setMinimumIdle(1)
    ds.setMaximumPoolSize(2)
    ds.setIdleTimeout(6000000)
    ds.setMaxLifetime(6000000)
    ConnectionPool.add(name, new DataSourceConnectionPool(ds))
    dataSources.synchronized(dataSources = dataSources :+ ds)
    name
  }

  val onlineShop: String = init("onlineShop")

  def close(): Unit = {
    dataSources.foreach(_.close())
  }
}

