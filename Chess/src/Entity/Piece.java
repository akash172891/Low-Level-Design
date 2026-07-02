package Entity;

import MovementStrategyPattern.MovementStrategy;

public class Piece {
    boolean isWhitePiece;
    boolean killed = false;
    MovementStrategy movementStrategy;
    public Piece(boolean isWhitePiece, MovementStrategy movementStrategy) {
        this.isWhitePiece = isWhitePiece;
        this.movementStrategy = movementStrategy;
    }

    public boolean isWhite() {
        return isWhitePiece;
    }
    public boolean isKilled() {
        return killed;
    }
    public void setKilled(boolean killed) {
        this.killed = killed;
    }
    public boolean canMove(Board board, Cell startBlock, Cell endBlock) {
        return movementStrategy.canMove(board, startBlock, endBlock);
    }
}
