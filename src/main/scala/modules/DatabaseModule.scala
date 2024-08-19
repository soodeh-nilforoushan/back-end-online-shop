package modules

import com.zaxxer.hikari.HikariDataSource
import scalikejdbc.{ConnectionPool, DataSourceConnectionPool}

import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext

trait DatabaseModule {
  implicit val ec: ExecutionContext = DatabaseModule.executionContext
}

object DatabaseModule {

  // Create an ExecutionContext using a cached thread pool
  implicit val executionContext: ExecutionContext = ExecutionContext fromExecutor Executors.newCachedThreadPool()
  private var dataSources: Vector[HikariDataSource] = Vector.empty

  // Initialize the database connection pool
  def init(name: String): String = {
    try {
      val ds = new HikariDataSource()
      ds.setPoolName(name)
      ds.setJdbcUrl("jdbc:postgresql://192.168.98.125:5432/sn_online_shop")
      ds.setUsername("soodeh")
      ds.setPassword("soodeh")
      ds.setDriverClassName("org.postgresql.Driver")
      ds.setMinimumIdle(1)
      ds.setMaximumPoolSize(2)
      ds.setIdleTimeout(6000000)
      ds.setMaxLifetime(6000000)

      // Add the connection pool to ScalikeJDBC
      ConnectionPool.add(name, new DataSourceConnectionPool(ds))

      // Synchronized block to safely modify the dataSources vector
      this.synchronized {
        dataSources :+= ds
      }
      name
    } catch {
      case e: Exception =>
        e.printStackTrace() // Log the error appropriately
        throw new RuntimeException("Failed to initialize the database connection pool", e)
    }
  }

  // Create a lazy val for the onlineShop connection pool
  lazy val onlineShop: String = init("onlineShop")

  // Clean up connection pools on shutdown
  def close(): Unit = {
    dataSources.foreach { ds =>
      try {
        ds.close()
      } catch {
        case e: Exception =>
          e.printStackTrace() // Log the closing error
      }
    }
  }
}