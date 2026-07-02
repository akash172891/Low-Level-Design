package Entity.ConcretePieces;

import Entity.Board;
import Entity.Cell;
import Entity.Piece;
import MovementStrategyPattern.ConcreteMovementStrategy.QueenMovementStrategy;

public class Queen extends Piece {
    public Queen(boolean isWhitePiece) {
        super(isWhitePiece, new QueenMovementStrategy());
    }

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return super.canMove(board, startCell, endCell);
    }
}