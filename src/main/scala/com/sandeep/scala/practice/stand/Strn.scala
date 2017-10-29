package com.sandeep.scala.practice.stand

/**
  * Created by sandeep.jakka on 9/5/17.
  */
object Strn extends App {
  val argument = "Sandeep"
  println(s"Hello,${argument}")


  val n = 12.0
  val m = 23.12

  println(s"Sum = {$n+$m}")
  println(s"Sum = ${n + m}")

  // Anonymous functions.


  val anonFunction = (x: Int, y: Int) => if (x > y) x else y
  // If a methods/operation has : at the end, then it is right associative
  // else everything is left associatove.
  val lis1 = 1 :: 2 :: 3 :: Nil
  val lis2 = 4 :: 5 :: 6 :: Nil
  println(lis1)
  val lis3 = lis1 ::: lis2

  // Remove name/def/ and replace = with => to make an anonymous function.
  def namedFunction(x: Int, y: Int) = {
    if (x > y) x else y
  }

  println(lis3)
}
