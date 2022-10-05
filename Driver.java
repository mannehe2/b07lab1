import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Driver {
	
	public static void main(String [] args) throws FileNotFoundException {
		
		// Polynomial p = new Polynomial();
		// System.out.println(p.evaluate(3));
		double [] c1 = {6, 5};
		int [] e1 = {0, 3};
		Polynomial p1 = new Polynomial(c1, e1);
		double [] c2 = {-2, -9};
		int [] e2 = {1, 4};
		Polynomial p2 = new Polynomial(c2, e2);
		Polynomial m = p1.add(p2);

		m.saveToFile("add.txt");

		System.out.println("m(0.1) = " + m.evaluate(0.1));
		
		if(m.hasRoot(1))
			System.out.println("1 is a root of m");
		else
			System.out.println("1 is not a root of m");

		Polynomial s = p1.multiply(p2);

		s.saveToFile("multiply.txt");

		for (int i = 0; i < s.coeffs.length; i++) {
			System.out.println(s.coeffs[i]);
			System.out.println(s.expos[i]);
			System.out.println("****");
		}

		File file = new File("//Users//elena//IdeaProjects//Lab2//sample.txt");
		Polynomial P = new Polynomial(file);
		for (int i = 0; i<P.coeffs.length; i++) {
			System.out.println(P.coeffs[i]);
			System.out.println(P.expos[i]);
		}


	}
}