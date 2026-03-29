package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class PieceSpec extends AnyWordSpec with Matchers:

  "A Piece" should {

    "have correct symbol for white king" in {
      Piece(PieceType.King, Color.White).symbol shouldBe "♔"
    }

    "have correct symbol for white queen" in {
      Piece(PieceType.Queen, Color.White).symbol shouldBe "♕"
    }

    "have correct symbol for white rook" in {
      Piece(PieceType.Rook, Color.White).symbol shouldBe "♖"
    }

    "have correct symbol for white bishop" in {
      Piece(PieceType.Bishop, Color.White).symbol shouldBe "♗"
    }

    "have correct symbol for white knight" in {
      Piece(PieceType.Knight, Color.White).symbol shouldBe "♘"
    }

    "have correct symbol for white pawn" in {
      Piece(PieceType.Pawn, Color.White).symbol shouldBe "♙"
    }

    "have correct symbol for black king" in {
      Piece(PieceType.King, Color.Black).symbol shouldBe "♚"
    }

    "have correct symbol for black queen" in {
      Piece(PieceType.Queen, Color.Black).symbol shouldBe "♛"
    }

    "have correct symbol for black rook" in {
      Piece(PieceType.Rook, Color.Black).symbol shouldBe "♜"
    }

    "have correct symbol for black bishop" in {
      Piece(PieceType.Bishop, Color.Black).symbol shouldBe "♝"
    }

    "have correct symbol for black knight" in {
      Piece(PieceType.Knight, Color.Black).symbol shouldBe "♞"
    }

    "have correct symbol for black pawn" in {
      Piece(PieceType.Pawn, Color.Black).symbol shouldBe "♟"
    }

    "be equal to another piece with same type and color" in {
      Piece(PieceType.King, Color.White) shouldBe Piece(PieceType.King, Color.White)
    }

    "not be equal to a piece with different type" in {
      Piece(PieceType.King, Color.White) should not be Piece(PieceType.Queen, Color.White)
    }

    "not be equal to a piece with different color" in {
      Piece(PieceType.King, Color.White) should not be Piece(PieceType.King, Color.Black)
    }
  }