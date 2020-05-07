public class Main {

    public static void main(String[] args) {

        Game game1 = new Game();
        game1.fillBoard();
        game1.printBoard();
        Move bestMove = game1.besteMove();
        System.out.println("ai best move row:"+bestMove.row);
        System.out.println("ai best move col:"+bestMove.col);

/*        while (!game1.checkWinner()) {
            game1.printBoard();
            game1.playerTurn();
            game1.printBoard();
            game1.aiTurn();

        }
    }*/
    }
}