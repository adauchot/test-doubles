package com.workshop

trait PurchaseOrderRepository {
  def save(purchase: Purchase): Purchase
}