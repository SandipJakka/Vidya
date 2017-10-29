package com.project.scala.filesystem

/**
  * Created by sandeep.jakka on 9/28/17.
  */

trait Command {
  def apply(state: State): State
}


object Command {
  def unknownCommand(name: String) =
    new Command {
      override def apply(state: State): State =
        state.withMessage(s"${name}   : [command not found]")
    }

  def ls() =
    new Command {
      private def list(root: Directory, currentDirectory: Directory): List[String] = {
        directoriesInCurrentDirectory(root, currentDirectory).map(_.name) ::: filesInCurrentDirectory(root, currentDirectory).map(_.name)
      }

      override def apply(state: State): State = {
        state.withMessage(s"Current dir fileContents : [${list(state.root, state.wd)}]")
      }
    }

  def directoriesInCurrentDirectory(root: Directory, currentDirectory: Directory): List[Directory] = {
    root.contents
      .filter(_.isInstanceOf[Directory])
      .filter(_.parentPath.equals(currentDirectory.name))
      .filterNot(_.name.equals(currentDirectory.name))
      .map(_.asInstanceOf[Directory])
  }

  def filesInCurrentDirectory(root: Directory, currentDirectory: Directory): List[File] = {
    val currentDirectoryInList = (root :: root.contents)
      .find(dirEntry => dirEntry.isInstanceOf[Directory] && dirEntry.name.equals(currentDirectory.name))
    currentDirectoryInList.get.asInstanceOf[Directory].contents.filter(_.isInstanceOf[File]).map(_.asInstanceOf[File])
  }

  def rm(name: String) = new Command {
    override def apply(state: State): State = {
      val filePresentPredicate = (dir: DirEntry) => (dir.parentPath.equals(state.wd.name) && dir.name.endsWith(name))
      val file = state.root.contents.find(filePresentPredicate)
      if (file.isEmpty) state.withMessage(s"File with name ${name} not present in Dir : [${state.wd.name}]") else {
        val newRoot = Directory.withContents(state.root.parentPath, state.root.name, state.root.contents.filter(dir => !filePresentPredicate(dir)))
        state.withNew(newRoot, state.wd, s"Deleted file with name ${name} in Dir : [${state.wd.name}]")
      }
    }
  }


  def cat(fileName: String) = new Command {
    require(!fileName.trim.isEmpty, "cat : <filename> is missing in the input")

    override def apply(state: State): State = {
      val filesInDir = filesInCurrentDirectory(state.root, state.wd)
      val contents = filesInDir.find(_.name.endsWith(fileName)).map(_.contents)
      state.withMessage(contents.getOrElse(s"No file with name : ${fileName} exists in dir : [${state.wd.name}]"))
    }
  }

  def mkdir(directoryName: String) = new Command {
    require(!directoryName.trim.isEmpty, "mkdir : <directory-name> is required format.")

    override def apply(state: State): State = {
      val fullPath = state.wd.name.concat(Directory.SEPARATOR).concat(directoryName)

      if (find(state.root, fullPath).isDefined) state.withMessage(s" Directory with name [${directoryName}] already exists .") else {
        val newEmptyDirectory = Directory.empty(state.wd.name, fullPath)
        val rootWithNewDirectory = Directory.withContents(state.root.parentPath, state.root.name, newEmptyDirectory :: state.root.contents)
        state.withNew(rootWithNewDirectory, state.wd, s" created Directory with name [${fullPath}]")
      }
    }

    def find(searchIn: Directory, search: String): Option[DirEntry] = {
      if (searchIn.name.equals(search)) Some(searchIn) else searchIn.contents.find(_.name.equals(search))
    }
  }

  def pwd() = new Command {
    override def apply(state: State): State = {
      state.withMessage(state.wd.name)
    }
  }

  def cd(toDirectory: String) = new Command {
    require(!toDirectory.trim.isEmpty, "cd : <directory-name> is required format.")

    override def apply(state: State): State = {
      toDirectory match {
        case "." => state
        case ".." =>
          val parentDirectoryPath = state.wd.parentPath
          changeDir(state, findDir(parentDirectoryPath, state.root), parentDirectoryPath)
        case _ =>
          val dirName = state.wd.name.concat(Directory.SEPARATOR).concat(toDirectory)
          changeDir(state, directoriesIn(state.wd.name, state.root).find(directory => directory.name.equals(dirName)), dirName)
      }
    }

    private def changeDir(state: State, directory: Option[Directory], dirName: String) = {
      if (directory.isEmpty) state.withMessage(s"Directory with name [${dirName}] not found") else {
        state.withDir(directory.get)
      }
    }

    private def directoriesIn(directory: String, startFrom: Directory): List[Directory] = {
      startFrom.contents.filter(_.isInstanceOf[Directory]).map(_.asInstanceOf[Directory]).filter(directory => !directory.parentPath.equals(directory))
    }

    private def findDir(searchForDirectoryWithName: String, from: Directory): Option[Directory] = {
      (from :: from.contents)
        .filter(_.isInstanceOf[Directory])
        .map(_.asInstanceOf[Directory])
        .find(dir => dir.name.equals(searchForDirectoryWithName))
    }
  }

  def echo(tokens: Array[String]) = new Command {

    object Operation extends Enumeration {
      val display, append, write = Value;
    }

    override def apply(state: State): State = {
      operations(tokens) match {
        case Operation.display => displayFile(tokens, state)
        case Operation.write => writeFile(tokens, state)
        case Operation.append => appendFile(tokens, state)
        case _ => state.withMessage(s"Unknown operation : ${tokens.mkString(" ")} with echo ")
      }
    }

    private def operations(tokens: Array[String]) = {
      if (tokens.contains(">"))
        Operation.write
      else if (tokens.contains(">>")) {
        Operation.append
      } else if (!(tokens.contains(">") && tokens.contains(">>"))) {
        Operation.display
      }
    }

    private def appendFile(tokens: Array[String], state: State): State = {
      val (index: Int, fileName: String) = filename(">>")
      val filePresent = fileInCurrentDirectory(fileName, state)
      if (filePresent.isEmpty) {
        state.withMessage(s"File with name ${tokens(1)} not present in Dir:[${state.wd.name}]")
      } else {
        val newFileContents = filePresent.get.asInstanceOf[File].contents.concat(fileContents(tokens, index))
        val file = create(state, fileName, newFileContents)
        write(state, file)
      }
    }

    private def writeFile(tokens: Array[String], state: State) = {
      val (index: Int, fileName: String) = filename(">")
      val contents = fileContents(tokens, index)
      val file: File = create(state, fileName, contents)
      write(state, file)
    }

    private def fileContents(tokens: Array[String], index: Int) = {
      tokens.toList.slice(1, index - 1).mkString(" ")
    }


    private def filename(operationType: String) = {
      val index = tokens.indexOf(operationType) + 1
      val fileName = tokens.apply(index)
      (index, fileName)
    }

    private def displayFile(tokens: Array[String], state: State) = {
      require(tokens.size == 2, "More tokens after echo. In case of Display only filename is relavent ")
      val filePresent = fileInCurrentDirectory(tokens(1), state)
      if (filePresent.isEmpty) {
        state.withMessage(s"File with name ${tokens(1)} not present in Dir:[${state.wd.name}]")
      } else {
        state.withMessage(filePresent.get.asInstanceOf[File].contents)
      }
    }

    private def fileInCurrentDirectory(filename: String, state: State): Option[DirEntry] = {
      (state.root :: state.root.contents)
        .filter(dir => dir.isInstanceOf[Directory] && dir.name.equals(state.wd.name))
        .flatMap(_.asInstanceOf[Directory].contents)
        .find(file => file.isInstanceOf[File] && file.name.endsWith(filename))
    }
  }

  def touch(filename: String) = new Command {
    require(!filename.trim.isEmpty, "touch requires a file-name as a second argument")

    override def apply(state: State): State = {
      val file = filesInCurrentDirectory(state.root, state.wd).find(_.name.endsWith(filename))
      if (file.isDefined) {
        state.withMessage(s"The file with name : ${filename} is already present ")
      } else {
        val newEmptyFile = create(state, filename, "")
        write(state, newEmptyFile)
      }
    }
  }

  def mv(dirEntry: String, toDir: String) = new Command {
    require(!(dirEntry.trim.isEmpty && toDir.trim.isEmpty), "mv <file-to-be-moved> <to-dir> is the format")

    override def apply(state: State): State = {
      val directories = directoriesInCurrentDirectory(state.root, state.wd)
      val files = filesInCurrentDirectory(state.root, state.wd)

      val isDirEntryAbsPath = dirEntry.contains(Directory.SEPARATOR)
      val directoryToMove = directories.find(dir => if (isDirEntryAbsPath) dir.name.equals(dirEntry) else dir.name.endsWith(dirEntry))
      val fileToMove = files.find(file => if (isDirEntryAbsPath) file.name.equals(dirEntry) else file.name.endsWith(dirEntry))
      val toDirectory = directories.find(dir => if (toDir.contains(Directory.SEPARATOR)) dir.name.equals(toDir) else dir.name.endsWith(toDir))

      if (directoryToMove.isDefined && toDirectory.isDefined) {
        val state1 = rm(dirEntry).apply(state)
        val dirName = if (isDirEntryAbsPath) {
          dirEntry.split("/").last
        } else {
          dirEntry
        }
        val directoryInNewPath = Directory.withContents(toDirectory.get.name, toDirectory.get.parentPath.concat(Directory.SEPARATOR).concat(dirName), directoryToMove.get.contents)
        val newRoot = Directory.withContents(state1.root.parentPath, state1.root.name, directoryInNewPath :: state1.root.contents)
        state1.withNew(newRoot, state.wd, s"Moved ${directoryToMove.get.name} to ${toDir}")
      }
      else if (fileToMove.isDefined && toDirectory.isDefined) {
        val state1 = rm(dirEntry).apply(state)
        val state2 = rm(toDir).apply(state1)
        val directoryWithFileAdded = Directory.withContents(toDirectory.get.parentPath, toDirectory.get.name, fileToMove.get :: toDirectory.get.contents)
        val newRoot = Directory.withContents(state2.root.parentPath, state2.root.name, directoryWithFileAdded :: state2.root.contents)
        state2.withNew(newRoot, state2.wd, s"Moved ${fileToMove.get.name} to ${toDir}")
      } else {
        state.withMessage("can't move..")
      }

    }
  }

  def from(input: String): Command = {
    val tokens = input.trim.split(" ")
    tokens(0) match {
      case "ls" => ls
      case "cat" => cat(tokens(1))
      case "mkdir" => mkdir(tokens(1))
      case "cd" => cd(tokens(1))
      case "pwd" => pwd
      case "rm" => rm(tokens(1))
      case "echo" => echo(tokens)
      case "touch" => touch(tokens(1))
      case "mv" => mv(tokens(1), tokens(2))
      case _ => unknownCommand(tokens(0))
    }
  }

  private def create(state: State, fileName: String, fileContents: String) = {
    File.newFileWithContents(state.wd.name, fileName, fileContents)
  }

  private def write(state: State, file: File) = {
    if (state.root.name.equals(state.wd.name)) {
      state.withNew(Directory.withContents(state.root.parentPath, state.root.name, replaceFileInDirectory(state.root.contents, file)), state.wd,
        s"File with name :${file.name} is created in root")
    } else {
      val newRootContents = (state.root :: state.root.contents)
        .map(dirEntry =>
          if (dirEntry.isInstanceOf[Directory] && dirEntry.name.equals(state.wd.name)) {
            Directory.withContents(dirEntry.parentPath, dirEntry.name, replaceFileInDirectory(dirEntry.asInstanceOf[Directory].contents, file))
          } else {
            dirEntry
          }
        )
      state.withNew(Directory.withContents(state.root.parentPath, state.root.name, newRootContents), state.wd,
        s"File with name : ${file.name} created in Dir : [${state.wd.name}] ")
    }
  }

  private def replaceFileInDirectory(dirContents: List[DirEntry], file: File) = {
    file :: dirContents.filterNot(dir => dir.isInstanceOf[File] && dir.name.endsWith(file.name))
  }
}