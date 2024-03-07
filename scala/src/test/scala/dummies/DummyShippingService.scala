package dummies

import com.workshop.{Purchase, ShippingService}

class DummyShippingService extends ShippingService {

  override def ship(customerEmailAddress: String, purchase: Purchase): Unit = {}
}
