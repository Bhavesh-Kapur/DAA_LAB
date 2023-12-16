#include <stdio.h>
#include <stdlib.h>

// Structure to represent an item
struct Item {
    float weight;
    int value;
    int idx;
};

// Structure to represent a node in the branch and bound algorithm
struct Node {
    float ub; // Upper bound
    float lb; // Lower bound
    int level; // Level in the decision tree
    int flag; // Flag to indicate if the item is selected or not
    float tv; // Total value of selected items
    float tw; // Total weight of selected items
};

// Comparator to sort nodes based on lower bound
int sortByC(const void* a, const void* b) {
    return ((Node*)b)->lb > ((Node*)a)->lb ? 1 : -1;
}

// Comparator to sort items based on value/weight ratio
int sortByRatio(const void* a, const void* b) {
    return ((float)((Item*)b)->value / ((Item*)b)->weight) > ((float)((Item*)a)->value / ((Item*)a)->weight) ? 1 : -1;
}

// Function to assign values to a node
void assign(Node* a, float ub, float lb, int level, int flag, float tv, float tw) {
    a->ub = ub;
    a->lb = lb;
    a->level = level;
    a->flag = flag;
    a->tv = tv;
    a->tw = tw;
}

// Function to calculate the upper bound (best case) considering fractional knapsack
float upperBound(float tv, float tw, int idx, Item arr[], int size, float capacity) {
    float value = tv;
    float weight = tw;

    // Iterate over the remaining items and add them to the knapsack if possible
    for (int i = idx; i < size; i++) {
        if (weight + arr[i].weight <= capacity) {
            weight += arr[i].weight;
            value -= arr[i].value;
        } else {
            // If the remaining capacity is not enough for the entire item, add a fraction of it
            value -= ((float)(capacity - weight) / arr[i].weight) * arr[i].value;
            break;
        }
    }
    return value;
}

// Function to calculate the lower bound (worst case) without considering fractional part of items
float lowerBound(float tv, float tw, int idx, Item arr[], int size, float capacity) {
    float value = tv;
    float weight = tw;

    // Iterate over the remaining items and add them to the knapsack if possible
    for (int i = idx; i < size; i++) {
        if (weight + arr[i].weight <= capacity) {
            weight += arr[i].weight;
            value -= arr[i].value;
        } else {
            break;
        }
    }
    return value;
}

// Function to solve the 0/1 knapsack problem using branch and bound
void solve(Item arr[], int size, float capacity) {
    // Sort the items based on the value/weight ratio
    qsort(arr, size, sizeof(Item), sortByRatio);

    // Initialize nodes
    Node current, left, right;
    current.tv = current.tw = current.ub = current.lb = 0;
    current.level = 0;
    current.flag = 0;

    float minLB = 0, finalLB = __FLT_MAX__;
    current.tv = current.tw = current.ub = current.lb = 0;
    current.level = 0;
    current.flag = 0;

    // Array to store the current and final selection of items
    int currPath[size];
    int finalPath[size];

    // Priority queue to store nodes based on lower bounds
    Node pq[size];

    // Explore nodes in the priority queue
    int pqSize = 0;
    pq[pqSize++] = current;

    while (pqSize > 0) {
        qsort(pq, pqSize, sizeof(Node), sortByC);
        current = pq[--pqSize];

        // Prune if the upper bound is greater than the minimum lower bound
        if (current.ub > minLB || current.ub >= finalLB) {
            continue;
        }

        // Store the selection if it reaches the final level
        if (current.level != 0)
            currPath[current.level - 1] = current.flag;

        // Check if it's the last level and update the final selection if the lower bound is better
        if (current.level == size) {
            if (current.lb < finalLB) {
                for (int i = 0; i < size; i++)
                    finalPath[arr[i].idx] = currPath[i];
                finalLB = current.lb;
            }
            continue;
        }

        int level = current.level;

        // Explore the right node (exclude current item)
        assign(&right, upperBound(current.tv, current.tw, level + 1, arr, size, capacity),
               lowerBound(current.tv, current.tw, level + 1, arr, size, capacity),
               level + 1, 0, current.tv, current.tw);

        // Explore the left node (include current item)
        if (current.tw + arr[current.level].weight <= capacity) {
            left.ub = upperBound(current.tv - arr[level].value, current.tw + arr[level].weight, level + 1, arr, size, capacity);
            left.lb = lowerBound(current.tv - arr[level].value, current.tw + arr[level].weight, level + 1, arr, size, capacity);
            assign(&left, left.ub, left.lb, level + 1, 1, current.tv - arr[level].value, current.tw + arr[level].weight);
        } else {
            // If the left node cannot be inserted, stop it from getting added to the priority queue
            left.ub = left.lb = 1;
        }

        // Update minLB
        minLB = fmin(minLB, left.lb);
        minLB = fmin(minLB, right.lb);

        // Add nodes to the priority queue if their upper bounds are less than minLB
        if (minLB >= left.ub)
            pq[pqSize++] = left;
        if (minLB >= right.ub)
            pq[pqSize++] = right;
    }

    // Print the final result
    printf("Items taken into the knapsack are\n");
    for (int i = 0; i < size; i++) {
        if (finalPath[i])
            printf("1 ");
        else
            printf("0 ");
    }
    printf("\nMaximum profit is %.2f\n", -finalLB);
}

// Main function
int main() {
    int size = 4;
    float capacity = 15;

    // Create an array of items
    Item arr[] = {{10, 2, 0}, {10, 4, 1}, {12, 6, 2}, {18, 9, 3}};

    // Call the solve function to solve the knapsack problem
    solve(arr, size, capacity);

    return 0;
}
