package kruskal;

import graph.Edge;
import graph.Graph;
import graph.Util;

import java.util.ArrayList;
import java.util.List;

public class Kruskal {
    private static final int UNCOLORED = -1;
    private final int[] colors;
    private final Graph graph;

    public Kruskal(Graph graph) {
        this.graph = graph;
        this.colors = Util.init(graph.getN(), UNCOLORED);
    }

    @SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
    public List<Edge> msTree() {
        final Tintoretto tint = new Tintoretto();
        final List<List<Edge>> kruskal = new ArrayList<>();
        graph.getRawEdge()
                .stream()
                .sorted()
                .forEach(edge -> {
                    int from = edge.getFrom();
                    int to = edge.getTo();
                    if (colors[from] == UNCOLORED) {
                        if (colors[to] == UNCOLORED) {
                            int color = tint.getNewColor();
                            ArrayList<Edge> array = new ArrayList<>();
                            array.add(edge);
                            kruskal.add(array);
                            colors[from] = color;
                            colors[to] = color;
                        } else {
                            colors[from] = colors[to];
                            kruskal.get(colors[to]).add(edge);
                        }
                    } else {
                        if (colors[to] == UNCOLORED) {
                            colors[to] = colors[from];
                            kruskal.get(colors[from]).add(edge);
                        } else {
                            if (colors[to] != colors[from]) {
                                List<Edge> edgeToAdd = kruskal.get(colors[to]);
                                edgeToAdd.add(edge);
                                edgeToAdd.addAll(kruskal.get(colors[from]));
                                repaint(colors[from], colors[to]);
                            }
                        }
                    }
                });

        Util.log("Kruskal finish ");
        return kruskal.get(colors[0]);
    }

    private boolean repaint(int baseColor, int newColor) {
        boolean colorful = true;
        for (int i = 0; i < colors.length; i++) {
            if (colors[i] == baseColor) {
                colors[i] = newColor;
            } else if (colorful && colors[i] != newColor) {
                colorful = false;
            }
        }
        return colorful;
    }

    class Tintoretto {
        private int color = UNCOLORED;

        public int getNewColor() {
            return ++color;
        }
    }
}
