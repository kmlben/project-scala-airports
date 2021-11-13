package com.scalaproject

class CsvTest extends AnyFunSuite  {

  val classloader = Thread.currentThread.getContextClassLoader

  test("Aeroport") {
    val fichier = classloader.getResource("airports.csv").getPath
    val aeroport = Airport.parse(fichier)
    assert(!aeroport.isEmpty)
  }

  test("Pistes") {
    val fichier = classloader.getResource("runways.csv").getPath
    val pistes = Runway.parse(fichier)
    assert(!pistes.isEmpty)
  }
}

  test("Pays") {
    val fichier = classloader.getResource("countries.csv").getPath
    val pays = Country.parse(fichier)
    assert(!pays.isEmpty)
  }
