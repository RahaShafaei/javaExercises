package src.main.java.game;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class Mancala extends Hole{
    @NonNull
    private Player player;
}
