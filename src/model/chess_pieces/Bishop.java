package model.chess_pieces;

import java.util.ArrayList;
import java.util.List;

import model.Position;


public class Bishop extends AbstractChessPiece {

	public Bishop(Colour colour, Position position) {
		super(colour, position);
		pieceName = colour.getName() + "Bishop";
	}

	@Override
	public Bishop clone(){
		Class classType = getClass();
		if(classType == Bishop.class){
			var cloned = new Bishop(getColour(), getPosition());
			cloned.hasMoved = hasMoved;
			return cloned;
		}
		return null;
	}

	@Override
	public List<List<Position>> deriveAllMoves() {
		List<List<Position>> listHolder = new ArrayList<List<Position>>();
		AbstractChessPiece.addDiagonalTranslations(listHolder, position);
		return listHolder;
	}

}
