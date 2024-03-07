package com.workshop

trait InvoicingService {
  def issue(purchase: Purchase): Unit
}

