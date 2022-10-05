import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Polynomial {

	double coeffs[];
	int expos[];

	public Polynomial() {
		coeffs = new double[] { 0 };
		expos = new int[] { 0 };
	}

	public Polynomial(double newCoeffs[], int newExpos[]) {
		coeffs = new double[newCoeffs.length];
		expos = new int[newExpos.length];
		for (int i = 0; i < newCoeffs.length; i++)
			coeffs[i] = newCoeffs[i];
		for (int i = 0; i < newExpos.length; i++)
			expos[i] = newExpos[i];
	}

	public Polynomial(File file) throws FileNotFoundException {
		Scanner scan = new Scanner(file);
		String s = "";
		while (scan.hasNextLine()) {
			s += (scan.nextLine());
		}
		String arrS[] = s.split("x");
		double []c = new double[arrS.length];
		int []e = new int[arrS.length];
		int cj = 0;
		int ej = 0;
		for (int i = 0; i < arrS.length; i++) {
			if (arrS[i].contains("^")) {
				e[cj] = Integer.parseInt(arrS[i].substring(arrS[i].indexOf("^") + 1, arrS[i].indexOf("^") + 2));
				cj++;
			}
			if (arrS[i].contains("+")) {
				c[ej] = Integer.parseInt(arrS[i].substring(arrS[i].indexOf("+") + 1, arrS[i].indexOf("+") + 2));
				ej++;
			}
			if (arrS[i].contains("-")) {
				c[ej] = Integer.parseInt(arrS[i].substring(arrS[i].indexOf("-"), arrS[i].indexOf("-") + 2));
				ej++;
			}
			if (!arrS[i].contains("+") && !arrS[i].contains("-") && !arrS[i].contains("^")) {
				c[ej] = Integer.parseInt(arrS[i]);
				ej++;
			}
		}

		int count = 0;
		for (int i = 1; i < c.length; i++) {
			if (c[i] != 0) {
				count++;
			}
		}
		double[] coeffs = new double[count + 1];
		int[] expos = new int[count + 1];

		int j = 0;
		for (int i = 0; i < c.length; i++) {
			if (c[i] != 0) {
				coeffs[j] = c[i];
				expos[j] = e[i];
				j++;
			}
		}

		this.coeffs = coeffs;
		this.expos = expos;

	}

	private Polynomial assignTempCoeffs(double tempCoeffs[]) {
		
		Polynomial poly = new Polynomial();

		int count = 0;
		for (int i = 0; i < tempCoeffs.length; i++) {
			if (tempCoeffs[i] == 0)
				count++;
		}

		poly.coeffs = new double[tempCoeffs.length - count];
		poly.expos = new int[tempCoeffs.length - count];

		int m = 0, k = 0;
		for (int i = 0; i < tempCoeffs.length; i++) {
			if (tempCoeffs[i] != 0) {
				poly.coeffs[m] = tempCoeffs[i];
				poly.expos[k] = i;
				m++;
				k++;
			}
		}
		return poly;
	}

	public Polynomial add(Polynomial newPoly) {
		
		double tempCoeffs[] = new double[1 + Math.max(expos[expos.length - 1], newPoly.expos[newPoly.expos.length - 1])];

		int j = 0;
		for (int i = 0; i < expos.length; i++) {
			if (expos[i] == newPoly.expos[j]) {
				tempCoeffs[expos[i]] = coeffs[i] + newPoly.coeffs[j];
				j++;
			}
			else if (tempCoeffs[expos[i]] == 0) tempCoeffs[expos[i]] = coeffs[i];
			if (i == expos.length - 1) {
				// tempCoeffs[expos[i]] = coeffs[i];
				while (j != newPoly.expos.length) {
					tempCoeffs[newPoly.expos[j]] = newPoly.coeffs[j];
					j++;
				}
			
			}
		}

		Polynomial poly = assignTempCoeffs(tempCoeffs);

		return poly;
	}

	public double evaluate(double x) {
		double sum = 0;

		for (int i = 0; i < coeffs.length; i++) {
			sum += coeffs[i] * (Math.pow(x, expos[i]));
		}

		return sum;
	}
	
	public boolean hasRoot(double x) {
		if (evaluate(x) == 0)
			return true;
		return false;
	}

	public Polynomial multiply(Polynomial newPoly) {

		double tempCoeffs[] = new double[1 + expos[expos.length - 1] + newPoly.expos[newPoly.expos.length - 1]];

		for (int i = 0; i < expos.length; i++) {
			for (int j = 0; j < newPoly.expos.length; j++) {
				tempCoeffs[expos[i] + newPoly.expos[j]] += coeffs[i] * newPoly.coeffs[j];
			}
		}

		Polynomial poly = assignTempCoeffs(tempCoeffs);

		return poly;
	}

	public void saveToFile(String filename){
		
		try {
			File myfile = new File(filename);
			
			if(!myfile.exists()) {
				myfile.createNewFile();
			}
			
			PrintWriter pw = new PrintWriter(myfile);
			String poly = "";
			for (int i = 0; i < coeffs.length-1; i++) {
				poly = poly + (int)coeffs[i];
				poly = poly + "x^";
				poly = poly + (int)expos[i];
				if (coeffs[i+1]>0) {
					poly = poly + "+";
				}
			}
				poly = poly + (int)coeffs[coeffs.length-1];
				poly = poly + "x^";
				poly = poly + (int)expos[expos.length-1];
			pw.print(poly);
			pw.close();
			System.out.println("File is created");
   
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}

}
