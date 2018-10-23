// Don't place your source in a package
import java.util.*;
import java.lang.*;
import java.io.*;

// Please name your class Main
class Main {
	public static void main (String[] args) throws java.lang.Exception {
		int count;
		int sum, length;
		int[] loc = new int[2];
		boolean found;
		int[] myList = null;
		Scanner in = new Scanner(System.in);
		int tests = in.nextInt();
		for(int i = 0; i < tests; i++) {
		    sum = in.nextInt();
			length = in.nextInt();
		    myList = new int[length];
			loc[0] = myList.length;
			loc[1] = myList.length;
		
			for(int j = 0; j < length; j++) {
				myList[j] = in.nextInt();
			}
			for(int j = 0; j < myList.length-1; j++) {
				for(int k = j+1; k < myList.length; k++) {
					if (myList[j] + myList[k] == sum) {
						if (k <= loc[1] ) {
							loc[0] = j;
							loc[1] = k;
						}
					}
				}
			}
			if(loc[0] != loc[1]) {
			    if(myList[loc[0]] <= myList[loc[1]]) {
				System.out.println(Integer.toString(myList[loc[0]]) + " " + Integer.toString(myList[loc[1]]));
			    }
			    else {
			        System.out.println(Integer.toString(myList[loc[1]]) + " " + Integer.toString(myList[loc[0]]));
			    }
			}
			else {
				System.out.println("!OK");
			}
			
		}
	}

}
