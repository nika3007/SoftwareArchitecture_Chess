error id: file://<WORKSPACE>/model/Piece.scala:Knight.
file://<WORKSPACE>/model/Piece.scala
empty definition using pc, found symbol in pc: Knight.
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -PieceType.Knight.
	 -PieceType.Knight#
	 -PieceType.Knight().
	 -scala/Predef.PieceType.Knight.
	 -scala/Predef.PieceType.Knight#
	 -scala/Predef.PieceType.Knight().
offset: 626
uri: file://<WORKSPACE>/model/Piece.scala
text:
```scala
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
    case (PieceType.Kni@@ght, Color.Black) => "♞"
    case (PieceType.Pawn,   Color.Black) => "♟"
```


#### Short summary: 

empty definition using pc, found symbol in pc: Knight.