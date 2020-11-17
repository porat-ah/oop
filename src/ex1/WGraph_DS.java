package ex1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

/**
 * this class implements the interface weighted_graph :
 * The interface represents an undirectional weighted graph.
 */

public class WGraph_DS implements weighted_graph, Serializable {
    private HashMap<Integer, node_info> nodes;
    private int num_edge;
    private int mc;

    public WGraph_DS() {
        nodes = new HashMap<Integer, node_info>(1_000_000);
        mc = 0;
        num_edge = 0;
    }

    public WGraph_DS(WGraph_DS g) {
        this.mc = g.mc;
        this.num_edge = g.num_edge;
        nodes = new HashMap<>();
        for (int i : g.nodes.keySet()) {
            Node n = (Node) g.getNode(i);
            Node temp = new Node(n.getKey());
            nodes.put(i, temp);
        }
        for (int i : g.nodes.keySet()) {
            Node temp = (Node) nodes.get(i);
            Node temp_2 = (Node) g.nodes.get(i);
            int a;
            for (Object j : temp_2.getNi()) {
                a = (Integer) j;
                temp.addNi(a, temp_2.getDist(a));
            }
        }
    }

    /**
     * resets the graph for the algorithms
     */
    public void reset() {
        Object[] o = nodes.values().toArray();
        node_info n;
        for (Object j : o) {
            n = (node_info) j;
            double d = (double) Integer.MAX_VALUE;
            n.setTag(d);
            n.setInfo("nv");
        }
    }

    /**
     * return the node_data by the node_id,
     *
     * @param key - the node_id
     * @return the node_data by the node_id, null if none.
     */
    @Override
    public node_info getNode(int key) {
        return nodes.get(key);
    }

    /**
     * return true iff (if and only if) there is an edge between node1 and node2
     *
     * @param node1
     * @param node2
     * @return
     */
    @Override
    public boolean hasEdge(int node1, int node2) {
        if (this.getNode(node1) == null || this.getNode(node2) == null) return false;
        if (node1 == node2) return true;
        Node n = (Node) this.getNode(node1);
        return n.hasNi(node2);
    }

    /**
     * return the weight if the edge (node1, node2) exist.
     * in case there is no such egde return -1
     *
     * @param node1
     * @param node2
     * @return
     */
    @Override
    public double getEdge(int node1, int node2) {
        if (nodes.get(node1) == null || nodes.get(node2) == null) return -1;
        Node n = (Node) nodes.get(node1);
        return n.getDist(node2);
    }

    /**
     * add a new node to the graph with the given key.
     *
     * @param key
     */
    @Override
    public void addNode(int key) {
        if (this.getNode(key) == null) {
            Node m = new Node(key);
            nodes.put(key, m);
            mc++;
        }
    }

    /**
     * Connect an edge between node1 and node2, with an edge with weight >=0.
     *
     * @param node1
     * @param node2
     * @param w
     */
    @Override
    public void connect(int node1, int node2, double w) {
        Node n1 = (Node) this.getNode(node1);
        Node n2 = (Node) this.getNode(node2);
        if (n1 != null && n2 != null) {
            if (!n1.hasNi(node2)) num_edge++;
            n1.addNi(node2, w);
            n2.addNi(node1, w);
            mc++;
        }
    }

    /**
     * This method return a pointer (shallow copy) for a
     * Collection representing all the nodes in the graph.
     *
     * @return Collection<node_info>
     */
    @Override
    public Collection<node_info> getV() {
        return nodes.values();
    }

    /**
     * This method returns a Collection containing all the
     * nodes connected to node_id
     *
     * @param node_id
     * @return Collection<node_info>
     */
    @Override
    public Collection<node_info> getV(int node_id) {
        if (getNode(node_id) == null) return null;
        Node m = (Node) nodes.get(node_id);
        ArrayList<node_info> node_list = new ArrayList<>();
        int i;
        for (Object j : m.getNi()) {
            i = (Integer) j;
            node_list.add(nodes.get(i));
        }
        return node_list;
    }

    /**
     * Delete the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     *
     * @param key
     * @return the data of the removed node (null if none).
     */
    @Override
    public node_info removeNode(int key) {
        if (this.getNode(key) != null) {
            Node n = (Node) nodes.get(key);
            int i;
            for (Object j : n.getNi()) {
                i = (Integer) j;
                this.removeEdge(key, i);
            }
            mc++;
            nodes.remove(key);
            return n;
        }
        return null;
    }

    /**
     * Delete the edge from the graph,
     *
     * @param node1
     * @param node2
     */
    @Override
    public void removeEdge(int node1, int node2) {
        if (this.hasEdge(node1, node2)) {
            Node n1 = (Node) nodes.get(node1);
            Node n2 = (Node) nodes.get(node2);
            if (n1.hasNi(node2)) {
                n1.removeNode(node2);
                n2.removeNode(node1);
                mc++;
                num_edge--;
            }
        }
    }

    /**
     * return the number of vertices (nodes) in the graph.
     *
     * @return
     */
    @Override
    public int nodeSize() {
        return nodes.size();
    }

    /**
     * return the number of edges (undirectional graph).
     *
     * @return
     */
    @Override
    public int edgeSize() {
        return num_edge;
    }

    /**
     * return the Mode Count - for testing changes in the graph.
     *
     * @return
     */
    @Override
    public int getMC() {
        return mc;
    }

    public String toString() {
        return "WGraph_DS{" +
                "nodes=" + nodes.values() +
                ", num_edge=" + num_edge +
                ", mc=" + mc +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WGraph_DS other = (WGraph_DS) o;
        boolean b = true;
        Node n, m;
        if (this.nodeSize() != other.nodeSize()) return false;
        for (int i : this.nodes.keySet()) {
            n = (Node) this.getNode(i);
            m = (Node) other.getNode(i);
            b = b && n.equals(m);
        }
        return num_edge == other.num_edge &&
                mc == other.mc && b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodes, num_edge, mc);
    }

    private class Node implements node_info, Serializable {
        private int key;
        private String info;
        private double tag;
        private HashMap<Integer, Double> nodes;

        public Node(int key) {
            this.key = key;
            nodes = new HashMap<>(10);
        }

        /**
         * this function check if the key is a neighbor of this node
         *
         * @param key
         * @return boolean
         */
        public boolean hasNi(int key) {
            return (nodes.get(key) != null) || this.key == key;
        }

        /**
         * this function adds the given node as a neighbor to the list of neighbors
         *
         * @param key
         * @param dis
         */
        public void addNi(int key, double dis) {
            if (!hasNi(key)) {
                nodes.put(key, dis);
            }
        }

        /**
         * this function removes the given node from the neighbors list
         *
         * @param key
         */
        public void removeNode(int key) {
            nodes.remove(key);
        }

        /**
         * this function return the dist between the node to the
         * node represented by the given node_id
         * in case there are not neighbors return -1
         *
         * @param key
         * @return
         */
        public double getDist(int key) {
            if (this.key == key) return 0;
            if (this.hasNi(key)) return nodes.get(key);
            return -1;
        }

        /**
         * return an array of all the node's neighbors node_ids
         *
         * @return
         */
        public Object[] getNi() {
            Object[] arr = nodes.keySet().toArray();
            return arr;
        }

        /**
         * Return the key (id) associated with this node.
         *
         * @return key
         */
        @Override
        public int getKey() {
            return this.key;
        }

        /**
         * the getter of info
         *
         * @return info
         */
        @Override
        public String getInfo() {
            return this.info;
        }

        /**
         * the setter of info
         *
         * @param s
         */
        @Override
        public void setInfo(String s) {
            this.info = s;
        }

        /**
         * the getter of tag
         *
         * @return tag
         */
        @Override
        public double getTag() {
            return this.tag;
        }

        /**
         * the setter of tag
         *
         * @param t - the new value of the tag
         */
        @Override
        public void setTag(double t) {
            this.tag = t;
        }

        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", info='" + info +
                    ", tag=" + tag +
                    ", neighbors id=" + nodes.keySet() +
                    ", neighbors dist =" + nodes.values() +
                    '}' + "\n";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            boolean b = true;
            return key == node.key &&
                    Double.compare(node.tag, tag) == 0 &&
                    Objects.equals(info, node.info) &&
                    Objects.equals(nodes, node.nodes);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, info, tag, nodes);
        }
    }
}

