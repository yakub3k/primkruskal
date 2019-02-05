import graph.ComeFrom;
import graph.Graph;
import graph.GraphGenerator;
import kruskal.Kruskal;
import prim.Prim;

import java.util.List;

import static graph.Util.show;


public class Runner {


    public static void modeA() {
        Graph graph = GraphGenerator.createFromFile("data.txt");
        Prim prim = new Prim(graph);
        List<ComeFrom> tree = prim.mstTree();
        show(tree);

        Kruskal kruskal = new Kruskal(graph);
    }

    /**
     * wykonanie strumieniowe dla losowych grafów o zmiennej wielkości
     * z zakresu określonego przez użytkownik
     */
    public static void modeB(int loop) {
        GraphGenerator.randomGraph(10, 20);
        for (int i = 0; i < loop; i++) {

        }
    }

    public static void main(String[] args) {
        modeA();
    }
}
