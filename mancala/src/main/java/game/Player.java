package src.main.java.game;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class Player {
    @NonNull
    private String name;
    private StatusEnm status;
}
