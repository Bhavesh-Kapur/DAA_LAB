import java.util.Scanner;

// Class to represent an edge
class Edge {
    int src, dest, weight;

    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

// Class to represent a graph
class Graph {
    int V, E;
    Edge[] edges;

    Graph(int V, int E) {
        this.V = V;
        this.E = E;
        this.edges = new Edge[E];
    }
}

public class test1 {
    // Function to create a graph
    static Graph createGraph(int V, int E) {
        return new Graph(V, E);
    }

    // Function to add an edge to the graph
    static void addEdge(Graph graph, int src, int dest, int weight) {
        graph.edges[graph.E] = new Edge(src, dest, weight);
        graph.E++;
    }

    // Function to print the minimum spanning tree
    static void printMST(int[] parent, Graph graph) {
        System.out.println("Minimum Spanning Tree:");
        for (int i = 1; i < graph.V; i++) {
            int src = parent[i];
            int dest = i;
            int weight = 0;

            for (Edge edge : graph.edges) {
                if (edge.src == src && edge.dest == dest) {
                    weight = edge.weight;
                    break;
                }
            }

            System.out.println("(" + src + ", " + dest + ") - " + weight);
        }
    }

    // Function to construct and find the minimum spanning tree
    static void findMinimumSpanningTree(Graph graph) {
        int[] parent = new int[graph.V];
        int[] key = new int[graph.V];
        boolean[] mstSet = new boolean[graph.V];

        for (int i = 0; i < graph.V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < graph.V - 1; count++) {
            int u = -1;
            int min = Integer.MAX_VALUE;

            for (int v = 0; v < graph.V; v++) {
                if (!mstSet[v] && key[v] < min) {
                    u = v;
                    min = key[v];
                }
            }

            mstSet[u] = true;

            for (int v = 0; v < graph.V; v++) {
                if (!mstSet[v]) {
                    for (Edge edge : graph.edges) {
                        if (edge.src == u && edge.dest == v && edge.weight < key[v]) {
                            parent[v] = u;
                            key[v] = edge.weight;
                        }
                    }
                }
            }
        }

        printMST(parent, graph);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of vertices in the graph: ");
        int V = scanner.nextInt();
        System.out.print("Enter the number of edges in the graph: ");
        int E = scanner.nextInt();

        // Create a graph
        Graph graph = createGraph(V, E);

        System.out.println("Enter the edges in the format (src, dest, weight):");
        for (int i = 0; i < E; i++) {
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            int weight = scanner.nextInt();
            addEdge(graph, src, dest, weight);
        }

        // Find the minimum spanning tree
        findMinimumSpanningTree(graph);

        // No need to free memory in Java; it's automatically managed
    }
}
