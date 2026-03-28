package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class ChessBoardSpec extends AnyWordSpec with Matchers:

  val board = ChessBoardFactory.initialBoard

  "A ChessBoard" should {

    "return Left(Invalid) when selecting an invalid position" in {
      val result = board.select(Position(-1, -1))
      result shouldBe Left(MoveResult.Invalid)
    }

    "return Left(Invalid) when selecting an empty square" in {
      val result = board.select(Position(4, 4))
      result shouldBe Left(MoveResult.Invalid)
    }

    "return Left(Invalid) when selecting an opponent's piece" in {
      val result = board.select(Position(0, 0))
      result shouldBe Left(MoveResult.Invalid)
    }

    "return Right when selecting a valid own piece" in {
      val result = board.select(Position(6, 0))
      result.isRight shouldBe true
    }

    "return Right with updated selectedPosition after valid selection" in {
      val result = board.select(Position(6, 0))
      result match
        case Right(newBoard) => newBoard.selectedPosition shouldBe Some(Position(6, 0))
        case Left(_)         => fail("Expected Right but got Left")
    }

    "return Left(Invalid) when moving to an invalid position" in {
      val result = board.move(Position(6, 0), Position(-1, -1))
      result shouldBe Left(MoveResult.Invalid)
    }

    "return Right when making a valid pawn move" in {
      val withSelected = board.select(Position(6, 0))
      withSelected match
        case Right(selectedBoard) =>
          val moveResult = selectedBoard.move(Position(6, 0), Position(5, 0))
          moveResult.isRight shouldBe true
        case Left(_) => fail("Expected Right but got Left")
    }

    "switch current player after a valid move" in {
      val withSelected = board.select(Position(6, 0))
      withSelected match
        case Right(selectedBoard) =>
          val moveResult = selectedBoard.move(Position(6, 0), Position(5, 0))
          moveResult match
            case Right(newBoard) => newBoard.currentPlayer shouldBe Color.Black
            case Left(_)         => fail("Expected Right but got Left")
        case Left(_) => fail("Expected Right but got Left")
    }

    "return Left(Invalid) when moving to a square occupied by own piece" in {
      val result = board.move(Position(7, 0), Position(7, 1))
      result shouldBe Left(MoveResult.Invalid)
    }

    "have no winner initially" in {
      board.winner shouldBe None
    }

    "not be game over initially" in {
      board.gameOver shouldBe false
    }

    "return correct piece at valid position" in {
      board.pieceAt(Position(0, 0)) shouldBe Some(Piece(PieceType.Rook, Color.Black))
    }

    "return None for empty square" in {
      board.pieceAt(Position(4, 4)) shouldBe None
    }

    "return None for invalid position" in {
      board.pieceAt(Position(-1, -1)) shouldBe None
    }

    "deselect when selecting the same position twice" in {
      val withSelected = board.select(Position(6, 0))
      withSelected match
        case Right(selectedBoard) =>
          val deselected = selectedBoard.select(Position(6, 0))
          deselected match
            case Right(newBoard) => newBoard.selectedPosition shouldBe None
            case Left(_)         => fail("Expected Right but got Left")
        case Left(_) => fail("Expected Right but got Left")
    }
  }