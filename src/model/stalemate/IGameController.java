package model.stalemate;

import model.Position;
import model.chess_pieces.AbstractChessPiece;
import model.chess_pieces.Colour;

import java.util.List;

public interface IGameController {
    void undo();
    int getMoveNumber();
    void redo();
    int getHighestRecordedMoveNumber();
    void reset();
    void squareClicked(Position clickedPosition);
    void determineIfCurrentPlayerIsInCheck();

    Colour getCurrentPlayerToMove();

    List<AbstractChessPiece> getPlayersPieces(Colour currentPlayerToMove);

    List<Position> getAllowedMovesForPiece(AbstractChessPiece piece);

    Colour getColourOfSquareAtPosition(Position position);
}
