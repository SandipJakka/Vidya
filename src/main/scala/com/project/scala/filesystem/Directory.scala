package com.project.scala.filesystem

/**
  * Created by sandeep.jakka on 9/28/17.
  */

abstract class DirEntry(val parentPath: String, val name: String) {}

class Directory(override val parentPath: String,
                override val name: String,
                val contents: List[DirEntry]) extends DirEntry(parentPath, name) {

}

object Directory {
  val SEPARATOR = "/"
  val ROOT_PATH = "/"

  def newRoot: Directory =
    Directory.empty("", "")

  def empty(parentPath: String, name: String) =
    new Directory(parentPath, name, List())

  def withContents(parentPath: String, name: String, contents: List[DirEntry]) =
    new Directory(parentPath, name, contents)

}