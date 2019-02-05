package graph;

public class ComeFrom {
    private final int id;
    private final int weight;
    private final int from;

    public ComeFrom(int id, int weight, int from) {
        this.from = from;
        this.weight = weight;
        this.id = id;
    }

    public int getFrom() {
        return from;
    }

    public int getWeight() {
        return weight;
    }

    public int getId() {
        return id;
    }
}
