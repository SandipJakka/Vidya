package com.sandeep.scala.practice

import scala.util.Random

/**
  * Created by sandeep.jakka on 9/4/17.
  */
object VectorTestting extends App {
  val maxRuns = 1000000
  val maxCapacity = 1000
  val numberList = (1 to maxCapacity).toList
  val numberVector = (1 to maxCapacity).toVector
  val numbers = (1 to 6)

  println(getWriteTime(numberList))
  println(getWriteTime(numberVector))

  //  println(numberVector)
  //  println(numberList)
  val lis = numbers.groupBy {
    _ % 2
  }

  println(lis)
  val phoneMappings = Map(2 -> "ABC", 1 -> "XYZ").withDefaultValue("Sandeep")

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = (1 to maxRuns) map { _ =>
      val currentTime = System.nanoTime
      collection updated(r nextInt (maxCapacity), 0)
      System.nanoTime - currentTime
    }
    1.0 * times.sum / times.length
  }

  phoneMappings.map(kv => kv._1 -> kv._2.toLowerCase) // treated as a tuple...

  println(phoneMappings get (25))
  println(assert("Sandeep".equals())) //defaults only are returned when apply is called.. STRANGE BEHAVIOUR


}
