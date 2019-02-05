package graph;

public class Edge implements Comparable<Edge> {
    private final int from;
    private final int to;
    private final int weight;

    public Edge(int to, int weight, int from) {
        this.from = from;
        this.weight = weight;
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public int getWeight() {
        return weight;
    }

    public int getTo() {
        return to;
    }


    @Override
    public int compareTo(Edge other) {
        int compare = Integer.compare(weight, other.getWeight());
        if (compare != 0) {
            return compare;
        }
        compare = Integer.compare(from, other.getFrom());

        return compare == 0
                ? Integer.compare(to, other.getTo())
                : compare;
    }

    @Override
    public String toString() {
        return "Edge{ from=" + from + ", to=" + to + ", weight=" + weight + '}';
    }
}
