package aview

import controller.controllerComponent.{ControllerAPI, GameStatus}
import util.Observer
import model.{Position, Color}
import scala.io.StdIn.readLine

class ChessTui(val controller: ControllerAPI) extends Observer:

  controller.add(this)

  def run(): Unit =
    println("Welcome to Chess!")
    println("Enter moves as: a2 (select) then a4 (destination)")
    println("Type 'u' to undo, 'r' to redo, 'q' to quit")
    println()
    printBoard()

    while !controller.isGameOver do
      println(s"${controller.currentPlayer}'s turn:")
      val input = readLine().trim.toLowerCase

      input match
        case "q" | "quit" => System.exit(0)
        case "u" | "undo" => controller.undo()
        case "r" | "redo" => controller.redo()
        case _            => controller.processInput(input)

  override def update: Boolean =
    printBoard()
    val msg = GameStatus.message(controller.gameStatus)
    if msg.nonEmpty then println(msg)
    if controller.isGameOver then
      controller.winner.foreach(w => println(s"$w wins! GG!"))
    true

  private def printBoard(): Unit =
    println()
    println("  a b c d e f g h")
    for row <- 0 until 8 do
      print(s"${8 - row} ")
      for col <- 0 until 8 do
        val symbol = controller.board.cells(row)(col)
          .map(_.symbol)
          .getOrElse(if (row + col) % 2 == 0 then "□" else "■")
        print(s"$symbol ")
      println(s"${8 - row}")
    println("  a b c d e f g h")
    println()