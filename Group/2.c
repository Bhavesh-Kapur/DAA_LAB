#include <stdio.h>
#include <stdlib.h>

int comparator(const void* p1, const void* p2)
{
    const int(*x)[3] = p1;
    const int(*y)[3] = p2;

    return (*x)[2] - (*y)[2];
}

void makeSet(int parent[], int rank[], int n)
{
    for (int i = 0; i < n; i++) {
        parent[i] = i;
        rank[i] = 0;
    }
}

int findParent(int parent[], int component)
{
    if (parent[component] == component)
        return component;

    return parent[component] = findParent(parent, parent[component]);
}

void unionSet(int u, int v, int parent[], int rank[], int n)
{
    u = findParent(parent, u);
    v = findParent(parent, v);

    if (rank[u] < rank[v]) {
        parent[u] = v;
    }
    else if (rank[u] > rank[v]) {
        parent[v] = u;
    }
    else {
        parent[v] = u;
        rank[u]++;
    }
}

void kruskalAlgo(int n, int matrix[n][n])
{
    int edgeCount = 0;
    int edge[n * n][3];

    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            if (matrix[i][j] != 0) {
                edge[edgeCount][0] = i;
                edge[edgeCount][1] = j;
                edge[edgeCount][2] = matrix[i][j];
                edgeCount++;
            }
        }
    }

    qsort(edge, edgeCount, sizeof(edge[0]), comparator);

    int parent[n];
    int rank[n];

    makeSet(parent, rank, n);

    int minCost = 0;

    printf("Following are the edges in the constructed MST\n");
    for (int i = 0; i < edgeCount; i++) {
        int v1 = findParent(parent, edge[i][0]);
        int v2 = findParent(parent, edge[i][1]);
        int wt = edge[i][2];

        if (v1 != v2) {
            unionSet(v1, v2, parent, rank, n);
            minCost += wt;
            printf("%d -- %d == %d\n", edge[i][0], edge[i][1], wt);
        }
    }

    printf("Minimum Cost Spanning Tree: %d\n", minCost);

    // Retrieve and print the shortest path between two vertices (e.g., 0 and 5)
    int src = 0;
    int dest = 5;

    printf("Shortest path between %d and %d:\n", src, dest);

    while (dest != src) {
        printf("%d -- ", dest);
        dest = findParent(parent, dest);
    }

    printf("%d\n", src);
}

void readAdjacencyMatrix(int n, int matrix[n][n])
{
    FILE* file = fopen("input.txt", "r");

    if (file == NULL) {
        perror("Error opening the file for reading.\n");
        exit(1);
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            fscanf(file, "%d", &matrix[i][j]);
        }
    }

    fclose(file);
}

int main()
{
    int n;

    printf("Enter the number of vertices: ");
    scanf("%d", &n);

    int matrix[n][n];

    readAdjacencyMatrix(n, matrix);

    kruskalAlgo(n, matrix);

    return 0;
}
