package src.main.java.game;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MancalaGame {
    private Board board;

    public MancalaGame(Player playerA, Player playerB) {
        Row rowA = new Row(playerA);
        Row rowB = new Row(playerB);

        Mancala mancalaA = new Mancala(playerA);
        Mancala mancalaB = new Mancala(playerB);

        this.board = new Board(rowA,rowB,mancalaA,mancalaB);
    }

}
