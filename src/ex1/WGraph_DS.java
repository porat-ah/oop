package ex1;

import java.util.Collection;
import java.util.HashMap;

public class WGraph_DS implements weighted_graph {
    private HashMap<Integer, node_info> nodes;
    private int num_edge;
    private int mc;
    private int keys;

    /**
     * return the node_data by the node_id,
     *
     * @param key - the node_id
     * @return the node_data by the node_id, null if none.
     */
    @Override
    public node_info getNode(int key) {
        if (nodes.get(key) == null) return null;
        return nodes.get(key);
    }

    /**
     * return true iff (if and only if) there is an edge between node1 and node2
     * Note: this method should run in O(1) time.
     *
     * @param node1
     * @param node2
     * @return
     */
    @Override
    public boolean hasEdge(int node1, int node2) {
        if(this.getNode(node1) == null || this.getNode(node2) == null)return false;
        if (node1 == node2) return true;
        Node n = (Node) this.getNode(node1);
        if (n == null) return false;
        return n.hasNi(node2);
    }

    /**
     * return the weight if the edge (node1, node1). In case
     * there is no such edge - should return -1
     * Note: this method should run in O(1) time.
     *
     * @param node1
     * @param node2
     * @return
     */
    @Override
    public double getEdge(int node1, int node2) {
        return 0;
    }

    /**
     * add a new node to the graph with the given key.
     * Note: this method should run in O(1) time.
     * Note2: if there is already a node with such a key -> no action should be performed.
     *
     * @param key
     */
    @Override
    public void addNode(int key) {
        Node m = new Node(key);
        nodes.put(keys, m);
        mc++;
    }

    /**
     * Connect an edge between node1 and node2, with an edge with weight >=0.
     * Note: this method should run in O(1) time.
     * Note2: if the edge node1-node2 already exists - the method simply updates the weight of the edge.
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
            if (!n1.hasNi(node2)) {
                n1.addNi(n2);
                n2.addNi(n1);
                num_edge++;
                mc++;
            }
        }
    }

    /**
     * This method return a pointer (shallow copy) for a
     * Collection representing all the nodes in the graph.
     * @return Collection<node_data>
     */
    @Override
    public Collection<node_info> getV() {
        return nodes.values();
    }

    /**
     * This method returns a Collection containing all the
     * nodes connected to node_id
     * @param node_id
     * @return Collection<node_data>
     */
    @Override
    public Collection<node_info> getV(int node_id) {
        if (getNode(node_id) == null) return null;
        Node m = (Node) nodes.get(node_id);
        return m.getNi();
    }

    /**
     * Delete the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * @param key
     * @return the data of the removed node (null if none).
     */
    @Override
    public node_info removeNode(int key) {
        if (this.getNode(key) != null) {
            Node n = (Node) nodes.get(key);
            for (Object j : n.getNi().toArray()) {
                Node m = (Node) j;
                this.removeEdge(key, m.getKey());
            }
            mc++;
            nodes.remove(key);
            return n;
        }
        return null;
    }

    /**
     * Delete the edge from the graph,
     * @param node1
     * @param node2
     */
    @Override
    public void removeEdge(int node1, int node2) {
        if (this.hasEdge(node1, node2)) {
            Node n1 = (Node) nodes.get(node1);
            Node n2 = (Node) nodes.get(node2);
            if (n1.hasNi(node2) == true) {
                n1.removeNode(n2);
                n2.removeNode(n1);
                mc++;
                num_edge--;
            }
        }
    }

    /**
     * return the number of vertices (nodes) in the graph.
     * Note: this method should run in O(1) time.
     *
     * @return
     */
    @Override
    public int nodeSize() {
        return nodes.size();
    }

    /**
     * return the number of edges (undirectional graph).
     * Note: this method should run in O(1) time.
     *
     * @return
     */
    @Override
    public int edgeSize() {
        return num_edge;
    }

    /**
     * return the Mode Count - for testing changes in the graph.
     * Any change in the inner state of the graph should cause an increment in the ModeCount
     *
     * @return
     */
    @Override
    public int getMC() {
        return mc;
    }
    public String toString() {
        return "WGraph_DS{" +
                "nodes=" + nodes.keySet() +
                ", num_edge=" + num_edge +
                ", mc=" + mc +
                '}';
    }

    private class Node implements node_info {
        private int key;
        private String info;
        private int tag;
        private HashMap<Integer,node_info> nodes;

        public Node(int key){
            this.key = key;
            nodes = new HashMap<>(10);
        }

        /**
         * this function returns the list of all the node's neighbors
         * @return
         */
        public Collection<node_info> getNi() {
            return nodes.values();
        }

        /**
         * this function check if the key is a neighbor of this node
         * @param key
         * @return
         */
        public boolean hasNi(int key) {
            return (nodes.get(key) != null) || this.key == key;
        }

        /**
         * this function adds the given node as a neighbor to the list of neighbors
         * @param t
         */
        public void addNi(node_info t) {
            if(! hasNi(t.getKey())) {
                t = (Node) t;
                int key = t.getKey();
                nodes.put(key, t);
            }
        }

        /**
         * this function removes the given node from the neighbors list
         * @param node
         */
        public void removeNode(node_info node) {
            nodes.remove(node.getKey());
        }
        /**
         * Return the key (id) associated with this node.
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
         * @return tag
         */
        @Override
        public double getTag() {
            return this.tag;
        }

        /** the setter of tag
         * @param t - the new value of the tag
         */
        @Override
        public void setTag(double t) {
            this.tag = tag;
        }
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", ni=" + nodes.keySet() +
                    ", info='" + info +
                    ", tag=" + tag +
                    '}';
        }
    }
}

