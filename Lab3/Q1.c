#include <stdio.h>
#include <math.h>
void findDuplicates(int arr[], int n) {

    int sum = 0;
    int product = 1;

    for (int i = 0; i < n + 2; i++) {
        sum += arr[i];
        product *= arr[i];
    }

 
    int sumOfNumbers = (n * (n + 1)) / 2;
    int productOfNumbers = 1;

    for (int i = 1; i <= n; i++) {
        productOfNumbers *= i;
    }

    int diff = sum - sumOfNumbers;
    int quotient = product / productOfNumbers;

    int sqrtTerm = sqrt(diff * diff - 4 * quotient);
    int duplicate1 = (diff + sqrtTerm) / 2;
    int duplicate2 = (diff - sqrtTerm) / 2;

    printf("Duplicates: %d and %d\n", duplicate1, duplicate2);
}

int main() {
    printf("Enter number of elements: ");
    int n,i;
    scanf("%d", &n);
    int arr[100];

    for (i = 0; i < n + 2; i++) {
        printf("Enter element %d: ", i + 1);
        scanf("%d", &arr[i]);
    }

    findDuplicates(arr, n);

    return 0;
}