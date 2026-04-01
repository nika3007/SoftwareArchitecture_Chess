import controller.controllerComponent.controllerBaseImpl.ControllerImpl
import model.ChessBoardFactory
import model.modelComponent.implModel.ChessGameImpl
import aview.ChessTui
import aview.gui.GUI
import scala.io.StdIn.readLine

object Chess:
  def main(args: Array[String]): Unit =
    val board      = ChessBoardFactory.initialBoard
    val game       = ChessGameImpl(board)
    val controller = ControllerImpl(game)

    println("Choose mode: 1) TUI  2) GUI  3) Both")
    val mode = readLine().trim match
      case "2" => "gui"
      case "3" => "both"
      case _   => "tui"

    mode match
      case "gui" =>
        GUI.launch(controller)

      case "both" =>
        new Thread(() =>
          GUI.launch(controller)
        ).start()
        ChessTui(controller).run()

      case _ =>
        ChessTui(controller).run()
