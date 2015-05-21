// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input. 
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel. When no key is pressed, the
// program clears the screen, i.e. writes "white" in every pixel.

// Put your code here.

  @color
  M=0

  @SCREEN
  D=A
  @8192
  D=D+A
  @screenEnd
  M=D

(GETINPUT)
  @KBD
  D=M

  @SETBLACK
  D;JNE
  @color
  M=0

  @PAINT
  0;JMP

(SETBLACK)
  @color
  M=-1

(PAINT)
  @SCREEN
  D=A
  @addr
  M=D

(LOOP)
// set color
  @color
  D=M
  @addr
  A=M
  M=D

  @addr
  D=M
  D=D+1
  M=D
  @screenEnd
  D=M
  @addr
  D=D-M
  @GETINPUT
  D;JEQ

  @LOOP
  0;JMP