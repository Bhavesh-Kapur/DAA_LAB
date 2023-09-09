import java.util.Scanner;

public class test2 {
    static void findRepeating(int arr[]) {
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            // Check if the element at this index has been processed
            if (arr[Math.abs(arr[i])] >= 0) {

                arr[Math.abs(arr[i])] = -arr[Math.abs(arr[i])];
            } else {
                // If the element is already negative, it's a duplicate
                System.out.println("Repeated Element: " + Math.abs(arr[i]));
            }
        }
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
        findRepeating(arr);
    }
}
