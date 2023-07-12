package com.player.playlistapplication.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
/**
 * @author Raha
 * @since 2023-06-22
 *
 * <p>
 *     To hold general static methods.
 * </p>
 */
public class Helper {
    private Helper() {
        throw new IllegalStateException("Helper Utility class");
    }
    public static <T, R, Y> Map<R, Y> convertListToMap(List<T> lst, Function<T, R> func1, Function<T, Y> func2) {
        Map<R, Y> temp = new HashMap<>();

        lst.stream().forEach(t -> temp.put(func1.apply(t), func2.apply(t)));

        return temp;
    }
}
