package ex1;

import java.io.*;
import java.util.*;

/**
 * this class implements the interface src.weighted_graph_algorithms :
 * Thee interface represents an Undirected Weighted Graph Theory algorithms.
 * this class functions are inspired by
 * https://www.coursera.org/lecture/advanced-data-structures/core-dijkstras-algorithm-2ctyF
 * (shortestPath , shortestPathDist)
 * https://www.youtube.com/watch?v=oDqjPvD54Ss
 * (isConnected)
 */
public class WGraph_Algo implements weighted_graph_algorithms {
    private WGraph_DS g;

    public WGraph_Algo(WGraph_DS g) {
        this.init(g);
    }

    public WGraph_Algo() {
    }

    /**
     * Init the graph on which this set of algorithms operates on.
     *
     * @param g
     */
    @Override
    public void init(weighted_graph g) {
        if (g == null) throw new IllegalArgumentException(" graph can not be null");
        this.g = (WGraph_DS) g;
    }

    /**
     * Return the underlying graph of which this class works.
     *
     * @return
     */
    @Override
    public weighted_graph getGraph() {
        return g;
    }

    /**
     * Compute a deep copy of this weighted graph.
     *
     * @return
     */
    @Override
    public weighted_graph copy() {
        return new WGraph_DS(g);
    }

    /**
     * Returns true if and only if (iff) there is a valid path from every node to each
     * other node.
     *
     * @return
     */
    @Override
    public boolean isConnected() {
        if (g.nodeSize() != 0) {
            int n = g.nodeSize();
            Object[] nl = g.getV().toArray();
            ArrayDeque<node_info> q = new ArrayDeque<>();
            q.add((node_info) nl[0]);
            HashMap<Integer, node_info> prev = new HashMap<>(n);
            prev.put(((node_info) nl[0]).getKey(), (node_info) nl[0]);
            while (!q.isEmpty()) {
                node_info node = q.poll();
                Object[] ni = g.getV(node.getKey()).toArray();
                node_info j;
                for (Object i : ni) {
                    j = (node_info) i;
                    if ((null == prev.get(j.getKey()))) {
                        q.add(j);
                        prev.put(j.getKey(), node);
                    }
                    // if the node j doesn't have a parent in the parent list adds n to it
                }
            }
            // goes through every node in the graph that connected to the first node
            // and adds their parent to the parent list
            return (prev.size() == n);
            // check if the parent list size equals to the number of all the graph's nodes
        }
        return true;
    }

    /**
     * returns the length of the shortest path between src to dest
     * if no such path returns -1
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return
     */

    @Override
    public double shortestPathDist(int src, int dest) {
        Pair<Double, ArrayList<node_info>> p = ShortestPathFunc(src, dest);
        return p.getT();

    }

    /**
     * returns the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * if no such path --> returns null;
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public List<node_info> shortestPath(int src, int dest) {
        Pair<Double, ArrayList<node_info>> p = ShortestPathFunc(src, dest);
        if (p.getT() == -1)
            return null;
        return p.getE();
    }

    private Pair<Double, ArrayList<node_info>> ShortestPathFunc(int src, int dest) {
        Pair<Double, ArrayList<node_info>> h = new Pair<>();
        ArrayList<node_info> al = new ArrayList<>();
        if ((g.getNode(src) == null) || (g.getNode(dest) == null)) {
            h.put(-1.0, al);
            return h;
        }
        if (g.nodeSize() != 0) {
            g.reset();// sets all the tag to integer max value
            PriorityQueue<node_info> pq = new PriorityQueue(Comparator.comparing(node_info::getTag));
            HashMap<Integer, node_info> prev = new HashMap<>();
            pq.add(g.getNode(src));
            g.getNode(src).setTag(0);
            node_info n, m;
            double dist;
            while (!pq.isEmpty()) {
                n = pq.poll();
                if (n.getInfo().equals("nv"))
                    n.setInfo("v");
                // sets n to visited
                if (n.getKey() == dest) {
                    for (node_info at = g.getNode(dest); at != null; at = prev.get(at.getKey())) {
                        al.add(at);
                    }
                    // create the list of the shortest path to the dest node
                    Collections.reverse(al);
                    h.put(n.getTag(), al);
                    return h;
                }
                Object[] o = g.getV(n.getKey()).toArray();
                for (Object j : o) {
                    dist = n.getTag();
                    m = (node_info) j;
                    dist += g.getEdge(n.getKey(), m.getKey());
                    if (dist < m.getTag()) {
                        prev.put(m.getKey(), n);
                        m.setTag(dist);
                        pq.add(m);
                    }
                    // if the node m has a shorter path through n sets the information of m to it
                }
            }
            h.put(-1.0, al);
            return h;
            // if the path doest exist
        }
        h.put(0.0, al);
        return h;
        //if the path is empty meaning that the graph  has 0 nodes
    }

    /**
     * Saves this weighted (undirected) graph to the given
     * file name
     * this function was inspired by https://stackoverflow.com/questions/10654236/java-save-object-data-to-a-file
     *
     * @param file - the file name (may include a relative path).
     * @return true - iff the file was successfully saved
     */
    @Override
    public boolean save(String file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(g);
            oos.close();
            return true;
        } catch (IOException fileNotFoundException) {
            return false;
        }
    }

    /**
     * This method load a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     *
     * @param file - file name
     * @return true - iff the graph was successfully loaded.
     */
    @Override
    public boolean load(String file) {
        try {
            FileInputStream fin = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fin);
            g = (WGraph_DS) ois.readObject();
            ois.close();
            return true;
        } catch (ClassNotFoundException | IOException e) {
            return false;
        }
    }


    private static class Pair<T, E> {
        private T t;
        private E e;

        public void put(T t, E e) {
            this.t = t;
            this.e = e;
        }

        public T getT() {
            return t;
        }

        public E getE() {
            return e;
        }

    }
}
