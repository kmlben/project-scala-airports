package com.scalaproject
package parser

import Tools.Converter
import factory.{Airport, Country, Runway}

import com.scalaproject.Database.DbManager

object Parser {

  def factoryAiport(file: String): Iterator[Airport] = {

    val bufferedSource = io.Source.fromFile(file)

    bufferedSource.getLines().drop(1) map (O => madeAirport(O.split(",")))

  }

  def madeAirport(data: Array[String]): Airport = {

    Airport(
      Converter.toInt(data(0)),
      data(1),
      data(2),
      data(3),
      Converter.toFloat(data(4)),
      Converter.toFloat(data(5)),
      Converter.toInt(data(6)),
      data(7),
      data(8),
      data(9),
      data(10),
      data(11)
    )

  }

  def factoryContry(file: String): Iterator[Country] = {

    val bufferedSource = io.Source.fromFile(file)

    bufferedSource.getLines().drop(1) map (O => madeCountry(O.split(",", -1)))

  }

  def madeCountry(data: Array[String]): Country = {

    Country(
      Converter.toInt(data(0)),
      data(1),
      data(2),
      data(3),
      data(4),
      data(5)
    )

  }

  def factoryRunway(file: String): Iterator[Runway] = {

    val bufferedSource = io.Source.fromFile(file)

    bufferedSource.getLines().drop(1) map (O => madeRunway(O.split(",", -1)))

  }

  def madeRunway(data: Array[String]): Runway = {

    Runway(
      Converter.toInt(data(0)),
      Converter.toInt(data(1)),
      data(2),
      data(3),
      data(4),
      data(5),
      Converter.toInt(data(6)),
      Converter.toInt(data(7)),
      data(8)
    )

  }

}