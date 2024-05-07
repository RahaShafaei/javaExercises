package src.main.java.game;

import java.util.stream.IntStream;

public class Pocket extends Hole {
    private static int stoneNumber = 1;

    public Pocket() {
        IntStream.rangeClosed(stoneNumber, stoneNumber + 3)
                .mapToObj(Stone::new)
                .forEach(super.getStones()::add);
        stoneNumber += 4;
    }
}
