package model.boardComponent

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import model.*
import boardBaseImpl.BoardImpl

class BoardImplSpec extends AnyWordSpec with Matchers:

  val board    = ChessBoardFactory.initialBoard
  val boardImpl = BoardImpl(board)

  "A BoardImpl" should {

    "return the board" in {
      boardImpl.board shouldBe board
    }

    "return Left(Invalid) for invalid position" in {
      boardImpl.select(Position(-1, -1)) shouldBe Left(MoveResult.Invalid)
    }

    "return Right for valid selection" in {
      boardImpl.select(Position(6, 0)).isRight shouldBe true
    }

    "return false for isGameOver on initial board" in {
      boardImpl.isGameOver shouldBe false
    }
  }