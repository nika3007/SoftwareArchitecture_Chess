package model.modelComponent.implModel

import model.*
import model.modelComponent.ChessGameAPI

final class ChessGameImpl(
  val board: ChessBoard
) extends ChessGameAPI:

  override def currentPlayer: Color = board.currentPlayer
  override def isGameOver: Boolean  = board.gameOver
  override def winner: Option[Color] = board.winner

  override def select(pos: Position): (ChessGameImpl, Either[MoveResult, ChessBoard]) =
    val result = board.select(pos)
    result match
      case Right(nextBoard) => (copy(board = nextBoard), result)
      case Left(_)          => (this, result)

  override def save(): GameMemento =
    GameMemento(board)

  override def restore(m: GameMemento): ChessGameImpl =
    copy(board = m.board)

  private def copy(board: ChessBoard = this.board): ChessGameImpl =
    ChessGameImpl(board)