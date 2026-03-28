package controller

import controller.controllerComponent.ControllerAPI
import controller.controllerComponent.controllerBaseImpl.ControllerImpl
import model.modelComponent.ChessGameAPI

object Controller:
  def apply(game: ChessGameAPI): ControllerAPI =
    new ControllerImpl(game)