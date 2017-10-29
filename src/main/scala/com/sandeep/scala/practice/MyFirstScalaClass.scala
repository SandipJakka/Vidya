package com.sandeep.scala.practice

/**
  * Created by sandeep.jakka on 8/29/17.
  */
class MyFirstScalaClass {

  def main(args: Array[String]): Unit = {
    println("Hello world-123!!!");
  }

  class Animal(val name1: String) {

  }

  class Cat(name1: String, val breed: String) extends Animal(name1) {

  }

  // Object private .. interesting ...
  class Person(private[this] val name: String) {
    def salute(person: Person) {
      println(s"Hi  ${this.name}, my name is $name ")
    }
  }


}
