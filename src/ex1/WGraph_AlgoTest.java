package ex1;

import org.junit.jupiter.api.*;

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
        System.out.println(g2);
        System.out.println(g);
        assert g2.toString().equals(g.toString());
    }

    @Test
    void isConnected() {
        ag.init(g);
        assert !ag.isConnected() ;
        g.connect(10,9,1);
        assert ag.isConnected() ;
    }

    @Test
    void shortestPathDist() {
    }

    @Test
    void shortestPath() {
    }

    @Test
    void save() {
    }

    @Test
    void load() {
    }
}