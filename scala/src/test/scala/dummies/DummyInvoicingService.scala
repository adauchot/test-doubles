package dummies

import com.workshop.{InvoicingService, Purchase}

class DummyInvoicingService extends InvoicingService {

  override def issue(purchase: Purchase): Unit = {}
}
