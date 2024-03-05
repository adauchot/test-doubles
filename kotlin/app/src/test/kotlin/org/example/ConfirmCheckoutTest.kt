package org.example

import org.junit.jupiter.api.Test
import java.util.Hashtable
import java.util.UUID

class ConfirmCheckoutTest {
    @Test
    fun dummy() {
        val purchaseOrderRepository = DummyPurchaseOrderRepository()
        ConfirmCheckout(
            uuidGenerator = UUIDGenerator(),
            purchaseOrderRepository = purchaseOrderRepository,
            pricingService = TODO(),
            paymentService = TODO(),
            invoicingService = TODO(),
            shippingService = TODO(),
            stockService = TODO()
        )
    }

    class DummyPurchaseOrderRepository : PurchaseOrderRepository {
        override fun save(purchase: Purchase): Purchase {
            TODO()
        }
    }

    @Test
    fun fake() {
        val purchaseOrderRepository = FakePurchaseOrderRepository()
        ConfirmCheckout(
            uuidGenerator = UUIDGenerator(),
            purchaseOrderRepository = purchaseOrderRepository,
            pricingService = TODO(),
            paymentService = TODO(),
            invoicingService = TODO(),
            shippingService = TODO(),
            stockService = TODO()
        )
    }

    class FakePurchaseOrderRepository: PurchaseOrderRepository {
        private val hashtable = Hashtable<UUID, Purchase>()
        override fun save(purchase: Purchase): Purchase {
            hashtable[purchase.id] = purchase
            return purchase
        }
    }

    @Test
    fun stub() {
        val purchaseOrderRepository = mock()
        ConfirmCheckout(
            uuidGenerator = UUIDGenerator(),
            purchaseOrderRepository = purchaseOrderRepository,
            pricingService = TODO(),
            paymentService = TODO(),
            invoicingService = TODO(),
            shippingService = TODO(),
            stockService = TODO()
        )
    }

    class FakePurchaseOrderRepository: PurchaseOrderRepository {
        private val hashtable = Hashtable<UUID, Purchase>()
        override fun save(purchase: Purchase): Purchase {
            hashtable[purchase.id] = purchase
            return purchase
        }
    }
}