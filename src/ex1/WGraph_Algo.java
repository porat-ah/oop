package ex1;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;

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
        WGraph_DS ng = new WGraph_DS(g);
        return ng;
    }

    /**
     * Returns true if and only if (iff) there is a valid path from EVREY node to each
     * other node.
     *
     * @return
     */
    @Override
    public boolean isConnected() {
        if (g.nodeSize() != 0) {
            int n = g.nodeSize();
            Object[] nl = g.getV().toArray();
            ArrayDeque<node_info> q = new ArrayDeque<node_info>();
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

                }
            }
            return (prev.size() == n);
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
        return 0;
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
        return null;
    }

    /**
     * Saves this weighted (undirected) graph to the given
     * file name
     *
     * @param file - the file name (may include a relative path).
     * @return true - iff the file was successfully saved
     */
    @Override
    public boolean save(String file) {
        return false;
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
        return false;
    }
}
