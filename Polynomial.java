
public class Polynomial {

	double coefficients[];
	
	public Polynomial() {
		coefficients = new double [] {0};
	}
	
	public Polynomial(double newCoefficients[]) {
		coefficients = new double [newCoefficients.length];
		for (int i = 0; i < newCoefficients.length; i++) {
			coefficients[i] = newCoefficients[i];
		}
	}
	
	public Polynomial add(Polynomial newPolynomial) {
		int len;
		if (newPolynomial.coefficients.length < coefficients.length) {
			len = newPolynomial.coefficients.length;
			newPolynomial.coefficients = new double [newPolynomial.coefficients.length];
			for (int i = len; i < newPolynomial.coefficients.length; i++) {
				newPolynomial.coefficients[i] = 0;
			}
		} else {
			len = coefficients.length;
		}
		
		for (int i = 0; i < len; i++) {
			newPolynomial.coefficients[i] = newPolynomial.coefficients[i] + coefficients[i];
		}
		
		return newPolynomial;
	}
	
	public double evaluate(double x) {
		double sum = 0;
		
		for (int i = 0; i < coefficients.length; i++) {
			sum = sum + coefficients[i]*(Math.pow(x, i));
		}
		
		return sum;
	}
	
	public boolean hasRoot(double x) {
		if (evaluate(x) == 0) return true;
		return false;
	}
 }
