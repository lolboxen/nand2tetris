package com.lolboxen.nand2tetris.assembler

/**
 * Created by trent ahrens on 5/20/15.
 */
class WhitespaceStripper {
  def apply(input: Seq[String]): Seq[String] =
    input.map(strip).filter(s => s.nonEmpty)

  def strip(input: String): String = (stripComments _ andThen stripWhitespace)(input)

  def stripComments(input: String): String = input.substring(0, indexOfDoubleSlashOrEnd(input))

  def stripWhitespace(input: String): String = input.replaceAll(" ","").replaceAll("\t","")

  def indexOfDoubleSlashOrEnd(input: String): Int = input.indexOf("//") match {
    case -1 => input.length
    case x => x
  }
}
