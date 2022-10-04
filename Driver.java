import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Driver {
	public static void main(String [] args) throws IOException {
		double [] e1 = {0,1,2};
		double [] c1 = {1,2,1};
		Polynomial p1 = new Polynomial(e1,c1);//first polynomial
		double [] e2 = {0,1,3,4};
		double [] c2 = {1,1,1,1};
		Polynomial p2 = new Polynomial(e2,c2);//second polynomial
		Polynomial s1 = p1.add(p2);
		Polynomial s2 = p1.multiply(p2);
		File f = new File("C:\\Users\\Kevin\\Desktop\\test.txt");
		BufferedReader reader = new BufferedReader(new FileReader(f));
		reader.close();
		Polynomial f1 = new Polynomial(f);
		System.out.println(" printing f1, a local polynomial file.");
		for(int i=0; i<f1.coefficients.length;i++) {
			System.out.println(f1.coefficients[i] + "x^" +f1.exponents[i]);
		}
		System.out.println("\n printing s1, sum of p1 + p2.");
		for(int i=0; i<s1.coefficients.length;i++) {
			System.out.println(s1.coefficients[i] + "x^" +s1.exponents[i]);
		}
		System.out.println("\n printing s2, product of p1 x p2.");
		for(int i=0; i<s2.coefficients.length;i++) {
			System.out.println(s2.coefficients[i] + "x^" +s2.exponents[i]);
		}
		
		System.out.println("Saving p1 to local, check local file.");
		p1.saveToFile("C:\\Users\\Kevin\\Desktop\\test.txt");
		
		System.out.println("Testing if -1 is a root of p1.");
		if(p1.hasRoot(-1))
			System.out.println("-1 is a root of s");
		else
			System.out.println("-1 is not a root of s");
	}
}