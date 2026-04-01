package aview.gui

import scalafx.application.{JFXApp3, Platform}
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.layout.BorderPane
import controller.controllerComponent.{ControllerAPI, GameStatus}
import util.Observer

object GUI:
  def launch(controller: ControllerAPI): Unit =
    new GUI(controller).main(Array())

class GUI(val controller: ControllerAPI) extends JFXApp3 with Observer:

  private val rootPane = new BorderPane()
  private var currentGameScene: Option[GameScene] = None

  override def start(): Unit =
    controller.add(this)

    stage = new PrimaryStage {
      title = "Chess"
      scene = new Scene(rootPane, 700, 700)
      resizable = true
    }

    showStartMenu()

  def showStartMenu(): Unit =
    currentGameScene = None
    rootPane.center = StartMenu(this).root
    rootPane.top = null
    rootPane.bottom = null

  def showGame(): Unit =
    val gs = GameScene(this, controller)
    currentGameScene = Some(gs)
    rootPane.center = gs.root

  override def update: Boolean =
    Platform.runLater {
      currentGameScene.foreach { gs =>
        gs.redrawBoard()
        gs.updateStatus()
      }
    }
    true
