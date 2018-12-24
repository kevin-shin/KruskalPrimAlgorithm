# KruskalPrimAlgorithm

The Kruskal and Prim algorithms are two approaches to building a minimum spanning tree from a connected graph. They are both greedy approaches, meaning that at each iteration of the algorithm, they take the locally optimal choice. Both approaches can be shown to be correct, either by induction or by contradiction. Kruskal's algorithm chooses the cheapest edge to take given that this edge does not form a cycle, and Prim's algorithm builds a minimum spanning tree from the edges already added to the tree, starting from an arbitrary vertex. 

Minimum spanning trees are used in topics as varied as biology to network science, but have practical applications as well, such as the well known problem of paving a city's roads after a snowstorm in the most efficient way. As such, having a program which has capabiliies to initialize graphs as well as build minimum spanning trees is useful and convenient. In most graphs, we're interesting in the distance between two points, and this program is built such that the weight of an edge will be the (x,y) distance between the two vertices it connects. 

This program is split into a graph folder where graph-building objects are initialized and the algorithms are written as public methods. Thus, graph objects can be initialized in other files and access this method. For our implementation, we chose to apply the algorithms to a map of Macalester College (Saint Paul, MN), located in the app folder. The program is run by executing the main method in Main.java, initializing a Window object with preloaded vertices. 

The window contains a panel with the map of Macalester and preloaded vertices, and buttons with the following functions:
- Run Kruskal's: Takes the graph drawn on the panel and shows a step by step implementation of Kruskal's algorithm
- Run Prim's: Analogous to Run Kruskal's with Prim's Algorithm
- Complete Graph: Takes the set of vertices on the panel and draws a complete graph, where each vertex is connected to the other. In terms of geography, this represents the total possible routes from the locations drawn on the map
- Clear Graph: clears the graph of vertices and edges
- Toggle Edge Visibility: Useful function to see the map with just the vertices. Click once to hide edges and click again to reinstate them. 

####Testing for Correctness: 
Both Prim's algorithm and Krukskal's algorithm consistently produce the same graphs. We can prove that given distinct weights, a graph has a unique minimum spanning tree, but this is not guaranteed if there are equal weights in the graph. Thus, we do not necessarily expect the same results every single time, but equivalent minimum spanning trees is a good indicator that the algorithms are correct. The algorithms themselves were tested before implementing them into the JFrame.

####Further Improvements: 
Although distance is the default for weights, our program allows for edges to be instantiated with any weight, and so this program would be able to accomodate other metrics as well. The JFrame is defined to hold a map of Macalester, but this image can also be replaced, depending on how this program is used. Other maps can be uploaded into the program, or no image can be used, perhaps as a didactic tool to illustrate the algorithms. 

