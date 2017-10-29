package com.project.scala.filesystem

import java.util.Scanner

/**
  * Created by sandeep.jakka on 9/28/17.
  */
object FileSystemApp extends App {
  val firstRoot = Directory.empty("/home/sandeep", "/home/sandeep")
  val scanner = new Scanner(System.in)
  var state = State(firstRoot, firstRoot)

  while (true) {
    state.show
    state = Command.from(scanner.nextLine()).apply(state)
  }
}
