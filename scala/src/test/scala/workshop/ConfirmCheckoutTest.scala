package workshop

import com.workshop.{ConfirmCheckout, StockService, UUIDGenerator}
import dummies.{DummyInvoicingService, DummyPaymentService, DummyPricingService, DummyShippingService, DummyStockService}
import fakes.InMemPurchaseOrderRepository
import org.scalatest.funspec.AnyFunSpec
import org.scalamock.scalatest.MockFactory

import scala.util.Try

class ConfirmCheckoutTest extends AnyFunSpec with MockFactory {
  describe("ConfirmCheckout") {
    val uuidGenerator = new UUIDGenerator()

    // Dummies
    val stockService = new DummyStockService()
    val shippingService = new DummyShippingService()
    val invoicingService = new DummyInvoicingService()
    val paymentService = new DummyPaymentService()
    val pricingService = new DummyPricingService()

    // Fake
    val inMemoryOrderRepository = new InMemPurchaseOrderRepository()

    it("should confirm a checkout successfully") {
      // Dummies: StockService, PaymentService, PricingService
      // Mocks: ShippingService, InvoicingService

      val shippingService = mock[DummyShippingService]
      val invoicingService = mock[DummyInvoicingService]

      (shippingService.ship _).expects(*, *).returning(*).once()
      (invoicingService.issue _).expects(*).returning(*).once()

      val confirmCheckout = new ConfirmCheckout(
        uuidGenerator,
        inMemoryOrderRepository,
        pricingService,
        paymentService,
        invoicingService,
        shippingService,
        stockService
      )

      val executeResult = Try {
        confirmCheckout.execute(
          List(("product1", 1)),
          "1234567890123456",
          2024,
          12,
          "123",
          "John Doe",
          "123 Main St",
          "john.doe@acme.org",
          "customer1")
      }

      assert(executeResult.isSuccess)
    }

    it("should fail to confirm a checkout due to insufficient stock") {
      val mockStockService = stub[StockService]

      // Stub
      (mockStockService.checkWhetherEnoughStock _).when(*).returns(false)

      val confirmCheckout = new ConfirmCheckout(
        uuidGenerator,
        inMemoryOrderRepository,
        pricingService,
        paymentService,
        invoicingService,
        shippingService,
        mockStockService
      )

      val executeResult = Try {
        confirmCheckout.execute(
          List(("product1", 1)),
          "1234567890123456",
          2024,
          12,
          "123",
          "John Doe",
          "123 Main St",
          "john.doe@acme.org",
          "customer1")
      }

      assert(executeResult.isFailure)
      assert(executeResult.failed.toOption.forall(_.isInstanceOf[RuntimeException]))
    }
  }
}
