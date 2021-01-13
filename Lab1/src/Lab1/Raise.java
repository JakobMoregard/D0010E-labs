package Lab1;
/**
 * 
 */

/**
 * @author Jakob Moregård
 *
 */
public class Raise {
	
	static int halfCount = 0;
	static int oneCount = 0;
	
	public static double recRaiseHalf(double x, int k) {
		
		int k2 = k/2;
		double x2 = x;
		
		if(k == 0) {
			return 1.0;
		}
		
		else if(k % 2 == 0) {
			x = recRaiseHalf(x2, k2);
			halfCount ++;
			return(x * x);
		}
		else {
			x = recRaiseHalf(x2, k2);
			halfCount ++;
			return(x2 * x * x);
		}
	
	}

	public static double recRaiseOne(double x, int k) {
		
		if (k == 0) {
			return 1.0;
		}
		else {
			oneCount ++;
			return(x * recRaiseOne (x, k-1));
		}
		
	}
	
	public static void main(String[] args) {
		
		//double x = Double.parseDouble(args[0]);
		//int i = Integer.parseInt(args[1]);
		for(int i = 1; i <= 15; i++) {
			halfCount = 0;
			oneCount = 0;
			System.out.print("1.000001 raised to " + i + " in RaiseHalf is: ");
			System.out.print(recRaiseHalf(2, i));
			System.out.println(" number of recursions in half: " + halfCount);
			System.out.println("");
			System.out.print("1.0000001 raised to " + i + " in RaiseOne is: ");
			System.out.print(recRaiseOne(2, i));
			System.out.println(" number of recursions in one: " + oneCount);
			System.out.println("");
			System.out.println("");
		}
		
	}
	
}

