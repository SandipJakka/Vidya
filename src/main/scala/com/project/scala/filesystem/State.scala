package com.project.scala.filesystem

/**
  * Created by sandeep.jakka on 9/28/17.
  */
class State(val root: Directory, val wd: Directory, val message: String) {

  def show: Unit = {
    println(s"${message}  ")
    showShell
  }

  def showShell: Unit = {
    print(s"[${root.name}]${State.SHELL_TOKEN}")
  }

  // constructs a new state
  def withMessage(message: String) =
    new State(root, wd, message)

  def withDir(wd: Directory) =
    new State(root, wd, message)

  def withNew(root: Directory, wd: Directory, message: String) =
    new State(root, wd, message)


}

object State {
  val SHELL_TOKEN = "$ "

  def apply(root: Directory, wd: Directory, message: String = "") =
    new State(root, wd, message)

}