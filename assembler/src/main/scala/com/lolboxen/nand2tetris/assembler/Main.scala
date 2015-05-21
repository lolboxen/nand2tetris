package com.lolboxen.nand2tetris.assembler

import java.io.File

import org.apache.commons.io.{FileUtils, FilenameUtils}

import scala.collection.JavaConverters._

/**
 * Created by trent ahrens on 5/20/15.
 */
object Main extends App {
  val input = args(0)
  val filenameWithoutExtention = FilenameUtils.removeExtension(input)
  val program: Seq[String] = FileUtils.readLines(new File(input)).asScala
  val whitespaceStripper = new WhitespaceStripper
  val symbolResolver = new SymbolResolver
  val hackCompiler = new HackCompiler
  val assembler = whitespaceStripper.apply _ andThen symbolResolver.apply andThen hackCompiler.apply
  val hack = assembler(program).mkString("\n")
  FileUtils.write(new File(filenameWithoutExtention + ".hack"), hack)
}
