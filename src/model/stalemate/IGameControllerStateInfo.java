package model.stalemate;

import model.Position;
import model.chess_pieces.AbstractChessPiece;

import java.util.List;

public interface IGameControllerStateInfo {

    AbstractChessPiece.Colour getCurrentPlayerToMove();

    boolean isCurrentPlayerIsInCheck();

    Position getEnPassantPosition();

    int getMoveNumber();

    List<Position> getCheckBlockingMoves();
}
