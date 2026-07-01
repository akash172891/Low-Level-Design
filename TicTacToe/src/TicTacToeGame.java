import Entity.Board;
import Entity.PieceO;
import Entity.PieceType;
import Entity.PieceX;
import Entity.Player;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {
    Deque<Player> players;
    Board gameBoard;

    void initializeGame() {
        players = new LinkedList<>();
        PieceX pieceX = new PieceX();
        Player player1 = new Player("p1", pieceX);

        PieceO pieceO = new PieceO();
        Player player2 = new Player("p2", pieceO);

        players.add(player1);
        players.add(player2);

        gameBoard = new Board(3);

    }

    String start() {
        boolean noWinner = true;

        while(noWinner) {

            Player player = players.removeFirst();
            List<List<Integer>> freeSpaces = gameBoard.getFreeCells();

            if(freeSpaces.isEmpty()) {
                noWinner = false;
                continue;
            }

            System.out.println("Player : "+ player.getName() + " Enter row, column");

            Scanner input = new Scanner(System.in);

            String s  = input.nextLine();
            String[] values = s.split(",");

            int inputRow = Integer.valueOf(values[0]);
            int inputCol = Integer.valueOf(values[1]);

            boolean pieceAdded = gameBoard.addPiece(inputRow, inputCol, player.getPiece());
            if(!pieceAdded) {
                System.out.println("Try Again");
                players.addFirst(player);
                continue;
            }

            players.addLast(player);
            boolean checkWinner = isWinnerExist(inputRow, inputCol, player.getPiece().getName());
            if(checkWinner) {
                return player.getName();
            }

        }
        return "tie";

    }

    boolean isWinnerExist(int row, int col, PieceType pieceType) {
        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        //need to check in row
        for(int i=0;i<gameBoard.size;i++) {

            if(gameBoard.board[row][i] == null || gameBoard.board[row][i].getName() != pieceType) {
                rowMatch = false;
            }
        }

        //need to check in column
        for(int i=0;i<gameBoard.size;i++) {

            if(gameBoard.board[i][col] == null || gameBoard.board[i][col].getName() != pieceType) {
                columnMatch = false;
            }
        }

        //need to check diagonals
        for(int i=0, j=0; i<gameBoard.size;i++,j++) {
            if (gameBoard.board[i][j] == null || gameBoard.board[i][j].getName() != pieceType) {
                diagonalMatch = false;
            }
        }

        //need to check anti-diagonals
        for(int i=0, j=gameBoard.size-1; i<gameBoard.size;i++,j--) {
            if (gameBoard.board[i][j] == null || gameBoard.board[i][j].getName() != pieceType) {
                antiDiagonalMatch = false;
            }
        }

        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
    }

}
