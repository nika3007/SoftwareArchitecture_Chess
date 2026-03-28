package model

final case class Piece(pieceType: PieceType, color: Color):
  def symbol: String = (pieceType, color) match
    case (PieceType.King,   Color.White) => "♔"
    case (PieceType.Queen,  Color.White) => "♕"
    case (PieceType.Rook,   Color.White) => "♖"
    case (PieceType.Bishop, Color.White) => "♗"
    case (PieceType.Knight, Color.White) => "♘"
    case (PieceType.Pawn,   Color.White) => "♙"
    case (PieceType.King,   Color.Black) => "♚"
    case (PieceType.Queen,  Color.Black) => "♛"
    case (PieceType.Rook,   Color.Black) => "♜"
    case (PieceType.Bishop, Color.Black) => "♝"
    case (PieceType.Knight, Color.Black) => "♞"
    case (PieceType.Pawn,   Color.Black) => "♟"