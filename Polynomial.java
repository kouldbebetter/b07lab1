public class Polynomial {
	double [] x;
	
	public Polynomial() {
		x = new double[1];
	}
	
	public Polynomial(double [] a) {
		x = new double[a.length];
		for(int i=0;i<a.length;i++) {
			x[i] = a[i];
		}
	}

	public Polynomial add(Polynomial other) {
		int size_of_a = other.x.length;
		int size_of_b = x.length;
		if(size_of_a<=size_of_b) {
			Polynomial sum = new Polynomial(x);
			for(int i=0;i<size_of_b;i++) {		
				if(size_of_a > 0) {
					size_of_a -= 1;
					sum.x[i] += other.x[i];
				}
			}
			return sum;
		}else {
			Polynomial sum = new Polynomial(other.x);
			for(int i=0;i<size_of_a;i++) {		
				if(size_of_b > 0) {
					size_of_b -= 1;
					sum.x[i] += x[i];
				}
			}
			return sum;
		}
		
	}
	
	public double evaluate(double a) {
		int size = x.length;
		double result = 0;
		for(int i=0;i<size;i++) {
			result += Math.pow(a, i) * x[i];
		}
		return result;
	}
	
	public boolean hasRoot(double a) {
		return evaluate(a) == 0;
	}
		
	
}
