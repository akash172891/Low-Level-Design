package MovementStrategyPattern.ConcreteMovementStrategy;

import Entity.Board;
import Entity.Cell;
import MovementStrategyPattern.MovementStrategy;

public class QueenMovementStrategy implements MovementStrategy {
    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return false;
    }
}
