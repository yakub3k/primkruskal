package prim;

import graph.ComeFrom;
import graph.Graph;
import graph.PriorityQueue;
import graph.Util;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Prim {
    public static final int START_VERTEX = 0;
    private static final int START_HEIGHT = 0;
    private final Graph graph;

    public Prim(Graph graph) {
        this.graph = graph;

    }

    public List<ComeFrom> mstTree() {
        int n = graph.getN();
        List<ComeFrom> comeFrom = new ArrayList<>(n);                               // drzewo spinajace z wagami
        Set<Pair<Integer, Integer>>[] neighbors = graph.getNeighbors();

        PriorityQueue queue = new PriorityQueue(n);

        for (int i = 0; i < n; i++) {
            ComeFrom current = queue.head();                                          // biorę pierwszy z kolejki
            comeFrom.add(current);                                                    // odkładam do odwiedzonych
            int currentId = current.getId();
            Set<Pair<Integer, Integer>> neighbor = neighbors[currentId];              // biorę jego sąsiadów
            queue.relax(currentId, neighbor);                                         // uaktualniam wagi w kolejce
        }
        return comeFrom;
    }

    public void retardedTree() {
        final int n = graph.getN();
        int[] parent = new int[n];

        int[] value = new int[n];
        int[] key = Util.init(n, Integer.MAX_VALUE);
        boolean[] visited = Util.init(n, false);

        key[0] = 0;
        parent[0] = -1;

        int[][] edge = graph.getEdge();
        for (int i = 0; i < n - 1; i++) {
            int next = getNext(key, visited);
            visited[next] = true;

            for (int j = 0; j < n; j++) {
                int weight = edge[i][j];
                if (!visited[j] && weight != 0 && weight < key[j]) {
                    parent[j] = next;
                    key[j] = weight;
                }
            }
        }

        for (int i = 1; i < n; i++)
            Util.log("" + parent[i] + " - " + i + "  " + edge[parent[i]][i]);

    }

    // minKey
    private int getNext(int[] key, boolean[] mstSet) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < graph.getN(); i++) {
            if (!mstSet[i] && key[i] <= min) {
                min = key[i];
                minIndex = i;
            }
        }

        return minIndex;
    }


    public List<Pair<Integer, Integer>> tree() {
        List<Pair<Integer, Integer>> tree = new ArrayList<>(graph.getN());
        Set<Pair<Integer, Integer>>[] neighbors = graph.getNeighbors();
        boolean[] visited = new boolean[graph.getN()];

        Set<Pair<Integer, Integer>> start = neighbors[START_VERTEX];
        visited[START_VERTEX] = true;
        tree.add(new Pair<>(START_VERTEX, START_HEIGHT));
        for (int i = 1; i < graph.getN(); i++) {
            for (Pair<Integer, Integer> next : start) {
                Integer nextKey = next.getKey();
                if (!visited[nextKey]) {
                    Util.log("visit: " + nextKey + " [" + next.getValue() + "]");
                    tree.add(next);
                    start = neighbors[nextKey];
                    visited[nextKey] = true;
                    break;
                }
            }
        }
        Util.log("finish " + tree.size());
        tree.stream()
                .map(p -> "p:" + p.getKey() + " v: " + p.getValue())
                .forEach(Util::log);
        return tree;
    }
}
