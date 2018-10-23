import java.util.*;
public class Main {
   
	
	public static void main(String[] args) throws java.lang.Exception {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		char[] query = new char[N];
		for(int i = 0; i< N; i++)
		{
		    query[i] = '0';
		}
		String solve = display("Q", query, N);
		System.out.println(solve);
		System.out.flush();
		int numCorrect = in.nextInt();
		String answer = solver(query, N, numCorrect);
		System.out.println(answer);
		
		
		in.close();
	}
	
	
	public static String display(String submi, char[] query, int N)
    {
        String output = submi;
        for (int i = 0; i < N; i++)
        {
            output  = output + " " + query[i];
        }
        
        return output;
    }
	
	public static String solver(char[] query, int N, int numCorrect)
	{
		int index = 0;
		int newCorrect = 0;
		int placeHolder = 0;
		while(index < N && numCorrect < N) {
			query[placeHolder] = '1';
			newCorrect = check(query, N);
			if(newCorrect <= numCorrect)
			{
				query[placeHolder] = '0';
				if(index == N-2)
				{
					query[placeHolder+1] = '1';
					newCorrect = N;
				}
			}
			numCorrect = newCorrect;
			placeHolder++;
			index++;
		}
		
		return display("A", query, N);
	}
	
	public static int check(char[] query, int N)
	{
		String output = display("Q", query, N);
		System.out.println(output);
		System.out.flush();
		Scanner check = new Scanner(System.in);
		int newCorrect = check.nextInt();
		
		
		return newCorrect;
		
		
	}
}
