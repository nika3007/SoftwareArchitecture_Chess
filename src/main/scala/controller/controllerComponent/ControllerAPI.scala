package controller.controllerComponent

import util.Observer
import model.{ChessBoard, Color, Position}
import model.modelComponent.ChessGameAPI

trait ControllerAPI:
  def add(o: Observer): Unit
  def remove(o: Observer): Unit
  def notifyObservers: Unit

  def board: ChessBoard
  def currentPlayer: Color
  def gameStatus: GameStatus
  def isGameOver: Boolean
  def winner: Option[Color]

  def processInput(input: String): Boolean
  def select(pos: Position): Unit
  def undo(): Unit
  def redo(): Unit

  def game: ChessGameAPI