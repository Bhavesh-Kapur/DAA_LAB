#include <stdio.h>
#include <stdlib.h>

int findDuplicate(int arr[], int n) {
    int tortoise = arr[0];
    int hare = arr[0];

    // Phase 1: Detect if there is a cycle
    do {
        tortoise = arr[tortoise];
        hare = arr[arr[hare]];
    } while (tortoise != hare);

    // Phase 2: Find the start of the cycle (duplicate element)
    tortoise = arr[0];
    while (tortoise != hare) {
        tortoise = arr[tortoise];
        hare = arr[hare];
    }

    return hare;
}

int main() {
    int n, i;

    printf("Enter the value of n: ");
    scanf("%d", &n);

    int *arr = (int *)malloc((n + 3) * sizeof(int));

    printf("Enter the elements of the sequence: \n");
    for (i = 0; i < n + 3; i++)
        scanf("%d", &arr[i]);

    int duplicate = findDuplicate(arr, n + 3);

    printf("The element that appears twice is: %d\n", duplicate);

    free(arr);

    return 0;
}