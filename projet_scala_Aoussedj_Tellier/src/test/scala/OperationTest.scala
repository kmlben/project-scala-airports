/*******
 Query and Report tests 
 *******/

package com.scalaproject
import Operations.ReportOperation
import Operations.QueryOperation
import factory.Airport

class OperationTest extends org.scalatest.FunSuite {

  test("testGetAirportOfSpecificCountry") {

    val airportTest1 = new Airport(
      id = 1,
      identifier = "",
      airportCat = "",
      name = "",
      lat = 0.0f,
      long = 0.0f,
      elevation = 0.0f,
      continent = "",
      country = "FR",
      region = "",
      municipality = "",
      sService = "")

    val airportTest2 = new Airport(
      id = 2,
      identifier = "",
      airportCat = "",
      name = "",
      lat = 0.0f,
      long = 0.0f,
      elevation = 0.0f,
      continent = "",
      country = "FR",
      region = "",
      municipality = "",
      sService = "")

    val airportTest3 = new Airport(
      id = 3,
      identifier = "",
      airportCat = "",
      name = "",
      lat = 0.0f,
      long = 0.0f,
      elevation = 0.0f,
      continent = "",
      country = "US",
      region = "",
      municipality = "",
      sService = "")

    val airports = List(airportTest1, airportTest2, airportTest3)

    val result = QueryOperation.getAirportsInCountry("FR", airports)

    assert(result != null)
    assert(result.size == 2)
    assert(result.head.id == 1)
    assert(result(1).id == 2)

  }
}
