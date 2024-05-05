package src.main.java.game;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@AllArgsConstructor
public class Hole {
    @NonNull
    Player player;
    List<Stone> stones;

    public Hole() {
        stones = new ArrayList<>();
    }
}
