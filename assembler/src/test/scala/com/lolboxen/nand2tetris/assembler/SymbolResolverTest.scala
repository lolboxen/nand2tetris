package com.lolboxen.nand2tetris.assembler

import com.lolboxen.test.UnitSpec

/**
 * Created by trent ahrens on 5/20/15.
 */
class SymbolResolverTest extends UnitSpec {

  val sut = new SymbolResolver

  "lables" must "be replaced by @ references" in {
    val input = List(
      "@LOOP",
      "(LOOP)",
      "A",
      "(END)",
      "A",
      "@END",
      "@test_test.test$test",
      "(test_test.test$test)",
      "A"
    )
    val actual = sut(input)
    val expected = List(
      "@1",
      "A",
      "A",
      "@2",
      "@5",
      "A"
    )
    actual shouldBe expected
  }

  "variables" must "be replaced generated memory address" in {
    val input = List(
      "@a",
      "@b",
      "@a",
      "@b",
      "@R0",
      "@R15",
      "@SCREEN",
      "@KBD",
      "@test_test$test.test"
    )
    val actual = sut(input)
    val expected = List(
      "@16",
      "@17",
      "@16",
      "@17",
      "@0",
      "@15",
      "@16384",
      "@24576",
      "@18"
    )
    actual shouldBe expected
  }

  "variables and lables" must "not get addresses mixed up" in {
    val input = List(
      "@a",
      "(LOOP)",
      "@LOOP"
    )
    val actual = sut(input)
    val expected = List(
      "@16",
      "@1"
    )
    actual shouldBe expected
  }
}
