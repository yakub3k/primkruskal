package graph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GraphGenerator {

    public static final int MAX_WEIGHT = 9;

    public static Graph createFromFile(String filename) {
        try {
            List<String> lines = Files.lines(Paths.get(filename))
                    .collect(Collectors.toList());

            Integer n = Integer.valueOf(lines.get(0)); //vertex count
            int size = lines.size();
            Graph graph = new Graph(n);
            Util.log("create graph n:" + n);
            for (int i = 1; i < size; i++) {
                String[] edge = lines.get(i).split(";");
                int from = Integer.parseInt(edge[0]);
                int to = Integer.parseInt(edge[1]);
                int weight = Integer.parseInt(edge[2]);
                Util.log("add edge " + from + " -> " + to + " [" + weight + "]");
                graph.addEdge(from, to, weight);
            }
            return graph;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Graph randomGraph(int n, int percent) {
        int edgeCount = (n * (n - 1)) * percent / 200;      // ilość krawędzi
        edgeCount = edgeCount < n - 1 ? n - 1 : edgeCount;

        Graph graph = new Graph(n);
        setEdges(graph, n, edgeCount);
        return graph;
    }

    private static void setEdges(Graph graph, int n, int edgeCount) {
        Random random = new Random();
        int[] visited = new int[n];
        int maxEdgeCount = n * (n - 1) / 2;
        edgeCount = edgeCount >= maxEdgeCount ? maxEdgeCount : edgeCount;
        Util.log("set edge " + edgeCount);

        /* Graph minimum */
        int weigh;
        int current = 0;
        for (int i = 0; i < n - 1; ) {
            int selected = random.nextInt(n);
            if (selected != current && visited[selected] == 0) {
                weigh = random.nextInt(MAX_WEIGHT) + 1;
                Util.log("[Edge m] add random edge: " + current + " -> " + selected + " weight: " + weigh);
                graph.addEdge(selected, current, weigh);
                ++visited[current];
                ++visited[selected];
                current = selected;
                ++i;
            }
        }
        weigh = random.nextInt(MAX_WEIGHT) + 1;
        Util.log("[Edge m] add random edge: " + current + " -> " + 0 + " weight: " + weigh);
        graph.addEdge(0, current, weigh);   // ponowne odwiedzenei pierwszego
        ++visited[current];
        ++visited[0];

        /* Graph fulfill other edges */
        for (int i = n - 1; i < edgeCount; ) {
            int from = random.nextInt(n);
            int to = random.nextInt(n);
            if (from != to && !graph.isEdge(from, to)) {
                weigh = random.nextInt(MAX_WEIGHT) + 1;
                Util.log("[Edge o] selected: " + from + " -> " + to + " weight: " + weigh);
                graph.addEdge(from, to, weigh);
                ++visited[from];
                ++visited[to];
                i++;
            }
        }
    }
}
