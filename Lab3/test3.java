import java.util.Scanner;

public class test3 {
    static void findDuplicates(int arr[], int n) {
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

        double sqrtTerm = Math.sqrt(diff * diff - 4 * quotient);
        int duplicate1 = (int)((diff + sqrtTerm) / 2);
        int duplicate2 = (int)((diff - sqrtTerm) / 2);

        System.out.println("Duplicates: " + duplicate1 + " and " + duplicate2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        int[] arr = new int[n + 2];

        for (int i = 0; i < n + 2; i++) {
            System.out.print("Enter element " + (i + 1) + ": ");
            arr[i] = sc.nextInt();
        }

        findDuplicates(arr, n);
    }
}
