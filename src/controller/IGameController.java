package controller;

import model.Position;

public interface IGameController {
    void undo();

    int getMoveNumber();

    void redo();

    int getHighestRecordedMoveNumber();

    void reset();

    void squareClicked(Position clickedPosition);

    void determineIfCurrentPlayerIsInCheck();
}
