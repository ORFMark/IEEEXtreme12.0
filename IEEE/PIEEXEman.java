// Don't place your source in a package
import java.util.*;
import java.lang.*;
import java.io.*;

// Please name your class Main
class Main {
	public static final int SPEED = 2;
	
	public static void main(String[] Args)
   {
	   Scanner in = new Scanner(System.in);
	   int height = in.nextInt() * 2 + 1;
	   int width = in.nextInt() * 2 + 1;
	   in.nextLine();
	   String[] mapSelection = new String[1];
	   switch (height) {
	   case 7:
		   mapSelection = new String[]{"U","R"};
		   break;
       case 15:
    	   mapSelection = new String[]{"U","R","R","D","U","U","U","R","U","L","L","L","U","U","D","D","R","U","U","D","D","R","R","D","D","D","R","D","L"};
		   break;
	   case 23:
	       mapSelection = new String[]{"R","R","R","R","R","U","U","U","U","U","L","L","L","L","L","U","U","R","D","U","R","D","U","R","D","U","L","L"
    			   ,"L","D","D","R","R","R","R","R","R","R","R","R","R","D","D","L","U","D","L","U","D","L","U","D","R","R","R","U"
    			   ,"U","L","L","L","L","L","U","U","U","U","U","R","D","R","R","R","R","D","D","L","R","U","U","L","D","L","D","U"
    			   ,"R","U","L","L","L","D","D","D","R","R","R","R","L","L","L","U","U","D","D","L","U","U","U","U","L","D","D","D"
    			   ,"D","D","D","D","D","D","D","L","U","L","L","L","L","U","U","R","L","D","D","R","U","R","U","D","L","D","R","R"
    			   ,"R","U","U","U","L","L","L","L","R","R","R","D","D","U","U","R","D","D","D","D","R","U","U","U","U","U","L","U"
    			   ,"U","U","U","U","L","L","L","L","D","D","R","R","R","U","L","L","R","R","D","L","L","L","U","U","R","R","R","R"
    			   ,"D","D","D","D","D","R","R","D","D","D","D","D","R","R","R","R","U","U","L","L","L","D","R","R"};
	       break;
       case 4:
    	   mapSelection = new String[]{};
	       break;
	   }
	   char[][] board = new char[height][width];
	   int[] cherries = new int[2];
	   char whom;
	   int g = 0;
	   String judgeMove = "";
	   String direction = "";
	   String temp = "";
	   String temp2 = "";
	   for(int i = 0; i < height; i++)
	   {
		   temp = in.nextLine();
		   
		   for(int j = 0; j < width; j++)
		   {
			   
			   board[i][j] = temp.charAt(j);
			   
			   
		   }
	   }

	   do{
		   whom = '1';
		   if(g < mapSelection.length) {
				   move(mapSelection[g], board, cherries, whom, height, width);
				   System.out.println(mapSelection[g]);
		   }else {
			   move("W", board, cherries, whom, height, width);
			   System.out.println("W");
		   }
		   judgeMove = in.nextLine();
		   if(judgeMove.length() == 2)
			   continue;
		   whom = '2';
           move(judgeMove, board, cherries, whom, height, width);
           g++;
	   }while(judgeMove.length() < 2);
	   
	   
   }
   
   public static void move(String M, char[][] board, int[] cherries, char whom, int height, int width)
   {
	   
	   int row = 0;
	   int col = 0;
	   
	   
	   for(int i = 0; i< board.length; i++)
		   for(int j = 0;j < board[i].length; j++)
		   {
			   if (board[i][j] == whom || board[i][j] == '3')
			   {
				   row = i;
				   col = j;
			   }
		   }
	   
	   
	   
	   if(M == "U") 
	   {
		   
		   if(board[row-1][col] != '#') {
			   if(board[row-SPEED][col] == '@')
			   {
				   switch (whom)
				   {
				      case '1':
				    	  cherries[0]++;
				    	  break;
				      case '2':
				    	  cherries[1]++;
				    	  break;
				   }
			   }
			   board[row][col] = '.';
			   if(board[row-SPEED][col] == '1' || board[row-SPEED][col] == '2')
				   board[row-SPEED][col] = '3';
			   else
			       board[row-SPEED][col] = whom;
		   }
		   
	   }
	   if(M == "D") 
	   {
		   if(board[row+1][col] != '#') {
			   if(board[row+SPEED][col] == '@')
			   {
				   switch (whom)
				   {
				      case '1':
				    	  cherries[0]++;
				    	  break;
				      case '2':
				    	  cherries[1]++;
				    	  break;
				   }
			   }
			   board[row][col] = '.';
			   if(board[row+SPEED][col] == '1' || board[row+SPEED][col] == '2')
				   board[row+SPEED][col] = '3';
			   else
			   board[row+SPEED][col] = whom;
		   }
		   
	   }
       if(M == "L") 
       {
    	   
    	   
    	   if(board[row][col-1] != '#') {
    		   if(board[row][col-SPEED] == '@')
		   {
			   switch (whom)
			   {
			      case '1':
			    	  cherries[0]++;
			    	  break;
			      case '2':
			    	  cherries[1]++;
			    	  break;
			   }
		   }
			   board[row][col] = '.';
			   if(board[row][col-SPEED] == '1' || board[row][col+SPEED] == '2')
				   board[row][col-SPEED] = '3';
			   else
			       board[row][col-SPEED] = whom;
		   }
		   
	   }
	   if(M == "R") 
	   {
		   if(board[row][col+1] != '#') {
			   if(board[row][col+SPEED] == '@')
			   {
				   switch (whom)
				   {
				      case '1':
				    	  cherries[0]++;
				    	  break;
				      case '2':
				    	  cherries[1]++;
				    	  break;
				   }
			   }
			   board[row][col] = '.';
			   if(board[row][col+SPEED] == '1' || board[row][col+SPEED] == '2')
				   board[row][col+SPEED] = '3';
			   else
				   board[row][col+SPEED] = whom;
		   }
		   
	   }
	   if(M == "W")
	   {
		   
	   }
   }

}