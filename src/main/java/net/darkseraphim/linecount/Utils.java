package net.darkseraphim.linecount;

import java.util.Spliterator;
import java.util.Spliterators;

public abstract class Utils {

    public static Boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }

    public static <T> Iterable<T> spliteratorAsIterable(Spliterator<T> spliterator) {
        return () -> Spliterators.iterator(spliterator);
    }
}
