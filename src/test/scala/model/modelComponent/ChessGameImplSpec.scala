package model.modelComponent

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import model.*
import implModel.ChessGameImpl

class ChessGameImplSpec extends AnyWordSpec with Matchers:

  val board = ChessBoardFactory.initialBoard
  val game  = ChessGameImpl(board)

  "A ChessGameImpl" should {

    "return the correct current player" in {
      game.currentPlayer shouldBe Color.White
    }

    "not be game over initially" in {
      game.isGameOver shouldBe false
    }

    "have no winner initially" in {
      game.winner shouldBe None
    }

    "return Right when selecting a valid piece" in {
      val (_, result) = game.select(Position(6, 0))
      result.isRight shouldBe true
    }

    "return Left when selecting an invalid position" in {
      val (_, result) = game.select(Position(-1, -1))
      result shouldBe Left(MoveResult.Invalid)
    }

    "return updated game after valid selection" in {
      val (newGame, result) = game.select(Position(6, 0))
      result.isRight shouldBe true
      newGame.board.selectedPosition shouldBe Some(Position(6, 0))
    }

    "save and restore board state" in {
      val memento     = game.save()
      val (newGame, _) = game.select(Position(6, 0))
      val restored    = newGame.restore(memento)
      restored.board shouldBe board
    }
  }