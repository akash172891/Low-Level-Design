package Entity;

public class Piece {
    PieceType name;
    Piece(PieceType name) {
        this.name = name;
    }

    public PieceType getName() {
        return name;
    }

    public void setName(PieceType name) {
        this.name = name;
    }

}
