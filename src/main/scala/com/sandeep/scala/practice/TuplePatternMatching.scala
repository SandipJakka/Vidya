package com.sandeep.scala.practice

/**
  * Created by sandeep.jakka on 9/4/17.
  */
object TuplePatternMatching extends App {

  def f(x: Any): Unit = {
    val y = x match {
      case (1, 1) => println("the only ones")
      //variable pattern in the tuple
      case (v, 2) => println(v + " is paired with 2")

      //deep match with wildcards and  a variable
      case (_, _, (v, 5)) => println("Deep tuple with " + v + " and 5 in the nested pair ")

      case _ => println("default case")
    }

  }

  f(1, 1)
  f(5, 2)
  f(2, 3, 6, 5)

  def V[A](x: Vector[A]): Unit = {

  }

  def l(x: List4): Unit = {
    val y = x match {
      case Nil1 => println("Empty List")
      case Cons(h, Nil1) => print("h and Nil")
      case Cons(h, Cons(sh, _)) => println(" A list starting with " + h + " and ending with " + sh)
    }
  }

  def l1[A](x: List[A]): String = {
    //@formatter:off
    val y = x match {
      case List(1, _, _, _) => "list of 4 elements , first is 1"
      case List(2, _*)      => "List of elements starting with 2"
      case x :: Nil         => "Single element List starting from x : " + x
      case 1 +: _           => "List starting with element 1 "
      case Nil              => " Empty list"
    }
   //@formatter:on
    y
  }

  def t[C](x: Tuple1[C]): Unit = {

  }

  sealed abstract class List4

  case class Cons(head: Int, tail: List4) extends List4


  println("================================")

  l(Nil1)
  l(Cons(10, Cons(3, Nil1)))
  l(Cons(10, Cons(20, Cons(30, Cons(40, Nil1)))))
  l(Cons(6, Nil1))

  println()
  println("****************")

  println(l1(List(1, 2, 3, 4)))
  println(l1(List(2, 3, 4)))
  println(l1(List(Nil))) // Still a list containing Nil
  println(l1(Nil))

  case object Nil1 extends List4
}
