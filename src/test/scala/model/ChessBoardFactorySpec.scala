package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class ChessBoardFactorySpec extends AnyWordSpec with Matchers:

  val board = ChessBoardFactory.initialBoard

  "ChessBoardFactory" should {

    "create a board with 8 rows" in {
      board.cells.size shouldBe 8
    }

    "create a board with 8 columns per row" in {
      board.cells.foreach(_.size shouldBe 8)
    }

    "place white rooks at correct positions" in {
      board.pieceAt(Position(7, 0)) shouldBe Some(Piece(PieceType.Rook, Color.White))
      board.pieceAt(Position(7, 7)) shouldBe Some(Piece(PieceType.Rook, Color.White))
    }

    "place white knights at correct positions" in {
      board.pieceAt(Position(7, 1)) shouldBe Some(Piece(PieceType.Knight, Color.White))
      board.pieceAt(Position(7, 6)) shouldBe Some(Piece(PieceType.Knight, Color.White))
    }

    "place white bishops at correct positions" in {
      board.pieceAt(Position(7, 2)) shouldBe Some(Piece(PieceType.Bishop, Color.White))
      board.pieceAt(Position(7, 5)) shouldBe Some(Piece(PieceType.Bishop, Color.White))
    }

    "place white queen at correct position" in {
      board.pieceAt(Position(7, 3)) shouldBe Some(Piece(PieceType.Queen, Color.White))
    }

    "place white king at correct position" in {
      board.pieceAt(Position(7, 4)) shouldBe Some(Piece(PieceType.King, Color.White))
    }

    "place white pawns at correct positions" in {
      for col <- 0 until 8 do
        board.pieceAt(Position(6, col)) shouldBe Some(Piece(PieceType.Pawn, Color.White))
    }

    "place black rooks at correct positions" in {
      board.pieceAt(Position(0, 0)) shouldBe Some(Piece(PieceType.Rook, Color.Black))
      board.pieceAt(Position(0, 7)) shouldBe Some(Piece(PieceType.Rook, Color.Black))
    }

    "place black knights at correct positions" in {
      board.pieceAt(Position(0, 1)) shouldBe Some(Piece(PieceType.Knight, Color.Black))
      board.pieceAt(Position(0, 6)) shouldBe Some(Piece(PieceType.Knight, Color.Black))
    }

    "place black bishops at correct positions" in {
      board.pieceAt(Position(0, 2)) shouldBe Some(Piece(PieceType.Bishop, Color.Black))
      board.pieceAt(Position(0, 5)) shouldBe Some(Piece(PieceType.Bishop, Color.Black))
    }

    "place black queen at correct position" in {
      board.pieceAt(Position(0, 3)) shouldBe Some(Piece(PieceType.Queen, Color.Black))
    }

    "place black king at correct position" in {
      board.pieceAt(Position(0, 4)) shouldBe Some(Piece(PieceType.King, Color.Black))
    }

    "place black pawns at correct positions" in {
      for col <- 0 until 8 do
        board.pieceAt(Position(1, col)) shouldBe Some(Piece(PieceType.Pawn, Color.Black))
    }

    "leave middle rows empty" in {
      for
        row <- 2 until 6
        col <- 0 until 8
      do
        board.pieceAt(Position(row, col)) shouldBe None
    }

    "start with white as current player" in {
      board.currentPlayer shouldBe Color.White
    }

    "start with no selection" in {
      board.selectedPosition shouldBe None
    }

    "start with game not over" in {
      board.gameOver shouldBe false
    }

    "start with no winner" in {
      board.winner shouldBe None
    }
  }