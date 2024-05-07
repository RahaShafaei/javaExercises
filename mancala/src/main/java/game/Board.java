package src.main.java.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Board {
    private Row rowA;
    private Row rowB;

    private Mancala mancalaA;
    private Mancala mancalaB;

    @Override
    public String toString() {
        return "Board\n {" +
                "\n rowA=" + rowA +
                "\n , rowB=" + rowB +
                "\n , mancalaA=" + mancalaA +
                "\n , mancalaB=" + mancalaB +
                "\n }";
    }
}
