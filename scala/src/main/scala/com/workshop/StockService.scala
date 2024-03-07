package com.workshop

trait StockService {
  def checkWhetherEnoughStock(productAndQuantityPairs: List[(String, Int)]): Boolean
}