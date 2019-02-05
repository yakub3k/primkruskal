package graph;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Util {
    public static final Comparator<Pair<Integer, Integer>> PAIR_COMPARATOR = (a, b) -> {
        int x = a.getValue();
        int y = b.getValue();
        int valueCompare = Integer.compare(x, y);
        if (valueCompare == 0) {
            return Integer.compare(a.getKey(), b.getKey());
        }
        return valueCompare;
    };

    public static void log(String log) {
        System.out.println(String.format("[Graph] %s", log));
    }

    public static int[] init(int n, int initValue) {
        int[] array = new int[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = initValue;
        }
        return array;
    }

    public static int[] initProgress(int n) {
        int[] array = new int[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        return array;
    }

    public static boolean[] init(int n, boolean defValue) {
        boolean[] booleans = new boolean[n];
        for (int i = 0; i < n; i++) {
            booleans[i] = false;
        }
        return booleans;
    }

    public static List<Pair<Integer, Integer>> init(int n) {
        List<Pair<Integer, Integer>> list = new ArrayList<>(n);
        list.add(new Pair<>(0, 0));
        for (int i = 1; i < n; i++) {
            list.add(new Pair<>(i, Integer.MAX_VALUE));
        }
        return list;
    }

    public static void show(List<Edge> tree) {
        log(" --- Show tree : " + tree.size() + " --- ");
        tree.forEach(e -> log("from: " + e.getFrom() + " to: " + e.getTo() + " weight:" + e.getWeight()));
        log(" --- Show end --- ");
    }
}
