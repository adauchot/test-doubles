package com.workshop

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer

import java.util.UUID

object Main extends App {
  implicit val system: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  val inMemPurchaseOrderRepository = new PurchaseOrderRepository {
    val purchaseHashlist = scala.collection.mutable.HashMap.empty[UUID, Purchase]

    override def save(purchase: Purchase): Purchase = {
      purchaseHashlist.addOne(
        purchase.id -> purchase
      )

      purchase
    }
  }

  val invoicingService = new InvoicingService {
    override def issue(purchase: Purchase): Unit = {
      println(s"Purchase issued: $purchase")
    }
  }

  //val confirmCheckout = new ConfirmCheckout(inMemPurchaseOrderRepository, invoicingService)
}
