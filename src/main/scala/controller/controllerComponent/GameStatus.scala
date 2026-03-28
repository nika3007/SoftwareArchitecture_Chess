package controller.controllerComponent

import model.Color

enum GameStatus:
  case Idle
  case PieceSelected
  case Moved
  case Capture
  case Invalid
  case GameOver(winner: Color)

object GameStatus:
  def message(status: GameStatus): String = status match
    case PieceSelected    => "Piece selected. Choose destination."
    case Moved            => "Move made."
    case Capture          => "Piece captured!"
    case Invalid          => "Invalid move."
    case GameOver(winner) => s"Game over! ${winner} wins!"
    case _                => ""