package controller.controllerComponent.controllerBaseImpl

import controller.controllerComponent.{ControllerAPI, GameStatus, Command}
import model.*
import model.modelComponent.ChessGameAPI
import util.Observable

final class ControllerImpl(private var _game: ChessGameAPI)
  extends Observable
  with ControllerAPI:

  override def game: ChessGameAPI = _game

  private var _gameStatus: GameStatus = GameStatus.Idle
  override def gameStatus: GameStatus = _gameStatus

  override def board: ChessBoard = _game.board
  override def currentPlayer: Color = _game.currentPlayer
  override def isGameOver: Boolean = _game.isGameOver
  override def winner: Option[Color] = _game.winner

  private var undoStack: List[Command] = Nil
  private var redoStack: List[Command] = Nil

  private def execute(cmd: Command): Unit =
    cmd.doStep()
    undoStack = cmd :: undoStack
    redoStack = Nil

  override def undo(): Unit =
    undoStack match
      case cmd :: rest =>
        undoStack = rest
        cmd.undoStep()
        redoStack = cmd :: redoStack
      case Nil => ()

  override def redo(): Unit =
    redoStack match
      case cmd :: rest =>
        redoStack = rest
        cmd.doStep()
        undoStack = cmd :: undoStack
      case Nil => ()

  override def processInput(input: String): Boolean =
    if input == null || input.trim.isEmpty then return false
    parsePosition(input.trim) match
      case Some(pos) =>
        execute(new MoveCommand(this, pos))
        true
      case None =>
        _gameStatus = GameStatus.Invalid
        notifyObservers
        false

  override def select(pos: Position): Unit =
    execute(new MoveCommand(this, pos))

  private[controllerBaseImpl] def handleSelect(pos: Position): Unit =
    val (nextGame, result) = _game.select(pos)
    _game = nextGame
    _gameStatus = result match
      case MoveResult.Selected   => GameStatus.PieceSelected
      case MoveResult.Deselected => GameStatus.Idle
      case MoveResult.Moved      => GameStatus.Moved
      case MoveResult.Capture    => GameStatus.Capture
      case MoveResult.Invalid    => GameStatus.Invalid
      case MoveResult.GameOver(w) => GameStatus.GameOver(w)
    notifyObservers

  private[controllerBaseImpl] def restoreBoard(board: ChessBoard, status: GameStatus): Unit =
    _game = _game.restore(GameMemento(board))
    _gameStatus = status
    notifyObservers

  private def parsePosition(input: String): Option[Position] =
    input.toLowerCase match
      case s if s.length == 2 =>
        val col = s(0) - 'a'
        val row = 8 - s(1).asDigit
        val pos = Position(row, col)
        if pos.isValid then Some(pos) else None
      case _ => None