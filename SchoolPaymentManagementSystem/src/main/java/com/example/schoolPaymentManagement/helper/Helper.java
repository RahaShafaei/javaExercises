package com.example.schoolPaymentManagement.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 * To hold general static methods.
 * </p>
 */
public class Helper {

    private Helper() {
        throw new IllegalStateException("Helper Utility class");
    }
    public static <T, R, Y> Map<R, Y> convertListToMap(List<T> lst,
                                                       Function<T, R> func1,
                                                       Function<T, Y> func2
    ) {
        Map<R, Y> temp = new HashMap<>();

        lst.stream().forEach(t -> temp.put(func1.apply(t), func2.apply(t)));

        return temp;
    }

    public static <T, R, Y, Z> Map<R, Z> convertListToMapAccordingToMapper(List<T> lst,
                                                                           Function<T, R> func1,
                                                                           Function<T, Y> func2,
                                                                           Function<Y, Z> func3
    ) {
        Map<R, Z> temp = new HashMap<>();

        lst.stream().forEach(t -> temp.put(func1.apply(t), func2.andThen(func3).apply(t)));

        return temp;
    }

    static <T, Y, R> R playTwoToOne(T t1,
                                    T t2,
                                    BiFunction<T, T, Y> twoFunc,
                                    Function<Y, R> oneFunc
    ) {
        return twoFunc.andThen(oneFunc).apply(t1, t2);
    }
}
