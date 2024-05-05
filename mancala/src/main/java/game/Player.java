package src.main.java.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Player {
    @NonNull
    String name;
    StatusEnm status;
}
