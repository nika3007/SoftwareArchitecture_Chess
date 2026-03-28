package model

import model.MoveResult

final case class ChessBoard(
  cells: Vector[Vector[Option[Piece]]],
  currentPlayer: Color = Color.White,
  selectedPosition: Option[Position] = None,
  gameOver: Boolean = false,
  winner: Option[Color] = None
):

  def pieceAt(pos: Position): Option[Piece] =
    if pos.isValid then cells(pos.row)(pos.col) else None

  def select(pos: Position): (ChessBoard, MoveResult) =
    if !pos.isValid then (this, MoveResult.Invalid)
    else selectedPosition match
      case None =>
        pieceAt(pos) match
          case Some(piece) if piece.color == currentPlayer =>
            (copy(selectedPosition = Some(pos)), MoveResult.Selected)
          case _ =>
            (this, MoveResult.Invalid)

      case Some(from) =>
        if from == pos then
          (copy(selectedPosition = None), MoveResult.Deselected)
        else
          pieceAt(pos) match
            case Some(piece) if piece.color == currentPlayer =>
              (copy(selectedPosition = Some(pos)), MoveResult.Selected)
            case _ =>
              move(from, pos)

  def move(from: Position, to: Position): (ChessBoard, MoveResult) =
    val piece = pieceAt(from)
    piece match
      case None => (this, MoveResult.Invalid)
      case Some(p) =>
        if !isValidMove(p, from, to) then (this, MoveResult.Invalid)
        else
          val capturedPiece = pieceAt(to)
          val newCells = cells
            .updated(from.row, cells(from.row).updated(from.col, None))
            .updated(to.row, cells(to.row).updated(to.col, Some(p)))

          val isCapture = capturedPiece.isDefined
          val isKingCaptured = capturedPiece.exists(_.pieceType == PieceType.King)

          val next = copy(
            cells = newCells,
            currentPlayer = currentPlayer.opposite,
            selectedPosition = None,
            gameOver = isKingCaptured,
            winner = if isKingCaptured then Some(currentPlayer) else None
          )
          if isKingCaptured then (next, MoveResult.GameOver(currentPlayer))
          else if isCapture then (next, MoveResult.Capture)
          else (next, MoveResult.Moved)

  def isValidMove(piece: Piece, from: Position, to: Position): Boolean =
    if !to.isValid then return false
    if pieceAt(to).exists(_.color == piece.color) then return false

    val dr = to.row - from.row
    val dc = to.col - from.col

    piece.pieceType match
      case PieceType.King =>
        math.abs(dr) <= 1 && math.abs(dc) <= 1 && !(dr == 0 && dc == 0)

      case PieceType.Queen =>
        isStraightPath(from, to) || isDiagonalPath(from, to)

      case PieceType.Rook =>
        isStraightPath(from, to)

      case PieceType.Bishop =>
        isDiagonalPath(from, to)

      case PieceType.Knight =>
        (math.abs(dr) == 2 && math.abs(dc) == 1) ||
        (math.abs(dr) == 1 && math.abs(dc) == 2)

      case PieceType.Pawn =>
        val direction = if piece.color == Color.White then -1 else 1
        val startRow  = if piece.color == Color.White then 6 else 1

        if dc == 0 && dr == direction && pieceAt(to).isEmpty then true
        else if dc == 0 && dr == 2 * direction
          && from.row == startRow
          && pieceAt(to).isEmpty
          && pieceAt(Position(from.row + direction, from.col)).isEmpty then true
        else if math.abs(dc) == 1 && dr == direction && pieceAt(to).isDefined then true
        else false

  private def isStraightPath(from: Position, to: Position): Boolean =
    val dr = to.row - from.row
    val dc = to.col - from.col
    if dr != 0 && dc != 0 then return false
    val stepR = if dr == 0 then 0 else dr / math.abs(dr)
    val stepC = if dc == 0 then 0 else dc / math.abs(dc)
    LazyList.iterate(Position(from.row + stepR, from.col + stepC))(p =>
      Position(p.row + stepR, p.col + stepC)
    )
    .takeWhile(_ != to)
    .forall(p => pieceAt(p).isEmpty)

  private def isDiagonalPath(from: Position, to: Position): Boolean =
    val dr = to.row - from.row
    val dc = to.col - from.col
    if math.abs(dr) != math.abs(dc) then return false
    val stepR = dr / math.abs(dr)
    val stepC = dc / math.abs(dc)
    LazyList.iterate(Position(from.row + stepR, from.col + stepC))(p =>
      Position(p.row + stepR, p.col + stepC)
    )
    .takeWhile(_ != to)
    .forall(p => pieceAt(p).isEmpty)