package controller.controllerComponent.controllerBaseImpl

import controller.controllerComponent.Command
import controller.controllerComponent.GameStatus
import model.{ChessBoard, Position}

class MoveCommand(controller: ControllerImpl, pos: Position) extends Command:

  private var beforeBoard: ChessBoard = controller.board
  private var beforeStatus: GameStatus = controller.gameStatus

  override def doStep(): Unit =
    beforeBoard = controller.board
    beforeStatus = controller.gameStatus
    controller.handleSelect(pos)

  override def undoStep(): Unit =
    controller.restoreBoard(beforeBoard, beforeStatus)

  override def redoStep(): Unit =
    controller.handleSelect(pos)