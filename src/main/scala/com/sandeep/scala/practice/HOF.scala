package com.sandeep.scala.practice

/**
  * Created by sandeep.jakka on 9/3/17.
  */
class HOF {

  // Returns an function that takes a string and returns a Int .. Hence it is a "Higher Order Function" ( HOF )
  def infer(input: Array[String], whichF: Boolean): (String => Int) = {
    if (whichF) {
      format1
    }
    else {
      format2
    }
  }

  def format1(input: String): Int = ???

  def format2(input: String): Int = ???
}

