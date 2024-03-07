package com.workshop

trait PricingService {
  def getPrices(productIds: List[String]): List[(String, Double)]
}
