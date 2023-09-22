import java.util.*;

class Graph {
    private int V;
    private List<Edge> edges;

    public Graph(int V) {
        this.V = V;
        this.edges = new ArrayList<>();
    }

    public void addEdge(String src, String dest, int weight) {
        edges.add(new Edge(src, dest, weight));
    }

    public List<Edge> kruskalMST() {
        List<Edge> result = new ArrayList<>();
        edges.sort(Comparator.comparingInt(Edge::getWeight));
        DisjointSet ds = new DisjointSet();

        for (Edge edge : edges) {
            String src = edge.getSrc();
            String dest = edge.getDest();

            if (!ds.inSameSet(src, dest)) {
                result.add(edge);
                ds.union(src, dest);
            }
        }
        return result;
    }

    public Map<String, List<Edge>> toAdjacentList() {
        Map<String, List<Edge>> adjList = new HashMap<>();
        for (Edge edge : edges) {
            adjList.computeIfAbsent(edge.getSrc(), k -> new ArrayList<>()).add(edge);
            adjList.computeIfAbsent(edge.getDest(), k -> new ArrayList<>()).add(edge);
        }
        return adjList;
    }
}

class Edge {
    private String src;
    private String dest;
    private int weight;

    public Edge(String src, String dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    public String getSrc() {
        return src;
    }

    public String getDest() {
        return dest;
    }

    public int getWeight() {
        return weight;
    }
}

class DisjointSet {
    private Map<String, String> parent;

    public DisjointSet() {
        parent = new HashMap<>();
    }

    public void makeSet(String node) {
        parent.put(node, node);
    }

    public String findSet(String node) {
        if (!parent.containsKey(node))
            return null;

        if (parent.get(node).equals(node))
            return node;
        return findSet(parent.get(node));
    }

    public boolean inSameSet(String a, String b) {
        String rootA = findSet(a);
        String rootB = findSet(b);

        return rootA != null && rootB != null && rootA.equals(rootB);
    }

    public void union(String a, String b) {
        String rootA = findSet(a);
        String rootB = findSet(b);

        if (rootA != null && rootB != null && !rootA.equals(rootB))
            parent.put(rootA, rootB);
    }
}

public class Q1 {
    public static void main(String[] args) {
        // Prompt the user to enter the number of nodes in the graph.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of nodes in the graph: ");
        int numNodes = scanner.nextInt();

        // Create a graph.
        Graph graph = new Graph(numNodes);

        // Add the nodes to the graph.
        for (int i = 0; i < numNodes; i++) {
            System.out.println("Enter the name of node " + i + ": ");
            String nodeName = scanner.next();
            graph.addEdge(nodeName, nodeName, 0);
        }

        // Prompt the user to enter the number of edges in the graph.
        System.out.println("Enter the number of edges in the graph: ");
        int numEdges = scanner.nextInt();

        // Add the edges to the graph.
        for (int i = 0; i < numEdges; i++) {
            System.out.println("Enter the names of the two nodes that edge " + i + " connects: ");
            String nodeName1 = scanner.next();
            String nodeName2 = scanner.next();

            System.out.println("Enter the weight of edge " + i + ": ");
            int edgeWeight = scanner.nextInt();

            graph.addEdge(nodeName1, nodeName2, edgeWeight);
        }

        // Find the minimum spanning tree.
        List<Edge> mst = graph.kruskalMST();

        // Print the minimum spanning tree to the console.
        System.out.println("The minimum spanning tree is:");
        for (Edge edge : mst) {
            System.out.println(edge.getSrc() + " - " + edge.getDest() + " (" + edge.getWeight() + ")");
        }
    }
}
