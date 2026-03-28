package model.boardComponent

import model.ChessBoard
import model.boardComponent.boardBaseImpl.BoardImpl

object BoardComponent:
  def apply(board: ChessBoard): BoardAPI =
    new BoardImpl(board)