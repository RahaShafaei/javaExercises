package src.main.java.game;

public class MainCls {
    public static void main(String[] args) {
        System.out.println("Hello Mancala!");
        System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        System.out.println();

        Player playerA = new Player("PlayerA", StatusEnm.UNKNOWN);
        Player playerB = new Player("PlayerB", StatusEnm.UNKNOWN);

        MancalaGame mancalaGame = new MancalaGame(playerA, playerB);

        System.out.println(mancalaGame);



    }
}
