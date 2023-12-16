#include <stdio.h>
#include <string.h>

int main() {
    int n, i, j, k;
    char temp[50];  // Assuming each name has a maximum length of 50 characters
    printf("Enter the number of names to be sorted: ");
    scanf("%d", &n);

    char names[n][50];  // Array to store names

    for (i = 0; i < n; i++) {
        printf("Enter name %d: ", i + 1);
        scanf("%s", names[i]);  // Read names as strings
    }

   // Insertion Sort in Descending Order
for (i = 1; i < n; i++) {
    for (j = i - 1; j >= 0; j--) {
        if (strcmp(names[i], names[j]) > 0) {  // Corrected condition for descending order
            break;
        }
    }
    if (i - j > 1) {
        strcpy(temp, names[i]);
        for (k = i; k > j + 1; k--) {
            strcpy(names[k], names[k - 1]);
        }
        strcpy(names[j + 1], temp);
    }
}


    // Sorted List Output in Descending Order
    printf("\nSorted list of names in descending order is:\n");
    for (i = n - 1; i >= 0; i--) {
        printf("%s\n", names[i]);
    }

    return 0;
}
