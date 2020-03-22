package model.stalemate;

import model.Position;
import model.chess_pieces.AbstractChessPiece;

import java.util.List;

public interface IGameController {
    void undo();
    int getMoveNumber();
    void redo();
    int getHighestRecordedMoveNumber();
    void reset();
    void squareClicked(Position clickedPosition);
    void determineIfCurrentPlayerIsInCheck();

    AbstractChessPiece.Colour getCurrentPlayerToMove();

    List<AbstractChessPiece> getPlayersPieces(AbstractChessPiece.Colour currentPlayerToMove);

    List<Position> getAllowedMovesForPiece(AbstractChessPiece piece);

    AbstractChessPiece.Colour getColourOfSquareAtPosition(Position position);
}
