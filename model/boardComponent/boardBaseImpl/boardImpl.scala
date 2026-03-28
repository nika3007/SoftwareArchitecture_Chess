package model.boardComponent.boardBaseImpl

import model.boardComponent.BoardAPI
import model.{ChessBoard, Position, MoveResult}

final class BoardImpl(val board: ChessBoard) extends BoardAPI:

  override def select(pos: Position): (ChessBoard, MoveResult) =
    board.select(pos)

  override def isGameOver: Boolean =
    board.gameOver