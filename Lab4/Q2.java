// find the value of (1+i)^n where i=underroot (-1) in O(log2n) time and Theta(long2n) space






import java.util.*;

class Complex {
    double real, imaginary;

    Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }


    Complex multiply(Complex other) {
        double newReal = (this.real * other.real) - (this.imaginary * other.imaginary);
        double newImaginary = (this.real * other.imaginary) + (this.imaginary * other.real);
        return new Complex(newReal, newImaginary);
    }
}

public class Q2 {
    static Complex complexPower(Complex base, int exponent) {
        Complex result = new Complex(1, 0);

        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = result.multiply(base);
            }
            base = base.multiply(base);
            exponent =exponent/ 2;
        }

        return result;
    }

    public static void main(String[] args) 
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the limit: ");
        Complex i = new Complex(0, 1);
        int n ;
        n=sc.nextInt();



        Complex result = complexPower(new Complex(1, 1), n);

        System.out.println("Result: " + result.real + " + " + result.imaginary + "i");
    }
}



















