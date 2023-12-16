import java.util.Scanner;

public class bell {
    static class Edge {
        int source, destination, weight;

        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    static class Graph {
        int V, E; // Number of vertices and edges
        Edge[] edges;

        Graph(int V, int E) {
            this.V = V;
            this.E = E;
            edges = new Edge[E];
        }
    }

    // Function to initialize the distance array
    static void initializeDistance(int[] distance, int source, int V) {
        for (int i = 0; i < V; i++) {
            distance[i] = Integer.MAX_VALUE; // Set all distances to infinity
        }
        distance[source] = 0; // Set the distance of the source to 0
    }

    // Function to relax an edge
    static void relax(int[] distance, int source, int destination, int weight) {
        if (distance[source] != Integer.MAX_VALUE && distance[source] + weight < distance[destination]) {
            distance[destination] = distance[source] + weight;
        }
    }

    // Bellman-Ford algorithm to find shortest paths
    static int bellmanFord(Graph graph, int source, int[] distance) {
        int V = graph.V;
        int E = graph.E;

        initializeDistance(distance, source, V);

        // Relaxation step (V-1) times
        for (int i = 1; i < V; i++) {
            for (int j = 0; j < E; j++) {
                int u = graph.edges[j].source;
                int v = graph.edges[j].destination;
                int weight = graph.edges[j].weight;
                relax(distance, u, v, weight);
            }
        }

        // Check for negative weight cycles
        for (int i = 0; i < E; i++) {
            int u = graph.edges[i].source;
            int v = graph.edges[i].destination;
            int weight = graph.edges[i].weight;
            if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v]) {
                return 0; // Negative weight cycle found
            }
        }

        return 1; // No negative weight cycle found
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int V, E;

        System.out.print("Enter the number of vertices and edges: ");
        V = scanner.nextInt();
        E = scanner.nextInt();

        Graph graph = new Graph(V, E);

        System.out.println("Enter the edges (source, destination, weight):");
        for (int i = 0; i < E; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.edges[i] = new Edge(source, destination, weight);
        }

        int source;
        System.out.print("Enter the source vertex: ");
        source = scanner.nextInt();

        int[] distance = new int[V];

        if (bellmanFord(graph, source, distance) == 1) {
            System.out.println("Shortest distances from the source vertex " + source + ":");
            for (int i = 0; i < V; i++) {
                System.out.println("Vertex " + i + ": " + distance[i]);
            }
        } else {
            System.out.println("Negative weight cycle detected. No shortest paths exist.");
        }
    }
}
