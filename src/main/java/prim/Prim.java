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
}
