package model.chess_pieces;

public enum Colour {
    WHITE("White"), BLACK("Black");

    private String name;

    Colour(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
