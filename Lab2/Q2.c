//A sequence of integers seperated by space. Find maximum subsequence


#include <stdio.h>

int mss(int arr[], int size) {
    int maxe = arr[0]; // Maximum sum ending at the current position
    int cmax = arr[0];      // Maximum sum seen so far

    for (int i = 1; i < size; i++) {
        maxe = (arr[i] > maxe + arr[i]) ? arr[i] : maxe + arr[i];
        
        cmax = (maxe > cmax) ? maxe : cmax;
    }

    return cmax;
}

int main() {
    int n;
    printf("Enter the number of elements: ");
    scanf("%d", &n);

    int arr[n];
    printf("Enter the sequence of integers separated by space:\n");
    for (int i = 0; i < n; i++) {
        scanf("%d", &arr[i]);
    }

    int maxSum = mss(arr, n);
    printf("Maximum subsequence sum: %d\n", maxSum);

    return 0;
}
