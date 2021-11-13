package com.scalaproject
package Operations

import factory.{Airport, Country, Runway}

import scala.annotation.tailrec
import scala.collection.immutable.ListMap

object QueryOperation {

  def getRunwayTypeCountry(airports: List[Airport], runways: List[Runway]): Map[String, Set[String]] = {
    @tailrec
    def aux(airports: List[Airport], runways: List[Runway], acc: Map[String, Set[String]]): Map[String, Set[String]] = runways match {
      case Nil => acc
      case x :: xs =>
        x.surface match {
          case "" => aux(airports, xs, acc)
          case _ =>
            val airportOfRunway = airports.find(a => a.identifier.equals(x.airportIdentifier))
            airportOfRunway match {
              case None => aux(airports, xs, acc)
              case Some(value) =>
                val key = value.country
                if (acc.contains(key)) {
                  val setOfCountry = acc(key)
                  val acc2 = acc.-(key)
                  aux(airports, xs, acc2 + (key -> (setOfCountry + x.surface)))
                } else {
                  aux(airports, xs, acc + (key -> (Set() + x.surface)))
                }
            }
        }
    }


    aux(airports, runways, Map[String, Set[String]]())
  }

  def getMostLatRunway(runways: List[Runway]): Set[String] = {
    @tailrec
    def aux(runways: List[Runway], acc: Map[String, Int]): Map[String, Int] = runways match {
      case Nil => acc
      case x :: xs =>
        val key = x.leIdent
        key match {
          case "" => aux(xs, acc)
          case keyValue =>
            if (acc.contains(keyValue)) {
              val nb = acc(keyValue)
              val acc2 = acc.-(keyValue)
              aux(xs, acc2 + (keyValue -> (nb + 1)))
            } else {
              aux(xs, acc + (keyValue -> 1))
            }
        }
    }


    val frequencyMap = aux(runways, Map[String, Int]())
    ListMap(frequencyMap.toSeq.sortWith(_._2 > _._2): _*)
      .take(10).keys
      .toSet
  }

  def queryName(countryName: String, countries: List[Country], airports: List[Airport], runways: List[Runway]): Map[String, List[String]] = {
    val code = getCodeFromName(countryName, countries)
    code match {
      case "" => Map[String, List[String]]()
      case codeValue => queryCode(codeValue, airports, runways)
    }
  }

  def getCodeFromName(countryName: String, countries: List[Country]): String = {
    val countryResult = countries.find(c => c.name match {
      case "" => false
      case value => value.equals(countryName)
    })

    countryResult match {
      case None => ""
      case x => x.get.code
    }
  }

  def queryCode(countryCode: String, airports: List[Airport], runways: List[Runway]): Map[String, List[String]] = {
    val airportsInCountry = getAirportsInCountry(countryCode, airports)
    getRunwaysInAirportList(airportsInCountry.map(a => (a.identifier, a.name)), runways)
  }

  def getAirportsInCountry(countryCode: String, airports: List[Airport]): List[Airport] = {

    airports.filter(a => a.country match {
      case "" => false
      case value => value.equals(countryCode)
    })
  }

  def getRunwaysInAirportList(airports: List[(String, String)], runways: List[Runway]): Map[String, List[String]] = {
    @tailrec
    def aux(airports: List[(String, String)], runways: List[Runway], acc: Map[String, List[String]]): Map[String, List[String]] = airports match {
      case Nil => acc
      case (ident, name) :: xs =>
        aux(xs, runways, acc + (name -> getRunwaysInAirport(ident, runways)))
    }

    aux(airports, runways, Map[String, List[String]]())
  }

  private def getRunwaysInAirport(airportIdent: String, runways: List[Runway]): List[String] = {
    runways.filter(r => r.airportIdentifier match {
      case "" => false
      case value => value.equals(airportIdent)
    }).map(r => "id = " + r.id)
  }

}
