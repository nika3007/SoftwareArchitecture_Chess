package model

object ChessBoardFactory:

  def initialBoard: ChessBoard =
    ChessBoard(cells = buildCells)

  private def buildCells: Vector[Vector[Option[Piece]]] =
    val empty = Vector.fill(8)(Vector.fill(8)(Option.empty[Piece]))

    def place(cells: Vector[Vector[Option[Piece]]], row: Int, col: Int, piece: Piece) =
      cells.updated(row, cells(row).updated(col, Some(piece)))

    val backRank = Vector(
      PieceType.Rook, PieceType.Knight, PieceType.Bishop, PieceType.Queen,
      PieceType.King, PieceType.Bishop, PieceType.Knight, PieceType.Rook
    )

    backRank.zipWithIndex
      .foldLeft(empty) { case (cells, (pt, col)) =>
        val withBlack = place(cells, 0, col, Piece(pt, Color.Black))
        val withWhite = place(withBlack, 7, col, Piece(pt, Color.White))
        withWhite
      }
      .pipe(cells =>
        (0 until 8).foldLeft(cells) { (cells, col) =>
          val withBlack = place(cells, 1, col, Piece(PieceType.Pawn, Color.Black))
          val withWhite = place(withBlack, 6, col, Piece(PieceType.Pawn, Color.White))
          withWhite
        }
      )