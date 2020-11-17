Assignment 1
============

Java implementation of an undirected weighted graph.
-------------------------------------------------------

A java implementation of undirected weighted graph, 
that implements the interface "weighted_graph".
The implementation uses a private class "Node" that implements the interface "node_info".

Java implementation of Dijkstra's Algorithm
--------------------------------------------

A java implementation of Dijkstra's Algorithm , 
for finding the shortest path length between two nodes in an undirected weighted graph.
this implementation implements the interface "weighted_graph_algorithms".
The implementation was ispired by  "Advanced Data Structures in Java" course on coursera,
made by The University of California San Diego.

Java implementation of BFS algorithm
------------------------------------

A java implementation of BFS Algorithm,
for finding if an undirected graph is connected.
The implementation was ispired by the youtube video :
"Breadth First Search Algorithm | Shortest Path | Graph Theory"
made by "WilliamFiset".

Links
=====
Dijkstra's Algorithm:
https://www.coursera.org/lecture/advanced-data-structures/core-dijkstras-algorithm-2ctyF

BFS algorithm:
https://www.youtube.com/watch?v=oDqjPvD54Ss

Clone:
---------
````
git clone --single-branch --branch task1 https://github.com/porat-ah/oop.git
````
A simple run:
=============
main
-----
````
package ex1;
public class Main {
    public static void main(String[] args) {
        WGraph_DS graph = new WGraph_DS();
        for (int i = 0; i < 7; i++) {
            graph.addNode(i);
        }
        graph.connect(0,1,2);
        graph.connect(0,2,4);
        graph.connect(3,1,9);
        graph.connect(1,2,1);
        graph.connect(2,4,2);
        graph.connect(4,5,1);
        graph.connect(3,5,4);
        graph.connect(3,6,1);
        System.out.println(graph.hasEdge(0,1));
        System.out.println(graph.getEdge(0,1));
        WGraph_Algo ag = new WGraph_Algo();
        ag.init(graph);
        System.out.println(ag.isConnected());
        System.out.println(ag.shortestPathDist(0,6));
        System.out.println(ag.shortestPath(0,6));
    }
}
````
Output:
-------
 ````
true
2.0
true
11.0
[Node{key=0, info='v, tag=0.0, neighbors id=[1, 2], neighbors dist =[2.0, 4.0]}
, Node{key=1, info='v, tag=2.0, neighbors id=[0, 2, 3], neighbors dist =[2.0, 1.0, 9.0]}
, Node{key=2, info='v, tag=3.0, neighbors id=[0, 1, 4], neighbors dist =[4.0, 1.0, 2.0]}
, Node{key=4, info='v, tag=5.0, neighbors id=[2, 5], neighbors dist =[2.0, 1.0]}
, Node{key=5, info='v, tag=6.0, neighbors id=[3, 4], neighbors dist =[4.0, 1.0]}
, Node{key=3, info='v, tag=10.0, neighbors id=[1, 5, 6], neighbors dist =[9.0, 4.0, 1.0]}
, Node{key=6, info='v, tag=11.0, neighbors id=[3], neighbors dist =[1.0]}
]
````
The Graph:
----------

![graph simple run](/images/graph.png)