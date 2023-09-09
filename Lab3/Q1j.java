import java.util.*;
public class Q1j {
	static int findRepeating(int arr[], int N) {
		int[] freq = new int[N + 1];
		
		for (int i = 0; i < N; i++) {
			freq[arr[i]]++;
		}
		
		for (int i = 0; i < N; i++) {
			if (freq[arr[i]] > 1) {
				return arr[i];
			}
		}
		
		return -1;
	}

	public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int i,n;
        System.out.println("Enter the limit");
        n=sc.nextInt();
        int arr[] = new int[n];
        for(i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
		
	

		// Function call
		System.out.println("Repeated Element is "+findRepeating(arr,n));
	}
}
