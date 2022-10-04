import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class Polynomial {
	double [] exponents;
	double [] coefficients;
	
	public Polynomial() {
		exponents = null;
		coefficients = null;
	}
	
	public Polynomial(double [] exponents, double [] coefficients) {
		for(int i=0;i<coefficients.length;i++) {
			if(coefficients[i]==0)
			{
				exponents = null;
				coefficients = null;
				return;
			}
		}
		this.exponents = exponents;
		this.coefficients = coefficients; 
	}
    
	public Polynomial(File f)  throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(f));
		String str = reader.readLine();
		System.out.println(str);
		double [] exp = new double[0];
		double [] coe = new double[0];
		String[] s = str.split("");
		int negative = 0;
		int not_constant = 0;
		String num = "";
		for(int i=0;i<s.length;i++) {
			if(s[i].equals("-")){
				if(num.equals("")) {
					
				}
				else if(not_constant == 1) {
					exp = new_poly(exp, Double.parseDouble(num));
				}
				else {
					if(negative == 1) {
						coe = new_poly(coe, Double.parseDouble("-" + num));
					}
					else {
						coe = new_poly(coe, Double.parseDouble(num));
					}
					exp = new_poly(exp, 0);
				}
				not_constant = 0;
				negative = 1;
				num = "";
			}
			else if(s[i].equals("+")) {
				if(not_constant == 1) {
					exp = new_poly(exp, Double.parseDouble(num));
				}
				else {
					if(negative == 1) {
						coe = new_poly(coe, Double.parseDouble("-" + num));
					}
					else {
						coe = new_poly(coe, Double.parseDouble(num));
					}
					exp = new_poly(exp, 0);
				}
				not_constant = 0;
				negative = 0;	
				num = "";
			}
			else if(s[i].equals("x")) {
				if(negative == 1) {
					coe = new_poly(coe, Double.parseDouble("-" + num));
				}
				else {
					coe = new_poly(coe, Double.parseDouble(num));
				}
				negative = 0;
				num = "";
				not_constant = 1;
			}
			else {
				num = num + s[i];
			}
		}
		exp = new_poly(exp, Double.parseDouble(num));
		exponents = exp;
		reader.close();
		coefficients = coe;
	}
	
	public double [] new_poly(double [] array, double a) {
		double new_array[] = new double[array.length+1]; 
		for(int i=0;i<array.length;i++) {
			new_array[i] = array[i];
		}
		new_array[array.length] = a;
		return new_array;
	}
	
	public int is_in_array(double [] array, double a) {
		for(int i=0;i<array.length;i++) {
			if(array[i]==a) {
				return i;
			}
		}
		return -1;
	}
	
	public Polynomial add(Polynomial other) {
		double [] exp = new double[exponents.length];
		for(int i=0;i<exponents.length;i++) {
			exp[i] = exponents[i];
		}
		double [] coe = new double[coefficients.length];
		for(int i=0;i<coefficients.length;i++) {
			coe[i] = coefficients[i];
		}
		
		for(int i=0;i<other.exponents.length;i++) {
			int j = is_in_array(exp,other.exponents[i]);
			if(j>=0) {
				coe[j] += other.coefficients[j];
			}
			else {
				exp = new_poly(exp, other.exponents[i]);
				coe = new_poly(coe, other.coefficients[i]);
			}
		}
		Polynomial sum = new Polynomial(exp, coe);
		return sum;
	}
	
	public Polynomial multiply(Polynomial other) {
		double [] exp = new double[0];
		double [] coe = new double[0];

		for(int i=0;i<exponents.length;i++) {
			for(int j=0;j<other.exponents.length;j++) {
				double new_exp = exponents[i] + other.exponents[j];
				double new_coe = coefficients[i] * other.coefficients[j];
				
				int m = is_in_array(exp,new_exp);
				if(m>=0) {
					coe[m] += new_coe;
				}
				else {
					exp = new_poly(exp, new_exp);
					coe = new_poly(coe, new_coe);
				}
			}
		}
		Polynomial product = new Polynomial(exp, coe);
		return product;
	}
	
	
	public double evaluate(double a) {
		double result = 0;
		for(int i=0;i<exponents.length;i++) {
			result += Math.pow(a, exponents[i]) * coefficients[i];
		}
		return result;
	}
	
	public boolean hasRoot(double a) {
		return evaluate(a) == 0;
	}
		
	
	public void saveToFile(String g) throws IOException {
		String s = "";
		if(coefficients[0]<0) {
			s = "-";
		}
		s += Integer.toString((int)coefficients[0]);
		if(exponents[0]!=0) {
			s = s + "x" + Integer.toString((int)exponents[0]);
		}
			
		for(int i=1;i<exponents.length;i++) {
			if(coefficients[i]<0) {
				s += "-";
			}
			else {
				s += "+";
			}
			System.out.println((int)coefficients[i]);
			s += Integer.toString((int)coefficients[i]);
			if(exponents[i]!=0) {
				s = s + "x" + Integer.toString((int)exponents[i]);
			}
		}
		
		FileWriter f = new FileWriter(g);
		f.write(s);
		f.flush();
		f.close();
	}
	
}	
