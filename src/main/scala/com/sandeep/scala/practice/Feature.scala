package com.sandeep.scala.practice

/**
  * Created by sandeep.jakka on 8/29/17.
  */
trait Feature[A] {
  def compare(that: A): Int

  def >(that: A): Boolean = this.compare(that) > 0

  def <(that: A): Boolean = this.compare(that) < 0
}
