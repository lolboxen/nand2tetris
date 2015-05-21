package com.lolboxen.nand2tetris.assembler

import com.lolboxen.test.UnitSpec
import org.apache.commons.io.IOUtils

/**
 * Created by trent ahrens on 5/20/15.
 */
class HackCompilerTest extends UnitSpec {

  val sut = new HackCompiler

  it must "output correct binary code" in {
    val input = IOUtils.toString(getClass.getResourceAsStream("/test.asm")).lines.toSeq
    val actual = sut(input)
    val expected = IOUtils.toString(getClass.getResourceAsStream("/test.hack")).lines.toSeq
    actual shouldBe expected
  }
}
