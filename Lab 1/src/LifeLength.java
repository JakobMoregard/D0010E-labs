package Lab1;
/**
 * 
 */
/**
 * @author Jakob Moregård
 *
 */
public class LifeLength{
	
	public static int f1(int a1) {
		
		if(a1 == 1) {
			return a1 = 1;	
		}
			
		else if(a1 % 2 == 0){
			a1 = a1 / 2;
			return a1;
		}
		
		else {
			a1 = (3 * a1) + 1;
			return a1;
		}
	}
	
	public static int f2(int a2) {
		
		a2 = f1(f1(a2));
		return a2;
	}
	
	public static int f4(int a4) {
		
		a4 = f2(f2(a4));
		return a4;
	}
	
	public static int f8(int a8) {
		
		a8 = f4(f4(a8));
		return a8;
	}
	
	public static int f16(int a16) {
		
		a16 = f8(f8(a16));
		return a16;
	}
	
	public static int f32(int a32) {
		
		a32 = f16(f16(a32));
		return a32;
	}
	
	public static int iterateF(int a0, int n) {
		
		for(int i = 0; i < n; i++) {
			a0 = f1(a0);
		}
		
		return a0;
	}
	
	public static int iterLifeLength(int a) {
		
		int steps = 0;
		while(a > 1) {
			a = f1(a);
			steps = steps + 1;
		}
		
		return steps;
	}

	public static String intToString(int aI, int bI) {
		
		String aS = Integer.toString(aI);
		String bS = Integer.toString(bI);
		
		String result = "The life length of " + aS + " is " + bS + ".";
		
		return result;
	}
	
	public static int recLifeLength(int c) {
		
		int stepfunc = 0;
		if(c == 1) {
			return 0;
		}
		else {
			stepfunc = recLifeLength(f1(c)) + 1;
			return stepfunc;
		}
	}
	
	public static String intToString2(int d) {
		
		String s = Integer.toString(d);
		return s;
	}
	
	public static String task1(int f) {
		
		int d1 = f1(f);
		String s = intToString2(f);
		String s1 = intToString2(d1);
		String result = "java lifelength" + s + ": f1 = " + s1;
		return result;
	}
	
	public static String task2(int e) {
		
		int e2 = f2(e);
		int e4 = f4(e);
		int e8 = f8(e);
		int e16 = f16(e);
		int e32 = f32(e);
		String s = intToString2(e);
		String s2 = intToString2(e2);
		String s4 = intToString2(e4);
		String s8 = intToString2(e8);
		String s16 = intToString2(e16);
		String s32 = intToString2(e32);
		String result = "java lifelength" + s + ": f2 = " + s2 + ", f4 = "
						+ s4 + ", f8 = " + s8 + ", f16 = " + s16 + ", f32 = " + s32;
		return result;
	}
	
	public static String task3(int g, int h) {
		
		int itres = iterateF(g, h);
		String s1 = intToString2(g); 
		String s2 = intToString2(h);
		String s3 = intToString2(itres);
		String result = "java lifelength " + s1 + ", " + s2 + " steps is: " + s3;
		return result;
	}
	
	public static void task4() {
		
		for(int i = 1; i <= 15; i++) {
			int res = iterLifeLength(i);
			String s = intToString(i, res);
			System.out.println(s);
		}
	}
	
	public static void task6() {
		
		for(int i = 1; i <= 15; i++) {
			//int ires = iterLifeLength(i);
			//String s1 = intToString(i, ires);
			int fres = recLifeLength(i);
			String s2 = intToString(i, fres);
			//System.out.println(s1);
			System.out.println(s2);
		}
	}
	
	public static void main(String[] args) {
		
		int n = Integer.parseInt(args[0]);
		int n1 = Integer.parseInt(args[1]);
		int n2 = Integer.parseInt(args[2]);
		
		switch(n) {
			
		case 1:
			String t1 = task1(n1);
			System.out.println(t1);
			break;
			
		case 2:
			String t2 = task2(n1);
			System.out.println(t2);
			break;
			
		case 3:
			String t3 = task3(n1, n2);
			System.out.println(t3);
			break;
		
		case 4:
			task4();
			break;
			
		case 6:
			task6();
			break;
			
		default:
			System.out.println("Wrong input");
			break;
		}
		
	}
	
}
