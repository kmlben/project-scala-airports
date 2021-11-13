package com.scalaproject
package Operations

import factory.Airport

import scala.annotation.tailrec
import scala.collection.immutable.ListMap

object ReportOperation {

  def isStringCountryCode(s: String): Boolean = {
    s.equals(s.toUpperCase()) && s.length == 2
  }

  def getCountriesTopAirport(airports: List[Airport]): Map[String, Int] = {
    val mapOfNumber = mappingOfAiportNumber(airports)

    ListMap(mapOfNumber.toSeq.sortWith(_._2 > _._2): _*)
      .take(10)
  }

  private def mappingOfAiportNumber(airports: List[Airport]): Map[String, Int] = {
    @tailrec
    def aux(airports: List[Airport], acc: Map[String, Int]): Map[String, Int] = airports match {
      case Nil => acc
      case x :: xs =>
        val key = x.country
        if (acc.contains(key)) {
          val nb = acc(key)
          val acc2 = acc.-(key)
          aux(xs, acc2 + (key -> (nb + 1)))
        } else {
          aux(xs, acc + (key -> 1))
        }
    }

    aux(airports, Map[String, Int]())

  }

  def getCountriesBotAirport(airports: List[Airport]): Map[String, Int] = {
    val mapOfNumber = mappingOfAiportNumber(airports)

    ListMap(mapOfNumber.toSeq.sortWith(_._2 < _._2): _*)
      .take(10)
  }

}
