package com.scalaproject

import Interface.CLI.MainMenu
import parser.Parser

import com.scalaproject.Interface.GUI

import java.awt.{BorderLayout, Dimension}
import javax.imageio.plugins.tiff.ExifGPSTagSet
import javax.swing.{JFrame, JScrollPane, JTextArea}

object MainClass extends App {

  val airports = Parser.factoryAiport("data/Airports.csv").toList
  val countries = Parser.factoryContry("data/Countries.csv").toList
  val runways = Parser.factoryRunway("data/runways.csv").toList


  println("Que souhaitez vous faire ?")
  println("1 - Requête")
  println("2 - Rapport")
  println("Utilisé n'importe qu'elle autre touche pour quitter le programme")

  val userInput = scala.io.StdIn.readLine()

  MainMenu(userInput, airports, countries, runways)

}