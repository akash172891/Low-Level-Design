package Entity;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public int size;
    public Piece[][] board;

    public Board(int size) {
        this.size = size;
        board = new Piece[size][size];
    }

    public boolean addPiece(int row, int col, Piece piece) {
        if(board[row][col]!=null) return false;
        board[row][col] = piece;
        return true;
    }

    public List<List<Integer>> getFreeCells() {
        List<List<Integer>> freeCells = new ArrayList<>();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board[row][col] == null) {
                    freeCells.add(List.of(row, col));
                }
            }
        }

        return freeCells;
    }




}
