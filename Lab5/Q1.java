import java.io.*;
import java.util.*;

class Student {
    int rollNumber;
    String name;

    public Student(int rollNumber, String name) {
        this.rollNumber = rollNumber;
        this.name = name;
    }
}

public class Q1 {
    public static void merge(Student[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Student[] leftArray = new Student[n1];
        Student[] rightArray = new Student[n2];

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

    public static void mergeSort(Student[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Open the input file in append mode
        try {
            FileWriter inputWriter = new FileWriter("input.txt", true);
            BufferedWriter inputBufferedWriter = new BufferedWriter(inputWriter);

            System.out.print("Enter the number of students: ");
            int n = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            Student[] students = new Student[n];

            for (int i = 0; i < n; i++) {
                System.out.printf("Enter roll number and name for student %d rollno. then  enter the name: ", i + 1);
                int rollNumber = scanner.nextInt();
                scanner.nextLine();
                String name = scanner.nextLine();
                students[i] = new Student(rollNumber, name);
                inputBufferedWriter.write(rollNumber + " " + name + "\n");
            }

            inputBufferedWriter.close();

            mergeSort(students, 0, n - 1);

            FileWriter outputWriter = new FileWriter("output.txt");
            BufferedWriter outputBufferedWriter = new BufferedWriter(outputWriter);

            for (int i = 0; i < n; i++) {
                outputBufferedWriter.write(students[i].rollNumber + " " + students[i].name + "\n");
            }

            outputBufferedWriter.close();

            System.out.println("Sorting completed. Sorted data saved in 'output.txt' file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
