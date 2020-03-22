package model.chess_pieces;

import model.Position;

public class AbstractChessPieceFactory {

    public static final AbstractChessPiece createChessPiece(String name, AbstractChessPiece.Colour colour, Position position) {
        if (name.equals("Pawn"))
            return new Pawn(colour, position);
        if (name.equals("Rook"))
            return new Rook(colour, position);
        if (name.equals("Knight"))
            return new Knight(colour, position);
        if (name.equals("Bishop"))
            return new Bishop(colour, position);
        if (name.equals("Queen"))
            return new Queen(colour, position);
        if (name.equals("King"))
            return new King(colour, position);
        assert false : "\"" + name + "\" is not a valid name of a chess piece class.";
        return null;
    }
}
