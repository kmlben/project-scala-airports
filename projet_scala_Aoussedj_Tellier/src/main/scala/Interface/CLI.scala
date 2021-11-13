package com.scalaproject
package Interface

import Operations.{QueryOperation, ReportOperation}
import factory.{Airport, Country, Runway}

object CLI {

  def PrintChoice(): Unit = {
    println("Que souhaitez-vous faire ?")
    println("1 - Requête")
    println("2 - Rapport")
    println("Utilisez n'importe qu'elle autre touche pour quitter le programme")
  }

  def MainMenu(userInput: String, airports: List[Airport], countries: List[Country], runways: List[Runway]): Unit = userInput match {
    case "1" => QueryMenu(airports, countries, runways)
    case "2" => ReportMenu(airports, countries, runways)
    case _ => println("Arret du programme.")
  }

  def ReportMenu(airports: List[Airport], countries: List[Country], runways: List[Runway]): Unit = {
    println("Quel rapport souhaitez vous voir ?")
    println("1 - Les 10 pays avec le plus d'aréoport")
    println("2 - Les 10 pays avec le moins d'aéroport")
    println("3 - Type de routes par pays")
    println("4 - Les 10 lattitudes les plus courantes")
    println("Appuyer sur n'importe quelle touche pour quitter.")

    val input = scala.io.StdIn.readLine()
    input match {
      case "1" =>

        println(ReportOperation.getCountriesTopAirport(airports))

        PrintChoice()

        val userInput = scala.io.StdIn.readLine()
        MainMenu(userInput, airports, countries, runways)

      case "2" =>

        println(ReportOperation.getCountriesBotAirport(airports))

        PrintChoice()

        val userInput = scala.io.StdIn.readLine()
        MainMenu(userInput, airports, countries, runways)

      case "3" =>

        println(QueryOperation.getRunwayTypeCountry(airports, runways))

        PrintChoice()

        val userInput = scala.io.StdIn.readLine()
        MainMenu(userInput, airports, countries, runways)
      case "4" =>

        println(QueryOperation.getMostLatRunway(runways))

        PrintChoice()

        val userInput = scala.io.StdIn.readLine()
        MainMenu(userInput, airports, countries, runways)
      case _ =>

        PrintChoice()

        val userInput = scala.io.StdIn.readLine()
        MainMenu(userInput, airports, countries, runways)
    }
  }

  def QueryMenu(airports: List[Airport], countries: List[Country], runways: List[Runway]): Unit = {
    println("Requête")
    println("Entrez le code ou le nom du pays :")
    val input = scala.io.StdIn.readLine()
    if (ReportOperation.isStringCountryCode(input)) {

      println(QueryOperation.queryCode("\"" + input + "\"", airports, runways).map { case (k, v) => k + " : " + v }.mkString("\n"))

      PrintChoice()

      val userInput = scala.io.StdIn.readLine()
      MainMenu(userInput, airports, countries, runways)

    } else {

      println(QueryOperation.queryName("\"" + input + "\"", countries, airports, runways))

      PrintChoice()

      val userInput = scala.io.StdIn.readLine()
      MainMenu(userInput, airports, countries, runways)
    }
  }

}
