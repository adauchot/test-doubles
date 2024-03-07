package dummies

import com.workshop.PricingService

class DummyPricingService extends PricingService {

  override def getPrices(productIds: List[String]): List[(String, Double)] = {
    productIds.map(_ -> 20.0)
  }
}
