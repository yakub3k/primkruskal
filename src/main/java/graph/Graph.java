package graph;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Graph {

    private final int n;
    private final Set<Pair<Integer, Integer>>[] neighbors;
    private int[][] edge;
    private final List<Edge> rawEdge;

    Graph(int n) {
        this.n = n;
        this.neighbors = new Set[n];
        for (int i = 0; i < n; i++) {
            neighbors[i] = new TreeSet<>(Util.PAIR_COMPARATOR);
        }
        this.edge = new int[n][n];
        this.rawEdge = new ArrayList<>();
    }

    public void addEdge(int from, int to, int weight) {
        this.rawEdge.add(new Edge(to, weight, from));
        this.edge[from][to] = weight;
        this.edge[to][from] = weight;
        this.neighbors[from].add(new Pair<>(to, weight));
        this.neighbors[to].add(new Pair<>(from, weight));
    }

    public String showNeighbors() {
        StringBuilder result = new StringBuilder("Neighbors").append("\n");
        int i = 0;
        for (Set<Pair<Integer, Integer>> neighbor : neighbors) {
            result.append("> ").append(i++).append(": ");
            for (Pair<Integer, Integer> vertex : neighbor) {
                result.append(vertex.getKey())
                        .append("[")
                        .append(vertex)
                        .append("] ")
                        .append("\n");
            }
        }
        return result.toString();
    }

    public boolean isEdge(int from, int to) {
        return this.edge[from][to] == 0
                && this.edge[to][from] == 0;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("\nint[][] graph = {\n");
        for (int i = 0; i < n; i++) {
            result.append(" { ");
            for (int j = 0; j < n; j++) {
                result.append(edge[i][j]).append(", ");
            }
            result.append("},\n");
        }
        result.append("}");

        return result.toString();
    }

    public int getN() {
        return n;
    }

    public Set<Pair<Integer, Integer>>[] getNeighbors() {
        return neighbors;
    }

    public int[][] getEdge() {
        return edge;
    }

    public List<Edge> getRawEdge() {
        return rawEdge;
    }
}
