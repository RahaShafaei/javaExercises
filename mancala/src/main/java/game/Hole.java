package src.main.java.game;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@ToString
public class Hole {

    private List<Stone> stones;

    public Hole() {
        stones = new ArrayList<>();
    }
}
