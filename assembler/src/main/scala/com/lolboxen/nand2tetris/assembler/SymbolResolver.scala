package com.lolboxen.nand2tetris.assembler

import SymbolResolver._

import scala.collection.mutable

/**
 * Created by trent ahrens on 5/20/15.
 */

object SymbolResolver {
  def defaultSymbols: Map[String,Int] =
    List.tabulate(16)(x => "R%01d".format(x) -> x).toMap ++ Map(
      "SCREEN" -> 16384,
      "KBD" -> 24576,
      "SP" -> 0,
      "LCL" -> 1,
      "ARG" -> 2,
      "THIS" -> 3,
      "THAT" -> 4
    )

  val labelRegex = """\(([a-zA-Z0-9_$.]+)\)""".r
  val varRegex = """@([a-zA-Z][a-zA-Z0-9_$.]*)""".r
}

class SymbolResolver {
  def apply(input: Seq[String]) = replaceSymbols(stripLabelDeclarations(input), buildSymbolTable(input))

  def buildSymbolTable(input: Seq[String]): Map[String,Int] = {
    var pc = 0
    val symbols = mutable.Map.empty ++ defaultSymbols
    input.foreach {
      case labelRegex(label) => symbols += (label -> pc)
      case _ => pc += 1
    }

    var memLoc = 16
    input.foreach {
      case varRegex(name) =>
        symbols.get(name) match {
          case None =>
            symbols += (name -> memLoc)
            memLoc += 1
          case _ =>
        }
      case _ =>
    }

    symbols.toMap
  }

  def stripLabelDeclarations(input: Seq[String]): Seq[String] =
    input.filter {
      case labelRegex(_) => false
      case _ => true
    }

  def replaceSymbols(input: Seq[String], symbols: Map[String,Int]): Seq[String] = {
    input.map {
      case varRegex(name) => "@" + symbols(name)
      case instruction => instruction
    }
  }
}
