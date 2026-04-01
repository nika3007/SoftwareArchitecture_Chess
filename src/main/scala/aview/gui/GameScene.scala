package aview.gui

import controller.controllerComponent.{ControllerAPI, GameStatus}
import scalafx.scene.layout.{BorderPane, GridPane, VBox, HBox, Region, Priority}
import scalafx.scene.control.{Button, Label}
import scalafx.scene.text.{Font, FontWeight}
import scalafx.scene.paint.Color
import scalafx.geometry.{Insets, Pos}
import scalafx.Includes._
import model._

case class GameScene(gui: GUI, controller: ControllerAPI):

  private var legalMovePositions: Set[Position] = Set.empty

  private val statusLabel = new Label("White's turn") {
    font = Font.font("Georgia", 16)
    textFill = Color.web("#f0d9b5")
  }

  private val grid = new GridPane {
    hgap = 2
    vgap = 2
    alignment = Pos.Center
  }

  private def buttonStyle(color: String): String =
    s"""
      -fx-background-color: $color;
      -fx-text-fill: ${if color == "#f0d9b5" then "#2b2b2b" else "white"};
      -fx-font-size: 14px;
      -fx-font-family: Georgia;
      -fx-background-radius: 6;
      -fx-cursor: hand;
      -fx-padding: 8 16;
    """

  private def pieceText(piece: model.Piece): String =
    piece.pieceType match
      case model.PieceType.King   => if piece.color == model.Color.White then "K" else "k"
      case model.PieceType.Queen  => if piece.color == model.Color.White then "Q" else "q"
      case model.PieceType.Rook   => if piece.color == model.Color.White then "R" else "r"
      case model.PieceType.Bishop => if piece.color == model.Color.White then "B" else "b"
      case model.PieceType.Knight => if piece.color == model.Color.White then "N" else "n"
      case model.PieceType.Pawn   => if piece.color == model.Color.White then "P" else "p"

  val root = new BorderPane {
    style = "-fx-background-color: #2b2b2b;"
    top = buildTopBar()
    center = buildCenter()
    bottom = buildStatusBar()
  }

  redrawBoard()

  private def buildTopBar(): HBox =
    new HBox {
      spacing = 10
      padding = Insets(10)
      alignment = Pos.CenterLeft
      style = "-fx-background-color: #1a1a1a;"
      children = Seq(
        new Button("⟵ Menu") {
          style = buttonStyle("#b58863")
          onAction = _ => gui.showStartMenu()
        },
        new Region {
          HBox.setHgrow(this, Priority.Always)
        },
        new Button("Undo") {
          style = buttonStyle("#555555")
          onAction = _ =>
            legalMovePositions = Set.empty
            controller.undo()
        },
        new Button("Redo") {
          style = buttonStyle("#555555")
          onAction = _ =>
            legalMovePositions = Set.empty
            controller.redo()
        },
        new Button("New Game") {
          style = buttonStyle("#f0d9b5")
          onAction = _ =>
            legalMovePositions = Set.empty
            gui.showGame()
        }
      )
    }

  private def buildCenter(): VBox =
    new VBox {
      alignment = Pos.Center
      padding = Insets(20)
      children = Seq(buildCoordinates(), grid)
    }

  private def buildCoordinates(): HBox =
    new HBox {
      spacing = 0
      alignment = Pos.Center
      padding = Insets(0, 0, 0, 40)
      children = new Label("") {
        prefWidth = 10
      } +: "abcdefgh".map { c =>
        new Label(c.toString) {
          prefWidth = 70
          alignment = Pos.Center
          font = Font.font("Georgia", 14)
          textFill = Color.web("#b58863")
        }
      }.toSeq
    }

  private def buildStatusBar(): HBox =
    new HBox {
      padding = Insets(10, 20, 10, 20)
      alignment = Pos.CenterLeft
      style = "-fx-background-color: #1a1a1a;"
      children = Seq(statusLabel)
    }

  def redrawBoard(): Unit =
    grid.children.clear()

    for row <- 0 until 8 do
      val rankLabel = new Label(s"${8 - row}") {
        prefWidth = 30
        prefHeight = 70
        alignment = Pos.Center
        font = Font.font("Georgia", 14)
        textFill = Color.web("#b58863")
      }
      GridPane.setRowIndex(rankLabel, row)
      GridPane.setColumnIndex(rankLabel, 0)
      grid.children.add(rankLabel)

      for col <- 0 until 8 do
        val isLight    = (row + col) % 2 == 0
        val piece      = controller.board.cells(row)(col)
        val pos        = Position(row, col)
        val isSelected = controller.board.selectedPosition.contains(pos)
        val isLegal    = legalMovePositions.contains(pos)

        val baseColor =
          if isSelected then "#aaa23a"
          else if isLegal && piece.isDefined then "#cdd16f"
          else if isLegal then "#e8ef8a"
          else if isLight then "#f0d9b5"
          else "#b58863"

        val btn = new Button {
          prefWidth  = 70
          prefHeight = 70
          text = piece.map(pieceText).getOrElse("")
          font = Font.font("Georgia", FontWeight.Bold, 28)
          style = s"""
            -fx-background-color: $baseColor;
            -fx-background-radius: 0;
            -fx-cursor: hand;
            -fx-text-fill: ${if piece.exists(_.color == model.Color.White) then "#ffffff" else "#1a1a1a"};
          """
          onAction = _ =>
            controller.board.selectedPosition match
              case Some(_) =>
                legalMovePositions = Set.empty
                controller.select(pos)
              case None =>
                controller.select(pos)
                legalMovePositions = controller.board.legalMoves(pos).toSet
        }

        GridPane.setRowIndex(btn, row)
        GridPane.setColumnIndex(btn, col + 1)
        grid.children.add(btn)

  def updateStatus(): Unit =
    if controller.isGameOver then
      controller.winner match
        case Some(w) =>
          statusLabel.text = s"Game Over! $w wins! 🎉"
          statusLabel.textFill = Color.web("#f0d9b5")
        case None =>
          statusLabel.text = "Game Over!"
    else
      val msg = GameStatus.message(controller.gameStatus)
      statusLabel.text =
        if msg.nonEmpty then msg
        else s"${controller.currentPlayer}'s turn"
