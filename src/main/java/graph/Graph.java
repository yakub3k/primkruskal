package graph;

import javafx.util.Pair;

import java.util.Set;
import java.util.TreeSet;

public class Graph {

    private final int n;
    private final Set<Pair<Integer, Integer>>[] neighbors;
    private int[][] edge;

    Graph(int n) {
        this.n = n;
        this.neighbors = new Set[n];
        for (int i = 0; i < n; i++) {
            neighbors[i] = new TreeSet<>(Util.PAIR_COMPARATOR);
        }
        this.edge = new int[n][n];
    }

    public void addEdge(int from, int to, int height) {
        this.edge[from][to] = height;
        this.edge[to][from] = height;
        this.neighbors[from].add(new Pair<>(to, height));
        this.neighbors[to].add(new Pair<>(from, height));
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

    public void showNeighborsSec() {
        StringBuilder result = new StringBuilder("\n");
        for (int i = 0; i < neighbors.length; i++) {
            result.append("[").append(i).append("] ");
            Set<Pair<Integer, Integer>> neighbor = neighbors[i];
            for (Pair<Integer, Integer> point : neighbor) {
                result.append(point.getKey())
                        .append(" (")
                        .append(point.getValue())
                        .append(") ");
            }
            result.append("\n");
        }
        Util.log("Neighbors:\n" + result.toString());
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

    public void getVertexes() {

    }
}
