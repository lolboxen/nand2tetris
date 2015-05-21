package com.lolboxen.nand2tetris.assembler

import com.lolboxen.test.UnitSpec

/**
 * Created by trent ahrens on 5/20/15.
 */
class WhitespaceStripperTest extends UnitSpec {

  val sut = new WhitespaceStripper

  "comments and whitespace" must "be removed" in {
    val input = List(
      "// some comment",
      "0;JMP // another comment",
      "      ", // spaces
      "     ", // tabs
      "  0 ; JMP",
      "  @100",
      "  D = A +   1"
    )
    val actual = sut(input)
    val expected = List(
      "0;JMP",
      "0;JMP",
      "@100",
      "D=A+1"
    )
    actual shouldBe expected
  }
}
