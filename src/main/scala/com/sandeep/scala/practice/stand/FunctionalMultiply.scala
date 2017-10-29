package com.sandeep.scala.practice.stand

/**
  * Created by sandeep.jakka on 9/18/17.
  */
object FunctionalMultiply extends App {

  def multiTale(): String = {
    val tableSeq = for (row <- 1 to 20) yield makeRow(row)
    tableSeq.mkString("\n")
  }

  def makeRow(row: Int): String = makeRowSeq(row).mkString

  def makeRowSeq(row: Int): Seq[String] = for (col <- 1 to 20) yield f"${row * col}%4d"

  println(multiTale())
}
