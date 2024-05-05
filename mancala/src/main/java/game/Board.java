package src.main.java.game;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Board {
    Row rowA;
    Row rowB;

    Mancala mancalaA;
    Mancala mancalaB;
}
