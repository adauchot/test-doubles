package fakes

import com.workshop.{Purchase, PurchaseOrderRepository}

import java.sql.DriverManager

class InMemPurchaseOrderRepository extends PurchaseOrderRepository {
  private val conn = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "test", "test")
  private val statement = conn.createStatement()

  statement.execute(
    """
      |create table orders (
      |  id varchar(50) primary key,
      |  customerId varchar(50),
      |  shippingAddress varchar(255)
      |);
      |""".stripMargin)

  statement.execute(
    """
      |create table order_products (
      |  orderId uuid,
      |  productId varchar(50),
      |  quantity int
      |);
      |""".stripMargin)

  statement.close()

  override def save(purchase: Purchase): Purchase = {
    val insertOrderQuery = conn.prepareStatement("insert into orders (id, customerId, shippingAddress) values (?, ?, ?)")
    insertOrderQuery.setString(1, purchase.id.toString)
    insertOrderQuery.setString(2, purchase.customerId)
    insertOrderQuery.setString(3, purchase.shippingAddress)

    insertOrderQuery.execute()

    purchase.productAndQuantityPairs.foreach { case (productId, quantity) =>
      val insertOrderProductQuery = conn.prepareStatement("insert into order_products (orderId, productId, quantity) values (?, ?, ?)")
      insertOrderProductQuery.setString(1, purchase.id.toString)
      insertOrderProductQuery.setString(2, productId)
      insertOrderProductQuery.setInt(3, quantity)

      insertOrderProductQuery.execute()
    }

    purchase
  }
}
