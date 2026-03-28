import controller.controllerComponent.controllerBaseImpl.ControllerImpl
import model.ChessBoardFactory
import model.modelComponent.implModel.ChessGameImpl
import aview.ChessTui

object Chess:
  def main(args: Array[String]): Unit =
    val board      = ChessBoardFactory.initialBoard
    val game       = ChessGameImpl(board)
    val controller = ControllerImpl(game)
    val tui        = ChessTui(controller)
    tui.run()