import graph.Edge;
import graph.Graph;
import graph.GraphGenerator;
import kruskal.Kruskal;
import prim.Prim;

import java.util.List;

import static graph.Util.log;
import static graph.Util.show;


public class Runner {


    public static void modeA() {
        Graph graph = GraphGenerator.createFromFile("data.txt");
        Prim prim = new Prim(graph);
        List<Edge> treePrim = prim.msTree();
        show(treePrim);

        Kruskal kruskal = new Kruskal(graph);
        List<Edge> treeKruskal = kruskal.msTree();
        show(treeKruskal);
    }

    /**
     * wykonanie strumieniowe dla losowych grafów o zmiennej wielkości
     * z zakresu określonego przez użytkownik
     */
    public static void modeB(int loop) {

        for (int i = 0; i < loop; i++) {
            Graph graph = GraphGenerator.randomGraph(10, 20);
            show(new Prim(graph).msTree());
            show(new Kruskal(graph).msTree());
        }
    }

    public static void main(String[] args) {
        log("------------ Mode A ------------");
        modeA();
        log("------------ Mode B ------------");
        modeB(3);
    }
}
