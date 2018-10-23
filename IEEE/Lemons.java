// Don't place your source in a package
import java.util.*;
import java.lang.*;
import java.io.*;

// Please name your class Main
class Main {
	public static void main (String[] args) throws java.lang.Exception {
	    Scanner in = new Scanner(System.in);
		int a = in.nextInt();
		int b = in.nextInt();
		int c = in.nextInt();
		double timeToFix;
		double travelTime;
		double time;
		in.close();
		timeToFix = Math.log10(a)/Math.log10(2) * c;
		travelTime =Math.log10(a * b)/Math.log10(2);
		time = timeToFix + travelTime;
		System.out.println((int)time);
	}
}