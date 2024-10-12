import java.util.Scanner;

public class ConnectFour {

    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static final char EMPTY = '-';
    private static final char PLAYER_ONE = 'X';
    private static final char PLAYER_TWO = 'O';
    
    private static char[][] board = new char[ROWS][COLUMNS];

    public static void main(String[] args) {
        initializeBoard();
        printBoard();

        boolean gameWon = false;
        char currentPlayer = PLAYER_ONE;
        Scanner scanner = new Scanner(System.in);

        while (!gameWon) {
            System.out.println("Player " + (currentPlayer == PLAYER_ONE ? "1" : "2") + "'s turn (Enter column 1-7): ");
            int column = scanner.nextInt() - 1;

            if (column < 0 || column >= COLUMNS) {
                System.out.println("Invalid column. Please try again.");
                continue;
            }

            if (!dropDisc(currentPlayer, column)) {
                System.out.println("Column is full. Please try another one.");
                continue;
            }

            printBoard();

            if (checkWin(currentPlayer)) {
                System.out.println("Player " + (currentPlayer == PLAYER_ONE ? "1" : "2") + " wins!");
                gameWon = true;
            } else if (isBoardFull()) {
                System.out.println("It's a draw!");
                break;
            }

            currentPlayer = (currentPlayer == PLAYER_ONE) ? PLAYER_TWO : PLAYER_ONE;
        }
        scanner.close();
    }

    // Initialize the board with empty slots
    private static void initializeBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                board[row][col] = EMPTY;
            }
        }
    }

    // Print the current state of the board
    private static void printBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println("1 2 3 4 5 6 7");  // Column numbers for user reference
    }

    // Drop a disc into the specified column
    private static boolean dropDisc(char player, int column) {
        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][column] == EMPTY) {
                board[row][column] = player;
                return true;
            }
        }
        return false;  // Column is full
    }

    // Check if the board is full (draw condition)
    private static boolean isBoardFull() {
        for (int col = 0; col < COLUMNS; col++) {
            if (board[0][col] == EMPTY) {
                return false;
            }
        }
        return true;
    }

    // Check if the current player has won
    private static boolean checkWin(char player) {
        // Check horizontal, vertical, and diagonal wins
        return checkHorizontal(player) || checkVertical(player) || checkDiagonal(player);
    }

    // Check for horizontal wins
    private static boolean checkHorizontal(char player) {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col <= COLUMNS - 4; col++) {
                if (board[row][col] == player && board[row][col + 1] == player &&
                    board[row][col + 2] == player && board[row][col + 3] == player) {
                    return true;
                }
            }
        }
        return false;
    }

    // Check for vertical wins
    private static boolean checkVertical(char player) {
        for (int col = 0; col < COLUMNS; col++) {
            for (int row = 0; row <= ROWS - 4; row++) {
                if (board[row][col] == player && board[row + 1][col] == player &&
                    board[row + 2][col] == player && board[row + 3][col] == player) {
                    return true;
                }
            }
        }
        return false;
    }

    // Check for diagonal wins
    private static boolean checkDiagonal(char player) {
        // Check for diagonals with positive slope
        for (int row = 3; row < ROWS; row++) {
            for (int col = 0; col <= COLUMNS - 4; col++) {
                if (board[row][col] == player && board[row - 1][col + 1] == player &&
                    board[row - 2][col + 2] == player && board[row - 3][col + 3] == player) {
                    return true;
                }
            }
        }

        // Check for diagonals with negative slope
        for (int row = 0; row <= ROWS - 4; row++) {
            for (int col = 0; col <= COLUMNS - 4; col++) {
                if (board[row][col] == player && board[row + 1][col + 1] == player &&
                    board[row + 2][col + 2] == player && board[row + 3][col + 3] == player) {
                    return true;
                }
            }
        }

        return false;
    }
}
