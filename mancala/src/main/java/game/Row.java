package src.main.java.game;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Setter
@Getter
@ToString
public class Row {
    private Player player;
    private List<Pocket> pockets = new ArrayList<>();

    public Row(Player player) {
        this.player = player;

        IntStream.range(0, 6)
                .mapToObj(i -> new Pocket())
                .forEach(pockets::add);
    }
}
