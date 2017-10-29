package com.sandeep.scala.practice

import java.sql.{Date => SqlDate}
import java.time.Instant
import java.util.{Date => JavaDate}
import java.{util => ju}

/**
  * Created by sandeep.jakka on 8/29/17.
  */
//Covariant
class List11[+A] {
  // >: is the lower bound type .. as +A is a covariant
  def add[B >: A](b: B): List1[B] = ??? // Nothing
}

// Contravariant -- wierd case...
class List2[-A] {
  //Upper bound
  def add[B <: A](b: B): List2[B] = {
    SqlDate.valueOf("")
    JavaDate.from(Instant.now())
    var t: ju.Set[Int] = new ju.HashSet[Int]();

    new List2
  }
}