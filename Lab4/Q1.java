import java.util.Scanner;
class Q1
{
    public static void fibofun(long s){    //void fibo fun as it is not returning any variable
        long n1=1,n2=2,n3=0,i;
        for(i=1;i<s;i++)   // loop to get s fibo elements
        {
            n3=n1+n2;
          //  System.out.print(" "+n1); // printing of the fibonacci series
            n1=n2;
            n2=n3;
          
        }
        System.out.print("The no. is "+n1);
    }

    public static void main(String args[])
    {
         Scanner sc=new Scanner(System.in);
         long c;
         System.out.println("Enter the limit");
         c=sc.nextInt();
         fibofun(c);    //function call of fibofun which will display the fibonacci series
    }
}