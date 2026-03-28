package model.modelComponent

import model.{ChessBoard, Color, GameMemento, Position, MoveResult}

trait ChessGameAPI:
  def board: ChessBoard
  def currentPlayer: Color
  def isGameOver: Boolean
  def winner: Option[Color]
  def select(pos: Position): (ChessGameAPI, MoveResult)
  def save(): GameMemento
  def restore(m: GameMemento): ChessGameAPI