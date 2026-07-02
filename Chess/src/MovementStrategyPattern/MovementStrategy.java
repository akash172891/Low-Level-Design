package MovementStrategyPattern;

import Entity.Board;
import Entity.Cell;

public interface MovementStrategy {
    boolean canMove(Board board, Cell startCell, Cell endCell);
}
