package dummies

import com.workshop.StockService

class DummyStockService extends StockService {
  override def checkWhetherEnoughStock(productAndQuantityPairs: List[(String, Int)]): Boolean = true
}
