package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import model.chess_pieces.AbstractChessPiece;
import view.ChessBoard;
import model.stalemate.ChessBoardMoment;
import model.Position;

/**
 * Template design pattern in use here.
 * 
 * @author rhys
 *
 */
public class UndoRedoMove {

	private IUndoRedoController gameController;
	private ChessBoard chessBoard;
	private List<ChessBoardMoment> previousMoments;
	private int moveNumber;
	private int highestMoveNumber;
	enum Change {UNDO, REDO}

	public UndoRedoMove(IUndoRedoController gameController, ChessBoard chessBoard,
			List<ChessBoardMoment> previousMoments) {
		super();
		this.gameController = gameController;
		this.chessBoard = chessBoard;
		this.previousMoments = previousMoments;
	}

	public void undo() {
		changeBoard(Change.UNDO);
	}

	public void redo() {
		changeBoard(Change.REDO);
	}

	private void changeBoard(Change change) {
		for (ChessBoardMoment c : previousMoments) {
			Set<Position> blah = c.getChessPieces().keySet();
			Set<Position> sortedSet = new TreeSet<Position>();
			for (Position p : blah)
				sortedSet.add(p);
		}
		ChessBoardMoment desiredChessBoardMoment = null;
		if (change == Change.UNDO) {
			desiredChessBoardMoment = getRequiredMomentForUndo();
		}
		else if (change == Change.REDO) {
			desiredChessBoardMoment = getRequiredMomentForRedo();
		}
		setGameControllerStateInfo(desiredChessBoardMoment);
		setChessPieces(desiredChessBoardMoment);
		updateVisualBoard(getCurrentMoment().getChessPieces(), desiredChessBoardMoment.getChessPieces());
		chessBoard.toggleCheckLabel(desiredChessBoardMoment.getGCState().isCurrentPlayerIsInCheck());
		// The following is for if the player clicks undo/redo whilst a piece is selected. The colouring
		// of the pieces available moves needed to be cleared.
		if (gameController.getPieceCurrentlyHeld() != null) {
			gameController.nullifyPieceAndPossibleMoves();
			chessBoard.resetAllBoardSquareColours();
		}
		if (change == Change.UNDO)
			moveNumber--;
		else if (change == Change.REDO)
			moveNumber++;
	}

	private ChessBoardMoment getCurrentMoment() {
		return previousMoments.get(moveNumber);
	}

	private ChessBoardMoment getRequiredMomentForUndo() {
		ChessBoardMoment retMoment = previousMoments.get(moveNumber - 1);
		return retMoment;
	}

	private ChessBoardMoment getRequiredMomentForRedo() {
		ChessBoardMoment retMoment =  previousMoments.get(moveNumber + 1);
		return retMoment;
	}

	private void setGameControllerStateInfo(ChessBoardMoment chessBoardMoment) {
		GameControllerStateInfo gcState = (GameControllerStateInfo) chessBoardMoment.getGCState();
		gameController.setGcState(gcState);
	}

	private void setChessPieces(ChessBoardMoment chessBoardMoment) {
		Map<Position, AbstractChessPiece> chessPieces = chessBoardMoment.getChessPieces();
		chessBoard.setChessPieces(chessPieces);
	}

	private void updateVisualBoard(
			Map<Position, AbstractChessPiece> currentPieces,
			Map<Position, AbstractChessPiece> intendedPieces) {
		AbstractChessPiece pieceToAdd = null;
		AbstractChessPiece pieceToDelete = null;

		Set<Position> currentPositions = currentPieces.keySet();
		Set<Position> intendedPositions = intendedPieces.keySet();

		List<AbstractChessPiece> piecesToDelete = new ArrayList<AbstractChessPiece>();
		List<AbstractChessPiece> piecesToAdd = new ArrayList<AbstractChessPiece>();

		addPieceToIntended(intendedPieces, currentPieces, intendedPositions, currentPositions, piecesToAdd);
		addPieceToIntended(currentPieces, intendedPieces, currentPositions, intendedPositions, piecesToDelete);

		for (AbstractChessPiece piece : piecesToDelete)
		    chessBoard.removePiece(piece.getPosition());
		for (AbstractChessPiece piece : piecesToAdd)
		    chessBoard.addPiece(piece);
	}

	private void addPieceToIntended(Map<Position, AbstractChessPiece> currentPieces, Map<Position, AbstractChessPiece> intendedPieces, Set<Position> currentPositions, Set<Position> intendedPositions, List<AbstractChessPiece> piecesToDelete) {
		AbstractChessPiece pieceToDelete;
		for (Position currentPosition : currentPositions) {
			if (!intendedPositions.contains(currentPosition)
					|| !currentPieces.get(currentPosition).equals(intendedPieces.get(currentPosition))) {
				pieceToDelete = currentPieces.get(currentPosition);
				assert pieceToDelete.getPosition().equals(currentPosition);
				piecesToDelete.add(pieceToDelete);
			}
		}
	}

	/**
	 * This is only called when the player makes a move rather than clicking undo/redo.
	 * 
	 * @param newMoveNumber
	 */
	public void setHighestMoveNumber(int newMoveNumber) {
		highestMoveNumber = newMoveNumber;
		moveNumber = newMoveNumber;
		trimPreviousMoments();
	}

	private void trimPreviousMoments() {
		while (previousMoments.size() > (highestMoveNumber)) {
			previousMoments.remove(previousMoments.size() - 1);
		}
	}

	public int getHighestMoveNumber() {
		return highestMoveNumber;
	}
}