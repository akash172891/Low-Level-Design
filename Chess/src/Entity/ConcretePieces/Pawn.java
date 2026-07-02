package Entity.ConcretePieces;

import Entity.Board;
import Entity.Cell;
import Entity.Piece;
import MovementStrategyPattern.ConcreteMovementStrategy.PawnMovementStrategy;

public class Pawn extends Piece {
    public Pawn(boolean isWhitePiece) {
        super(isWhitePiece, new PawnMovementStrategy());
    }

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return super.canMove(board, startCell, endCell);
    }
}