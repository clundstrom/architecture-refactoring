package model.stalemate;

import model.Position;
import model.chess_pieces.Colour;

import java.util.List;

public interface IGameControllerStateInfo {

    Colour getCurrentPlayerToMove();

    boolean isCurrentPlayerIsInCheck();

    Position getEnPassantPosition();

    int getMoveNumber();

    List<Position> getCheckBlockingMoves();
}
