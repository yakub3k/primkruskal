package graph;

import javafx.util.Pair;

import java.util.List;
import java.util.Set;

public class PriorityQueue {
    private final int n;                                        // wielkosc kolejki
    private final List<Pair<Integer, Integer>> elements;        // wszystkie elementy kolejki
    private final int[] weight;                                 // wagi elementow
    private final int[] from;                                   // skąd przyszła ścieżka
    private final boolean[] visited;                            // oznaczanie odwiedzonych
    private int head;                                           // numer elementu glowy w tablicy

    /**
     * @param n długość kolejki
     */
    public PriorityQueue(int n) {
        this.n = n;
        this.head = 0;
        this.elements = Util.init(n);
        this.weight = Util.init(n, Integer.MAX_VALUE);
        this.from = Util.init(n, -1);
        this.weight[0] = 0;
        this.visited = Util.init(n, false);
    }

    public ComeFrom head() {
        Pair<Integer, Integer> element = elements.remove(0);
        Integer id = element.getKey();
        Integer value = element.getValue();
        visited[id] = true;
        return new ComeFrom(id, value,  from[id]);
    }

    public Pair<Integer, Integer> get(int i) {
        return elements.get(i);
    }

    public int getFrom(int i) {
        return from[i];
    }

    public void relax(int current, Set<Pair<Integer, Integer>> neighbors) {
        for (Pair<Integer, Integer> element : neighbors) {
            Integer next = element.getKey();
            Integer value = element.getValue();
            if (!visited[next] && (from[next] == -1 || weight[next] > value)) {
                weight[next] = value;
                from[next] = current;
                update(next, value);
            }
        }
    }

    void update(int number, int newHeight) {
        int start = 0;
        for (; start < elements.size(); start++) {
            Pair<Integer, Integer> current = elements.get(start);
            if (current.getKey().equals(number)) {
                elements.remove(start);
                break;
            }
        }
        int elementSize = elements.size();
        start = start >= elementSize ? elementSize - 1 : start;
        for (; start >= 0; start--) {
            Pair<Integer, Integer> next = elements.get(start);
            if (next.getValue() <= newHeight) {
                elements.add(start + 1, new Pair<>(number, newHeight));
                return;
            }
        }
        elements.add(0, new Pair<>(number, newHeight));
    }


}
