package com.sandeep.scala.practice

/**
  * Created by sandeep.jakka on 9/3/17.
  */
object Comprehenstions extends App {
  val res = for {
    x <- List(1, 2, 3) if x % 2 != 0
    y <- List(4, 5, 6)
    z <- List(3, 7)
  } yield x * y + z
  println(res)

  /**
    * x and y above are called generators ....
    * if's are converted to withGFilter
    * generators are convered to nested flatmaps
    * the expression after yield is converted to a map.
    */

  val res2 = List(1, 2, 3)
    .withFilter(_ % 2 != 0)
    .flatMap(x =>
      List(4, 5, 6).map(y => x * y))
    .flatMap(y => List(3, 7).map(z => z + y))
  println(res2)


  /** for comprehensions  for using foreach
    * If yield is missing then map is replaced by foreach
    * foreach has no side effects
    *
    * */
  /** Anonymous function with no return type... Return type is inferred by the compiler */
  val isOdd = (x: Int) => (x % 2) != 0

  for {
    x <- List(1, 2, 3, 4, 5) if isOdd(x)
  } print(x)

  List(1, 2, 3, 4, 5)
    .withFilter(_ % 2 != 0)
    .foreach(x => print(x))

}