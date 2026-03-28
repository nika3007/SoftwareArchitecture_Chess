package model

enum MoveResult:
  case Selected
  case Deselected
  case Moved
  case Capture
  case Invalid
  case GameOver(winner: Color)