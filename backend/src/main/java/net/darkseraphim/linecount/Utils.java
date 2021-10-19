package net.darkseraphim.linecount;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public abstract class Utils {

    public static Boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }

    public static <T> Iterable<T> spliteratorAsIterable(Spliterator<T> spliterator) {
        return () -> Spliterators.iterator(spliterator);
    }

    public static <T> Stream<Stream<T>> chunks(Iterable<T> in, int n) {
        List<Iterator<T>> iterators = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Iterator<T> it = in.iterator();
            for (int j = 0; j < i && it.hasNext(); j++) {
                it.next(); // drop element
            }
            iterators.add(it);
        }
        
        return StreamSupport.stream(((Iterable<Stream<T>>) () -> (new Iterator<Stream<T>>(){
            @Override
            public boolean hasNext() {
                return iterators.stream().allMatch(Iterator::hasNext);
            }

            @Override
            public Stream<T> next() {
                return iterators.stream().map(Iterator::next);
            }
        })).spliterator(), false);
    }  
}
