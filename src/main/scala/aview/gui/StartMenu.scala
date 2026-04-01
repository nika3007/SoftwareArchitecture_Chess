package aview.gui

import scalafx.scene.layout.VBox
import scalafx.scene.control.{Button, Label}
import scalafx.scene.text.{Font, FontWeight}
import scalafx.geometry.Pos
import scalafx.scene.paint.Color

case class StartMenu(gui: GUI):

  val root = new VBox {
    spacing = 40
    alignment = Pos.Center
    style = "-fx-background-color: #2b2b2b;"

    children = Seq(
      new Label("♔ CHESS ♚") {
        font = Font.font("Georgia", FontWeight.Bold, 64)
        textFill = Color.web("#f0d9b5")
      },
      new Label("A Classic Game of Strategy") {
        font = Font.font("Georgia", 18)
        textFill = Color.web("#b58863")
      },
      new Button("New Game") {
        prefWidth = 200
        prefHeight = 50
        style = """
          -fx-background-color: #f0d9b5;
          -fx-text-fill: #2b2b2b;
          -fx-font-size: 18px;
          -fx-font-family: Georgia;
          -fx-background-radius: 8;
          -fx-cursor: hand;
        """
        onAction = _ => gui.showGame()
      },
      new Button("Quit") {
        prefWidth = 200
        prefHeight = 50
        style = """
          -fx-background-color: #b58863;
          -fx-text-fill: white;
          -fx-font-size: 18px;
          -fx-font-family: Georgia;
          -fx-background-radius: 8;
          -fx-cursor: hand;
        """
        onAction = _ => System.exit(0)
      }
    )
  }
