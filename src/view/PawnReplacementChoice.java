package view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import model.stalemate.IGameController;
import model.Position;
import model.chess_pieces.AbstractChessPiece;
import model.chess_pieces.Bishop;
import model.chess_pieces.Knight;
import model.chess_pieces.Queen;
import model.chess_pieces.Rook;

public class PawnReplacementChoice {

    IChessBoard chessBoard;
    IGameController gameController;
    JOptionPane optionPane;
    JDialog dialog;
    AbstractChessPiece pieceCurrentlyHeld;
    Position clickedPosition;

    public PawnReplacementChoice(IChessBoard chessBoard, IGameController gameController,
                                 AbstractChessPiece pieceCurrentlyHeld, Position clickedPosition) {
        this.chessBoard = chessBoard;
        this.gameController = gameController;
        this.pieceCurrentlyHeld = pieceCurrentlyHeld;
        this.clickedPosition = clickedPosition;
    }

    public void replace() {
        JButton[] optionButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            String choice = null;
            switch (i) {
                case 0:
                    choice = "Bishop";
                    break;
                case 1:
                    choice = "Knight";
                    break;
                case 2:
                    choice = "Queen";
                    break;
                case 3:
                    choice = "Rook";
                    break;
            }
            JButton button = new JButton(choice);
            button.addActionListener(generateActionListener(choice));
            optionButtons[i] = button;
        }
        optionPane = new JOptionPane("Choose a piece to replace the pawn.", JOptionPane.QUESTION_MESSAGE,
                JOptionPane.DEFAULT_OPTION, null, optionButtons);

        dialog = new JDialog((Dialog) chessBoard, true);
        dialog.setContentPane(optionPane);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        optionPane.addPropertyChangeListener(
                e -> {
                    if (dialog.isVisible() && (e.getSource() == optionPane)) {
                        dialog.setVisible(false);
                    }
                });
        dialog.pack();
        dialog.setLocationRelativeTo((Component) chessBoard);
        dialog.setVisible(true);
    }

    private ActionListener generateActionListener(final String choice) {
        return e -> implementPawnReplacementChoice(choice);
    }

    private void implementPawnReplacementChoice(String choice) {
        if (choice.equals("Queen"))
            pieceCurrentlyHeld = new Queen(pieceCurrentlyHeld.getColour(), clickedPosition);
        else if (choice.equals("Knight"))
            pieceCurrentlyHeld = new Knight(pieceCurrentlyHeld.getColour(), clickedPosition);
        else if (choice.equals("Rook"))
            pieceCurrentlyHeld = new Rook(pieceCurrentlyHeld.getColour(), clickedPosition);
        else if (choice.equals("Bishop"))
            pieceCurrentlyHeld = new Bishop(pieceCurrentlyHeld.getColour(), clickedPosition);
        else
            assert false;
        chessBoard.setPieceAtPosition(clickedPosition, pieceCurrentlyHeld);
        chessBoard.movePiece(pieceCurrentlyHeld, clickedPosition);
        // Call this again to see if the player is in check now that a replacement piece has been selected.
        gameController.determineIfCurrentPlayerIsInCheck();
        optionPane.firePropertyChange("a", false, true);
    }

}