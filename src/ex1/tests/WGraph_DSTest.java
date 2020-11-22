package ex1.tests;

import ex1.src.WGraph_DS;
import ex1.src.node_info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WGraph_DSTest {
    static WGraph_DS g;

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
    }

    @Test
    void getNode() {
        for (int i = 0; i < 11; i++) {
            System.out.println(g.getNode(i));
        }
        System.out.println(g.hashCode());
    }

    @Test
    void hasEdge() {
        assert g.hasEdge(0, 1);
        assert !g.hasEdge(0, 0);
        assert !g.hasEdge(0, 100);
        assert !g.hasEdge(0, 4);
        assert !g.hasEdge(100, 100);
    }

    @Test
    void getEdge() {
        double x = g.getEdge(5, 7);
        assert 15 == x;
        x = g.getEdge(0, 10);
        assert -1 == x;
        x = g.getEdge(0, 100);
        assert -1 == x;
        x = g.getEdge(100, 100);
        assert -1 == x;
        x = g.getEdge(0, 0);
        assert 0 == x;
        x = g.getEdge(101, 100);
        assert -1 == x;

    }

    @Test
    void addNode() {
        g.addNode(11);
        assert g.getNode(11) != null;
    }

    @Test
    void connect() {
        assert !g.hasEdge(9, 10);
        g.connect(9, 10, 8);
        assert g.hasEdge(9, 10);
    }

    @Test
    void getV() {
        assert g.getV() != null;
        Object[] arr = g.getV().toArray();
        for (int i = 0; i < arr.length; i++) {
            assert arr[i] == g.getNode(i);
        }
    }

    @Test
    void testGetV() {
        assert g.getV(0) != null;
        Object[] arr = g.getV(0).toArray();
        node_info n;
        StringBuilder s;
        s = new StringBuilder();
        for (Object o : arr) {
            n = (node_info) o;
            assert n == g.getNode(n.getKey());
            s.append(n.getKey());
        }
        assert s.toString().equals("123");
    }

    @Test
    void removeNode() {
        g.removeNode(11);
        assert g.getV(11) == null;
        assert g.getNode(11) == null;
        Object[] arr = g.getV(3).toArray();
        node_info n;
        node_info m;
        boolean b;
        n = g.removeNode(3);
        assert n.getKey() == 3;
        for (Object o : arr) {
            n = (node_info) o;
            assert !g.hasEdge(3, n.getKey());
            Object[] arr2 = g.getV(n.getKey()).toArray();
            b = true;
            for (Object value : arr2) {
                m = (node_info) value;
                if (m.getKey() == 3)
                    b = false;
                assert b;
            }
        }
        g.addNode(3);
        g.connect(0, 3, 1);
        g.connect(3, 5, 1);
        g.connect(2, 3, 1);
    }

    @Test
    void removeEdge() {
        g.connect(10,9,8);
        g.removeEdge(10,9);
        assert !g.hasEdge(10,9);
        node_info m;
        Object[] arr = g.getV(10).toArray();
        boolean b = true;
        for (Object o : arr) {
            m = (node_info) o;
            if (m.getKey() == 9)
                b = false;
            assert b;
        }
        g.removeEdge(10,0);
        g.removeEdge(100,0);
        g.removeEdge(0,0);
        g.removeEdge(100,100);
    }

    @Test
    void nodeSize() {
        assert g.nodeSize() == 11;
        g.removeNode(9);
        assert g.nodeSize() == 10;
    }

    @Test
    void edgeSize() {
        assert g.edgeSize() == 12;
        g.connect(10,9,8);
        assert g.edgeSize() == 13;
        g.removeEdge(10,9);
        assert g.edgeSize() == 12;
        g.connect(1,4,2);
        assert g.edgeSize() == 12;
        g.removeNode(3);
        assert g.edgeSize() == 9;
    }

    @Test
    void getMC() {
        assert g.getMC() == 23;
        g.connect(1,3,1);
        assert g.getMC() == 24;
        g.removeEdge(1,3);
        assert g.getMC() == 25;
        g.removeEdge(0,3);
        assert g.getMC() == 26;
        g.connect(0,3,1);
        assert g.getMC() == 27;
        g.removeNode(3);
        assert g.getMC() == 31;
    }
    @Test
    void graph_limits(){
        WGraph_DS g1 = new WGraph_DS();
        for (int i = 0; i < 1_000_000; i++) {
            g1.addNode(i);
        }
        int j = 1;
        for (int i = 0; i < 500_000; i++) {
            while(j%11 != 0) {
                g1.connect(i,j,5);
                j++;
            }
            j++;
        }
    }
}