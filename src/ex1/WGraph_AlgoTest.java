package ex1;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

class WGraph_AlgoTest {
    static WGraph_DS g;
    static WGraph_Algo ag;

    @BeforeEach
    void setUp() {
        g = new WGraph_DS();
        for (int i = 0; i < 11; i++) {
            g.addNode(i);
        }
        g.connect(0, 1, 5);//1
        g.connect(0, 2, 2);//2
        g.connect(0, 3, 1);//3
        g.connect(4, 1, 1);//4
        g.connect(6, 4, 1);//5
        g.connect(2, 10, 3);//6
        g.connect(3, 5, 1);//7
        g.connect(5, 10, 2);//8
        g.connect(2, 3, 1);//9
        g.connect(5, 7, 15);//10
        g.connect(6, 7, 2);//11
        g.connect(7, 8, 2);//12
        ag = new WGraph_Algo();
    }

    @Test
    void getGraph_init() {
        ag.init(g);
        assert ag.getGraph() == g;

    }

    @Test
    void copy() {
        ag.init(g);
        WGraph_DS g2 = (WGraph_DS) ag.copy();
        assert g2.toString().equals(g.toString());
    }

    @Test
    void isConnected() {
        ag.init(g);
        assert !ag.isConnected();
        g.connect(10, 9, 1);
        assert ag.isConnected();
    }

    @Test
    void shortestPathDist() {
        ag.init(g);
        double d = ag.shortestPathDist(0, 7);
        assert d == 9;
        d = ag.shortestPathDist(0, 0);
        assert d == 0;
        d = ag.shortestPathDist(100, 101);
        assert d == -1;
        d = ag.shortestPathDist(100, 100);
        assert d == -1;
        d = ag.shortestPathDist(0, 9);
        assert d == -1;
        g.connect(10,9,100.91);
        d = ag.shortestPathDist(0, 9);
        assert d == 104.91;

    }

    @Test
    void shortestPath() {
        ag.init(g);
        List<node_info> d = ag.shortestPath(0, 7);
        ArrayList<node_info> arr = new ArrayList<>();
        arr.add(g.getNode(0));
        arr.add(g.getNode(1));
        arr.add(g.getNode(4));
        arr.add(g.getNode(6));
        arr.add(g.getNode(7));
        assert d.equals(arr);
        d = ag.shortestPath(0, 0);
        arr= new ArrayList<>();
        arr.add(g.getNode(0));
        assert d.equals(arr);
        d = ag.shortestPath(100, 101);
        assert d == null;
        d = ag.shortestPath(100, 100);
        assert d == null;
        d = ag.shortestPath(0, 9);
        assert d == null;
        g.connect(10,9,100.91);
        d = ag.shortestPath(0, 9);
        arr= new ArrayList<>();
        arr.add(g.getNode(0));
        arr.add(g.getNode(3));
        arr.add(g.getNode(5));
        arr.add(g.getNode(10));
        arr.add(g.getNode(9));
        assert d.equals(arr);

    }

    @Test
    void save() {
        ag.init(g);
        assert ag.save("test.txt");
    }

    @Test
    void load() {
        ag.init(g);
        assert ag.load("test.txt");
        WGraph_DS g2 = (WGraph_DS) ag.getGraph();
        String ags = g2.toString();
        String gs = g.toString();
        assert ags.equals(gs);
    }
}