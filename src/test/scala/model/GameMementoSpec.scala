package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class GameMementoSpec extends AnyWordSpec with Matchers:

  "A GameMemento" should {

    "store the board" in {
      val board = ChessBoardFactory.initialBoard
      val memento = GameMemento(board)
      memento.board shouldBe board
    }

    "be equal to another memento with the same board" in {
      val board = ChessBoardFactory.initialBoard
      GameMemento(board) shouldBe GameMemento(board)
    }

    "not be equal to a memento with a different board" in {
      val board1 = ChessBoardFactory.initialBoard
      val board2 = board1.select(Position(6, 0)) match
        case Right(b) => b
        case Left(_)  => board1
      GameMemento(board1) should not be GameMemento(board2)
    }
  }