package com.lolboxen.nand2tetris.assembler

import HackCompiler._

/**
 * Created by trent ahrens on 5/20/15.
 */

object HackCompiler {
  val aInstruction = "@([0-9]+)".r
  val cInstruction = "(([AMD]{0,3})=)?([AMD|&01!+-]{0,3});?(JGT|JEQ|JGE|JLT|JNE|JLE|JMP)?".r

  val destMapping: Map[String,String] = Map(
    "" ->  "000",
    "M" ->   "001",
    "D" ->   "010",
    "MD" ->  "011",
    "A" ->   "100",
    "AM" ->  "101",
    "AD" ->  "110",
    "AMD" -> "111"
  )

  val jumpMapping: Map[String,String] = Map(
    "" ->  "000",
    "JGT" -> "001",
    "JEQ" -> "010",
    "JGE" -> "011",
    "JLT" -> "100",
    "JNE" -> "101",
    "JLE" -> "110",
    "JMP" -> "111"
  )

  val compMapping: Map[String,String] = Map(
    "0" ->    "0101010",
    "1" ->    "0111111",
    "-1" ->   "0111010",
    "D" ->    "0001100",
    "A" ->    "0110000",
    "!D" ->   "0001101",
    "!A" ->   "0110001",
    "-D" ->   "0001111",
    "-A" ->   "0110011",
    "D+1" ->  "0011111",
    "A+1" ->  "0110111",
    "D-1" ->  "0001110",
    "A-1" ->  "0110010",
    "D+A" ->  "0000010",
    "D-A" ->  "0010001",
    "A-D" ->  "0000111",
    "D&A" ->  "0000000",
    "D|A" ->  "0010101",
    "M" ->    "1110000",
    "!M" ->   "1110001",
    "-M" ->   "1110011",
    "M+1" ->  "1110111",
    "M-1" ->  "1110010",
    "D+M" ->  "1000010",
    "D-M" ->  "1010011",
    "M-D" ->  "1000111",
    "D&M" ->  "1000000",
    "D|M" ->  "1010101"
  )
}

class HackCompiler {
  def apply(input: Seq[String]): Seq[String] =
    input.map {
      case aInstruction(address) => "0%15s".format(address.toInt.toBinaryString).replaceAll(" ", "0")
      case cInstruction(_,null,comp,null) => "111%s%s%s".format(compMapping(comp),destMapping(""),jumpMapping(""))
      case cInstruction(_,dest,comp,null) => "111%s%s%s".format(compMapping(comp),destMapping(dest),jumpMapping(""))
      case cInstruction(_,null,comp,jump) => "111%s%s%s".format(compMapping(comp),destMapping(""),jumpMapping(jump))
      case cInstruction(_,dest,comp,jump) => "111%s%s%s".format(compMapping(comp),destMapping(dest),jumpMapping(jump))
      case any => any
    }
}
