package com.sandeep.scala.practice

/**
  * Created by sandeep.jakka on 9/4/17.
  */
object TypeSpecifiers extends App {
  def typeSpecifier[A](x: Any): Unit = {
    val y = x match {
      case l: List[A] => println("a list ")
      case _: Vector[A] => println("a vector ")
      case _ => println("Something else")
    }
  }

  typeSpecifier(List(1, "test"))
  typeSpecifier(Vector(1))
  typeSpecifier((1, 2))
}