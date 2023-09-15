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

public class test {
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
        // Read student data from a pre-existing input file
        List<Student> studentsList = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(new File("input.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" ");
                int rollNumber = Integer.parseInt(parts[0]);
                String name = parts[1];
                studentsList.add(new Student(rollNumber, name));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Convert the list to an array for sorting
        Student[] students = studentsList.toArray(new Student[0]);

        // Sorting the students based on roll numbers
        mergeSort(students, 0, students.length - 1);

        try {
            FileWriter outputWriter = new FileWriter("output.txt");
            BufferedWriter outputBufferedWriter = new BufferedWriter(outputWriter);

            for (int i = 0; i < students.length; i++) {
                outputBufferedWriter.write(students[i].rollNumber + " " + students[i].name + "\n");
            }

            outputBufferedWriter.close();

            System.out.println("Sorting completed. Sorted data saved in 'output.txt' file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
