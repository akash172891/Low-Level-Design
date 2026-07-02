package Entity.ConcretePieces;


import Entity.Board;
import Entity.Cell;
import Entity.Piece;
import MovementStrategyPattern.ConcreteMovementStrategy.BishopMovementStrategy;

public class Bishop extends Piece {
    public Bishop(boolean isWhitePiece) {
        super(isWhitePiece, new BishopMovementStrategy());
    }

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return super.canMove(board, startCell, endCell);
    }
}
