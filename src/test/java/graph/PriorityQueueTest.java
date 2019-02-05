package graph;

import javafx.util.Pair;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class PriorityQueueTest {
    @Test
    public void update() {
        PriorityQueue queue = new PriorityQueue(5);

        queue.update(2, 1);
        queue.update(4, 10);
        queue.update(3, 1);
        queue.update(4, 9);
        queue.update(1, 10);

        assertEquals(0, queue.head());
        assertEquals(2, queue.head());
        assertEquals(3, queue.head());
        assertEquals(4, queue.head());
        assertEquals(1, queue.head());
    }

    @Test
    public void head() {
        PriorityQueue queue = new PriorityQueue(3);

        assertEquals(0, queue.head());
        assertEquals(1, queue.head());
        assertEquals(2, queue.head());
    }

    @Test
    public void relax() {
        List<Pair<Integer, Integer>> firstPairs = Arrays.asList(new Pair<>(3, 2), new Pair<>(2, 5));
        TreeSet<Pair<Integer, Integer>> first = new TreeSet<>(Util.PAIR_COMPARATOR);
        first.addAll(firstPairs);
        List<Pair<Integer, Integer>> secondPairs = Arrays.asList(new Pair<>(2, 1), new Pair<>(4, 4));
        TreeSet<Pair<Integer, Integer>> second = new TreeSet<>(Util.PAIR_COMPARATOR);
        second.addAll(secondPairs);
        PriorityQueue queue = new PriorityQueue(5);

        queue.relax(0, first);

        assertEquals(Integer.valueOf(0), queue.get(0).getKey());
        assertEquals(Integer.valueOf(3), queue.get(1).getKey());
        assertEquals(Integer.valueOf(2), queue.get(2).getKey());
        assertEquals(Integer.valueOf(1), queue.get(3).getKey());
        assertEquals(Integer.valueOf(4), queue.get(4).getKey());

        queue.head();
        queue.relax(3, second);

        assertEquals(Integer.valueOf(2), queue.get(0).getKey());
        assertEquals(Integer.valueOf(3), queue.get(1).getKey());
        assertEquals(Integer.valueOf(4), queue.get(2).getKey());
        assertEquals(Integer.valueOf(1), queue.get(3).getKey());
    }
}