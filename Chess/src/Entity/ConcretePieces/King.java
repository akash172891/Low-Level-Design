package Entity.ConcretePieces;

import Entity.Board;
import Entity.Cell;
import Entity.Piece;
import MovementStrategyPattern.ConcreteMovementStrategy.KingMovementStrategy;

public class King extends Piece {
    public King(boolean isWhitePiece) {
        super(isWhitePiece, new KingMovementStrategy());
    }

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return super.canMove(board, startCell, endCell);
    }
}
