//filename: TicTacToe.java
//description: creating a game of tictactoe
//name: Ifeoluwa Tella

import java.util.*;

public class TicTacToe{
	private static final int ROW = 3;
	private static final int COL = 3;
	private static String board [][] = new String[ROW][COL];
	
	//main method
		public static void main (String[] args) 
		{
			//create instance of Scanner class
			Scanner input = new Scanner(System.in);
			do{
			     TicTacToe.play();
			}while(TicTacToe.getYNConfirm(input, "\nContinue(y/n)? "));
		}
	
	//method returns and integer in range of low-high
	public static int getRangedInt(Scanner input, String prompt, int low, int high) {
	
		int result;
		
		// Loop until valid input is read in
		do {
			// Prompt user and loop until they have entered a number
			System.out.print(prompt);
			while (!input.hasNextInt()) {
				input.nextLine();
				System.out.print(prompt);
			}
		// Read in the number
		result = input.nextInt();
		} while (result < low || result > high);
		// Return the result
		return result;	
	}
	
	//method return true if user enter yes or y otherwise return false 
	public static boolean getYNConfirm(Scanner input, String prompt)
	{
		String str;
		// Prompt to the user
		System.out.print(prompt);
		
		// Read in the string
		str = input.next();
		if (str.equalsIgnoreCase("yes") || str.equalsIgnoreCase("y"))
			return true;
		// Return false
		return false;	
	}
	
	// sets all the board elements to a space
	private static void clearBoard()
	{
		for (int row = 0; row < 3; row++)
        	for (int col = 0; col < 3; col++)
            	board[row][col] = " ";
	}
	
	// shows the Tic Tac Toe game board
	private static void display() 
	{
        for(int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                        System.out.print(" " + board[row][col] + "(" + row + "," + col + ") ");
                        if (col != 2)
                                System.out.print("");
                }
                System.out.println();
                }
        System.out.println();
                
        }
	// returns true if there is a space at the given proposed move coordinates which means it is a legal move.
	private static boolean isValidMove(int row, int col)
	{
		if (row>=0 && row<ROW && col>=0 && col<COL && board[row][col].equals(" "))
               return true;
       
       	return false;
	}
	
	// checks to see if there is a win state on the current board
	private static boolean isWin(String player)
	{
		if(isColWin(player) || isRowWin(player) || isDiagnalWin(player))
			return true;
		return false;
	}
	
	// checks for a col win for specified player
	private static boolean isColWin(String player)
	{
		for(int col=0; col<COL; col++)
	    {
	        if(board[0][col].equals(player) && board[0][col].equals(board[1][col]) && board[1][col].equals(board[2][col]))
	            return true;
	    }
	    return false;
	}
	
	// checks for a row win for the specified player
	private static boolean isRowWin(String player)
	{
		for(int row=0; row<ROW; row++)
	    {
	        if(board[row][0].equals(player) && board[row][0].equals(board[row][1]) && board[row][1].equals(board[row][2]))
	            return true;
	    }
	    return false;
	}
	
	// checks for a diagonal win for the specified player
	private static boolean isDiagnalWin(String player)
	{
		if(board[1][1].equals(player) && ((board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) ||
	       (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]))))
	            return true;
	    return false;
	}
	
	// checks for a tie condition: all spaces on the board are filled OR there is an X and an O in every win vector 
	//(i.e. all possible 8 wins are blocked by having both and X and an O in them.)	
	private static boolean isTie()
	{
		for(int row=0; row<ROW; row++)
		{
			for(int col=0; col<COL; col++)
			{
				if(board[row][col].equals(" "))
					return false;
			}
		}
		return true;
	}
	
	//play method
	public static void play() 
	{
		//create instance of Scanner class
		Scanner input = new Scanner(System.in);
		//create instance of Random class
		Random rand = new Random();
		
		System.out.print("Want to go first (X) or second(O) (enter X or O)?  ");
		String user = input.next().toUpperCase();
		
		String computer = "O";
		if(user.equals("O"))
			computer = "X";	
		String player = "X";
		//clear the board
		clearBoard();
		//display the board
		display();
		//start the game
		while(true)
		{
				int rowMove, colMove;
				if(player.equals(user))
				{
					//get a move from user
					System.out.println ("Your move: ");
					rowMove = getRangedInt(input, "Enter row: ", 0, 2);
					colMove = getRangedInt(input, "Enter column: ", 0, 2);
					while(!isValidMove(rowMove, colMove))
					{
						System.out.println ("Invalid move! Try again.");
						rowMove = getRangedInt(input, "Enter row: ", 0, 2);
						colMove = getRangedInt(input, "Enter column: ", 0, 2);
					}
					System.out.println ("User's move " + rowMove + " " + colMove);
					System.out.println();
				}
				else
				{
					//get a move from computer
					do{
						rowMove = rand.nextInt(3);
						colMove = rand.nextInt(3);
					}while(!isValidMove(rowMove, colMove));
					System.out.println ("Computer's move " + rowMove + " " + colMove);
				}
				System.out.println();
				board[rowMove][colMove] = player;
					
		      	//display the board
		        display();
		
				//get the status
				if(isWin(player) && player.equals(user))
				{
					System.out.println ("Tic-tac-toe!");
						
					break;
				}
				else if(isTie())
				{
					System.out.println ("This game is a Tie!");
					break;
				}
					
				//toggle the player
		        if(player == "X")
		            player = "O";
		        else
		            player = "X";
		            
			}//end of while
	}
}
