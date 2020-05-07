import java.util.Random;
import java.util.Scanner;

public class Game {

    private Character[][] board;
    private static final int rows = 3;
    private static final int cols = 3;
    private Move move = new Move();
    Random random;
    char player = 'x';
    char ai = '0';
    Scanner sc = new Scanner(System.in);


    public Game() {
        board = new Character[rows][cols];
    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    System.out.print("[ ]");
                } else {
                    System.out.print("[" + board[i][j] + "]");
                }
            }
            System.out.println("");
        }
    }

    void fillBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
        board[0][0]='0';
        board[0][1]='0';
        //board[0][2]='x';
        board[1][0]='x';
        board[1][1]='x';
      //  board[2][0]='x';
       // board[2][1]='x';




    }

    boolean isMovesLeft(Character[][] borad) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (borad[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    Move aiMove() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    i = random.nextInt(2);
                    j = random.nextInt(2);
                    move.row = i;
                    move.col = j;
                }
            }
        }
        return move;
    }

    int evaluateMove() {
        //Evaluate Moves on all the rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                if (board[i][0] == 'x') {
                    return 1;
                } else if (board[i][0] == '0') {
                    return -1;
                }
            }
        }
        //Evaluate Moves on all the columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                if (board[0][i] == 'x') {
                    return 1;
                } else if (board[0][i] == '0') {
                    return -1;
                }
            }
        }
        //Evaluate Moves on the diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == 'x') {
                return 1;
            }
            if (board[0][0] == '0')
                return -1;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == 'x') {
                return 1;
            }
            if (board[0][0] == '0')
                return -1;
        }
        //Return 0 if it's tie
        return 0;
    }

    int minimax(Character[][] board, int depth, boolean isMax) {
        int score = evaluateMove();
        //X won
        if (score == 1) {
            return score;
        }
        //0 won
        if (score == -1) {
            return score;
        }
        //Tie
        if (!isMovesLeft(board)) {
            return 0;
        }

        if (isMax) {
            int best = -100;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '-') {
                            board[i][j] = player;
                        best = Math.max(best, minimax(board, depth + 1, false));
                        board[i][j] = '-';
                    }
                }
            }
            return best;
        } else {
            int best = 100;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '-') {
                        board[i][j] = ai;
                        best = Math.min(best, minimax(board, depth + 1, true));
                        board[i][j] = '-';
                    }
                }
            }
            return best;
        }
    }

    Move besteMove() {
        int bestVal = -100;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    board[i][j] = player;
                    int moveVal = minimax(board, 0, false);
                    board[i][j] = '-';
                    if (moveVal > bestVal) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }

            }
        }
        return bestMove;
    }

    boolean checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
                System.out.println("row ");
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != '-') {
                System.out.println("col");
                return true;
            }
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
            System.out.println("diag first");
            return true;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '-') {
            System.out.println("diag sec");
            return true;
        }
        return false;
    }

    void playGame() {
            fillBoard();
        do {
            printBoard();
            playerTurn();
            printBoard();
            aiTurn();
        } while (!checkWinner());
            printBoard();
    }

    void playerTurn() {
        System.out.println("player move");
        System.out.println("row:");
        System.out.println("col:");
         int row = sc.nextInt();
         int col = sc.nextInt();
        if(board[row][col]=='-'){
            board[row][col]=player;
        }
        else{
            System.out.println("Please eneter a valid position");
            playerTurn();
        }
    }

    void aiTurn() {
        Move besteMove=besteMove();
        System.out.println("ai move");
        System.out.println("row:" + besteMove.row);
        System.out.println("col:" + besteMove.col);
        int row = besteMove.row;
        int col = besteMove.col;
        board[row][col]=ai;
    }
}
