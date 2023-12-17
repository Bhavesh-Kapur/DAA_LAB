import java.util.Scanner;
public class Q1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, i, j, k;
        System.out.print("Enter number of words to be sorted: ");
        n = scanner.nextInt();
        String[] arr = new String[n];

        for (i = 0; i < n; i++) {
            System.out.print("Enter word " + (i + 1) + ": ");
            arr[i] = scanner.next();
        }

        // Insertion Sort for words
        for (i = 1; i < n; i++) {
            for (j = i - 1; j >= 0; j--) {
                if (arr[i].compareTo(arr[j]) <= 0) {
                    break;
                }
            }
            if (i - j > 1) {
                String temp = arr[i];
                for (k = i; k > j + 1; k--) {
                    arr[k] = arr[k - 1];
                }
                arr[j + 1] = temp;
            }
        }

        // Sorted List Output
        System.out.println("\nSorted list is:");
        for (i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
