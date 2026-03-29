package controller.controllerComponent.controllerBaseImpl

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import model.ChessBoard
import model.ChessBoardFactory
import model.Color
import model.Position
import model.MoveResult
import model.GameMemento
import model.modelComponent.implModel.ChessGameImpl
import controller.controllerComponent.GameStatus
import controller.controllerComponent.controllerBaseImpl.ControllerImpl

class ControllerImplSpec extends AnyWordSpec with Matchers:

  def freshController =
    val board = ChessBoardFactory.initialBoard
    val game  = ChessGameImpl(board)
    ControllerImpl(game)

  "A ControllerImpl" should {

    "return initial board" in {
      freshController.board shouldBe ChessBoardFactory.initialBoard
    }

    "return White as initial current player" in {
      freshController.currentPlayer shouldBe Color.White
    }

    "not be game over initially" in {
      freshController.isGameOver shouldBe false
    }

    "have no winner initially" in {
      freshController.winner shouldBe None
    }

    "have Idle as initial game status" in {
      freshController.gameStatus shouldBe GameStatus.Idle
    }

    "return false for empty input" in {
      freshController.processInput("") shouldBe false
    }

    "return false for invalid input" in {
      freshController.processInput("xyz") shouldBe false
    }

    "return true for valid position input" in {
      freshController.processInput("a2") shouldBe true
    }

    "update game status after valid selection" in {
      val controller = freshController
      controller.processInput("a2")
      controller.gameStatus shouldBe GameStatus.PieceSelected  // change to Moved
}

    "update game status to Invalid for invalid move" in {
      val controller = freshController
      controller.processInput("a5")
      controller.gameStatus shouldBe GameStatus.Invalid
    }

    "undo last move" in {
      val controller = freshController
      controller.processInput("a2")
      controller.undo()
      controller.gameStatus shouldBe GameStatus.Idle
    }

    "redo after undo" in {
      val controller = freshController
      controller.processInput("a2")
      controller.undo()
      controller.redo()
      controller.gameStatus shouldBe GameStatus.PieceSelected  // change to Moved
}
  }