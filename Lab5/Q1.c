#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int rollNumber;
    char name[50];
} Student;

void merge(Student arr[], int left, int mid, int right) {
    int n1 = mid - left + 1;
    int n2 = right - mid;

    Student leftArray[n1];
    Student rightArray[n2];

    for (int i = 0; i < n1; i++) {
        leftArray[i] = arr[left + i];
    }
    for (int j = 0; j < n2; j++) {
        rightArray[j] = arr[mid + 1 + j];
    }

    int i = 0, j = 0, k = left;

    while (i < n1 && j < n2) {
        if (leftArray[i].rollNumber <= rightArray[j].rollNumber) {
            arr[k] = leftArray[i];
            i++;
        } else {
            arr[k] = rightArray[j];
            j++;
        }
        k++;
    }

    while (i < n1) {
        arr[k] = leftArray[i];
        i++;
        k++;
    }

    while (j < n2) {
        arr[k] = rightArray[j];
        j++;
        k++;
    }
}

void mergeSort(Student arr[], int left, int right) {
    if (left < right) {
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }
}

int main() {
    // Read student data from a pre-existing input file
    FILE *inputFile = fopen("input.txt", "r");
    if (inputFile == NULL) {
        perror("Error opening input file");
        return 1;
    }

    Student students[100]; // Assuming a maximum of 100 students, adjust as needed
    int n = 0; // Number of students read

    while (fscanf(inputFile, "%d %s", &students[n].rollNumber, students[n].name) == 2) {
        n++;
    }

    fclose(inputFile);

    // Sorting the students based on roll numbers
    mergeSort(students, 0, n - 1);

    // Save the sorted data to an output file
    FILE *outputFile = fopen("output.txt", "w");
    if (outputFile == NULL) {
        perror("Error opening output file");
        return 1;
    }

    for (int i = 0; i < n; i++) {
        fprintf(outputFile, "%d %s\n", students[i].rollNumber, students[i].name);
    }

    fclose(outputFile);

    printf("Sorting completed. Sorted data saved in 'output.txt' file.\n");

    return 0;
}