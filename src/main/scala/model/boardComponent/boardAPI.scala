package model.boardComponent

import model.{ChessBoard, Position, MoveResult}

trait BoardAPI:
  def board: ChessBoard
  def select(pos: Position): Either[MoveResult, ChessBoard]
  def isGameOver: Boolean