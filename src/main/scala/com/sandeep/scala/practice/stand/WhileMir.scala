package com.sandeep.scala.practice.stand

/**
  * Created by sandeep.jakka on 9/12/17.
  */
object WhileMir extends App {
  val lineList = List("Hi", "Hello", "Text", "")
  val linesIter = lineList.iterator
  var line = ""

  def readLine(): String = linesIter.next()

  while ( {
    line = readLine();
    line
  } != "") {
    println(line)
  }
}
