package com.scalaproject
package factory

case class Runway(id: Int,
                  airportKey: Int,
                  airportIdentifier: String,
                  length: String,
                  width: String,
                  surface: String,
                  lighted: Int,
                  closed: Int,
                  leIdent: String)
