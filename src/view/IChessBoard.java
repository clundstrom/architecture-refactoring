package view;

import model.Position;
import model.chess_pieces.AbstractChessPiece;

public interface IChessBoard {
    void setPieceAtPosition(Position clickedPosition, AbstractChessPiece pieceCurrentlyHeld);

    void movePiece(AbstractChessPiece pieceCurrentlyHeld, Position clickedPosition);
}
