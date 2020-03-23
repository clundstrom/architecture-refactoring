package controller;

import model.chess_pieces.AbstractChessPiece;

public interface IUndoRedoController {


    AbstractChessPiece getPieceCurrentlyHeld();

    void nullifyPieceAndPossibleMoves();

    void setGcState(GameControllerStateInfo gcState);
}
