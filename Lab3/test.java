import java.util.Scanner;

public class test {
    static int findRepeating(int arr[], int N) {
        int xor = 0;

        // XOR all elements of the array
        for (int i = 0; i < N; i++) {
            xor=xor^arr[i];
        }

        // XOR with elements from 1 to N-1
        for (int i = 1; i < N; i++) {
            xor =xor^i;
        }

        return xor;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        System.out.println("Enter the limit");
        n = sc.nextInt();
        int arr[] = new int[n];
        System.out.println("Enter the elements");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Function call
        int repeatedElement = findRepeating(arr, n);
        System.out.println("Repeated Element is " + repeatedElement);
    }
}