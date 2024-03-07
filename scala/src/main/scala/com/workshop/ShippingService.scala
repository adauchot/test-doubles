package com.workshop

trait ShippingService {
  def ship(customerEmailAddress: String, purchase: Purchase): Unit
}

