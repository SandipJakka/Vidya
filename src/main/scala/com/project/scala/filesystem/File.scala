package com.project.scala.filesystem

/**
  * Created by sandeep.jakka on 9/28/17.
  */
class File(override val parentPath: String,
           override val name: String,
           val contents: String) extends DirEntry(parentPath, name) {

}

object File {

  def newFile: File =
    File.empty("", "")

  def newFile(name: String) =
    File.empty("", name)

  def empty(parentPath: String, name: String) =
    new File(parentPath, name, "")

  def newFileWithContents(parentPath: String, name: String, contents: String) =
    new File(parentPath, name, contents)
}