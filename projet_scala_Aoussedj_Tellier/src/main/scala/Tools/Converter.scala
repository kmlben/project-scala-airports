package com.scalaproject
package Tools

object Converter {

  def toInt(s: String): Int = {
    try {
      s.toInt
    } catch {
      case e: Exception => 0
    }
  }

  def toFloat(s: String): Float = {
    try {
      s.toFloat
    } catch {
      case e: Exception => 0
    }
  }


}
