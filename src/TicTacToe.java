import java.util.Scanner;

public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];
    private static String currentPlayer = "X";

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        clearBoard(); // Clear the board before starting the game.
        display(); // Display the empty board.

        while (true)
        {
            System.out.println("Player " + currentPlayer + "'s turn"); // Prompt the current player to make a move.
            int row, col;
            do
            {
                System.out.print("Enter row (1-3): "); // Prompt for row input.
                row = scanner.nextInt() - 1; // Adjusting input to match array index.
                System.out.print("Enter column (1-3): "); // Prompt for column input.
                col = scanner.nextInt() - 1; // Adjusting input to match array index.
            } while (!isValidMove(row, col)); // Check if the move is valid. Repeat if not.

            board[row][col] = currentPlayer; // Place the current player's symbol on the board.
            display(); // Display the updated board.

            if (isWin(currentPlayer))
            { // Check if the current player has won.
                System.out.println("Player " + currentPlayer + " wins!"); // Announce the winner.
                break; // End the game.
            } else if (isTie())
            { // Check if it's a tie.
                System.out.println("It's a tie!"); // Announce a tie.
                break; // End the game.
            }

            currentPlayer = (currentPlayer.equals("X")) ? "O" : "X"; // Switch players for the next turn.
        }
    }

    private static void clearBoard()
    {
        for (int i = 0; i < ROW; i++)
        {
            for (int j = 0; j < COL; j++)
            {
                board[i][j] = " "; // Set all positions on the board to empty.
            }
        }
    }

    private static void display()
    {
        for (int i = 0; i < ROW; i++)
        {
            for (int j = 0; j < COL; j++)
            {
                System.out.print(board[i][j]); // Display each position on the board.
                if (j < COL - 1)
                {
                    System.out.print(" | "); // Add vertical separators between columns.
                }
            }
            System.out.println();
            if (i < ROW - 1)
            {
                System.out.println("---------"); // Add horizontal separators between rows.
            }
        }
    }

    private static boolean isValidMove(int row, int col)
    {
        if (row < 0 || row >= ROW || col < 0 || col >= COL)
        {
            System.out.println("Invalid move. Row and column must be between 1 and 3."); // Check if the chosen position is within the board.
            return false;
        }
        if (!board[row][col].equals(" "))
        {
            System.out.println("Invalid move. Cell already occupied."); // Check if the chosen position is empty.
            return false;
        }
        return true;
    }

    private static boolean isWin(String player)
    {
        return isRowWin(player) || isColWin(player) || isDiagnalWin(player); // Check if the player has won.
    }

    private static boolean isColWin(String player)
    {
        for (int col = 0; col < COL; col++)
        {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true; // Check if there's a win in any column.
            }
        }
        return false;
    }

    private static boolean isRowWin(String player)
    {
        for (int row = 0; row < ROW; row++)
        {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player))
            {
                return true; // Check if there's a win in any row.
            }
        }
        return false;
    }

    private static boolean isDiagnalWin(String player)
    {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)); // Check if there's a win in any diagonal.
    }

    private static boolean isTie()
    {
        for (int i = 0; i < ROW; i++)
        {
            for (int j = 0; j < COL; j++)
            {
                if (board[i][j].equals(" "))
                {
                    return false; // Check if the board is fully occupied.
                }
            }
        }
        return true;
    }
}