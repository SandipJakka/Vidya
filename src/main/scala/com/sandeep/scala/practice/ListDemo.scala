package com.sandeep.scala.practice

import scala.annotation.tailrec

object ListDemo extends App {
  val List1A = NOOP
  val List1B = new Cons(2, new Cons(4, NOOP))
  val List1C = List1B.add(5)
  val List1D = List1C.reverse
  println(List1A)
  println(List1B)
  println(List1C)
  println(List1D)
  println(List1C ++ new Cons(9, new Cons(11, NOOP)))
  val List1E = new Cons(List1A, new Cons(List1B, new Cons(List1C, new Cons(List1D, NOOP))))
  print(List1.flatten(List1E))
}

trait List1[+T] {
  def isEmpty: Boolean

  def head: T

  def tail: List1[T]

  def add[S >: T](elem: S): List1[S]

  def ++[S >: T](other: List1[S]): List1[S] //Append
  def reverse: List1[T]

  //  def size:Int
}

//Nothing is the "subtype" of everything
// Nothing can't be instantiated
// Anything can be passed for Nothing ( Co-variance )
object NOOP extends List1[Nothing] {
  override def isEmpty: Boolean = true

  override def head: Nothing = throw new NoSuchElementException()

  override def tail: List1[Nothing] = throw new UnsupportedOperationException()

  override def add[S](elem: S): List1[S] = ??? // S >:Nothing ( S superType of Nothing )
  // is kind of redundant as Nothing is subType of all
  override def ++[S](other: List1[S]): List1[S] = other

  override def reverse: List1[Nothing] = NOOP

  //  override def size: Int = 0
  override def toString: String = "[]"
}

// Note this is a class .....
class Cons[T](val head: T, val tail: List1[T]) extends List1[T] {
  override def isEmpty: Boolean = false // Since Const is an non empty List1
  //new List1 ( prepending )  with elem as head and 'this' as the tail
  override def add[S >: T](elem: S): List1[S] = new Cons(elem, this)

  override def ++[S >: T](other: List1[S]): List1[S] = new Cons(head, tail ++ other)

  override def reverse: List1[T] = {
    @tailrec
    def reverseUtil(input: List1[T], out: List1[T]): List1[T] = {
      if (input.isEmpty) out
      else reverseUtil(input.tail, new Cons(input.head, out))
    }

    reverseUtil(this, NOOP)
  }

  override def toString: String = {
    def enumerateAll(List1: List1[T]): String = {
      if (List1.isEmpty) ""
      else if (List1.tail.isEmpty) "" + List1.head
      else List1.head + " " + enumerateAll(List1.tail)
    }

    "[" + enumerateAll(this) + "]"
  }
}

object List1 {
  def flatten[T](List1: List1[List1[T]]): List1[T] = {
    @tailrec
    def flattenUtils(remaining: List1[List1[T]], currentExpanding: List1[T], acc: List1[T]): List1[T] = {
      if (currentExpanding.isEmpty) {
        if (remaining.isEmpty) acc
        else flattenUtils(remaining.tail, remaining.head, acc);
      } else {
        flattenUtils(remaining, currentExpanding.tail, new Cons(currentExpanding.head, acc))
      }
    }

    flattenUtils(List1, NOOP, NOOP).reverse
  }

}