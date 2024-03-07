package dummies

import com.workshop.PaymentService

class DummyPaymentService extends PaymentService {

  override def chargeCard(cardNumber: String, expirationYear: Int, expirationMonth: Int, cvv: String, cardholderName: String, amount: Double): Unit = {}
}
