package com.sandeep.scala.practice

/**
  * Created by sandeep.jakka on 9/3/17.
  */
class Rational(n: Int, d: Int) { //Primary constructor
  def this(n: Int) = this(n, 1) // Auxillary constructor ..Should call the
private[this] def this() = this(0)
}

/**
  * Auxillary constructor should call other constructor first always
  * AND IMP
  * ONLY PRIMARY CONSTRUCTOR can call the super class constrcutors...not the auxillary one's.
  */
object Main1 {
  val rational1 = new Rational(2) // 1-arg def this() .. auxillary constructor
  val rational2 = new Rational(1, 2) //2 -arg primary constructor
  ///val rational3 = new Rational()  // since def this() is private scoped .. it can't be seen outside.
}