package model

final case class Position(row: Int, col: Int):
  def isValid: Boolean = row >= 0 && row < 8 && col >= 0 && col < 8