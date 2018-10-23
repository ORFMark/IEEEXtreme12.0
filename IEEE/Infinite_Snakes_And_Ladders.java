import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int boardSize = in.nextInt();
		int numberOfPlayers = in.nextInt();
		
		// Create an array of snakes present on the board
		int numberOfSnakes = in.nextInt();
		Snake[] snakes = new Snake[numberOfSnakes];
		for (int i = 0; i < numberOfSnakes; i++) {
			int snakeHead_xpos = in.nextInt();
			int snakeHead_ypos = in.nextInt();
			int snakeTail_xpos = in.nextInt();
			int snakeTail_ypos = in.nextInt();
			snakes[i] = new Snake(new Position(snakeHead_xpos, snakeHead_ypos), new Position (snakeTail_xpos, snakeTail_ypos));
		}
		
		// Create an array of ladders present on the board
		int numberOfLadders = in.nextInt();
		Ladder[] ladders = new Ladder[numberOfLadders];
		for (int i = 0; i < numberOfLadders; i++) {
			int ladderBase_xpos = in.nextInt();
			int ladderBase_ypos = in.nextInt();
			int ladderTop_xpos = in.nextInt();
			int ladderTop_ypos = in.nextInt();
			ladders[i] = new Ladder(new Position(ladderBase_xpos, ladderBase_ypos), new Position(ladderTop_xpos, ladderTop_ypos));
		}
		
		int numberOfTurns = in.nextInt();
		
		// Create a board of size boardSize
		Board board = new Board(boardSize, snakes, ladders);
		
		
		// Create an array of players
		Player[] players = new Player[numberOfPlayers];
		for (int i = 0; i < numberOfPlayers; i++) {
			players[i] = new Player();
		}
		
		// Handles dice rolls and turns
		for (int i = 0; i < numberOfTurns; i++) {
			int dice1Roll = in.nextInt();
			int dice2Roll = in.nextInt();
			int spacesToTravel = dice1Roll + dice2Roll;
			int playerNumber = i % numberOfPlayers;
			int isDone = 0;
			while (players[playerNumber].getIsDone()) {
				playerNumber++;
				isDone++;
				if (playerNumber > numberOfPlayers - 1)
					playerNumber = 0;
				if (isDone == numberOfPlayers)
					break;
			}
			if (isDone != numberOfPlayers) {
				players[playerNumber].movePlayer(spacesToTravel, board);
				//System.out.print("Player " + ((playerNumber) + 1) + "'s position is");
				//players[playerNumber].displayCurrentPosition();
			} else {
				//System.out.println("Just Rollin the Dice");
			}
		}
		
		//players[0].movePlayer(3, board);
		//players[0].displayCurrentPosition();
		
		for(int i = 0; i < numberOfPlayers; i++) {
			System.out.print(i + 1);
			if (players[i].getIsDone()) {
				System.out.print(" winner");
			} else {
				players[i].displayCurrentPosition();
			}
			System.out.println();
		}
		
		
		in.close();
		
	}
}

class Position {
	private int x;
	private int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void incrementX() {
		x++;
	}
	
	public void incrementY() {
		y++;
	}
	
	public void decrementX() {
		x--;
	}
	
	public boolean isEqual(Position p) {
		if (p.getX() == x && p.getY() == y) {
			return true;
		}
		return false;
	}
}

class Ladder {
	Position base;
	Position top;
	
	public Ladder(Position base, Position top) {
		this.base = base;
		this.top = top;
	}
	
	public Position getBase() {
		return base;
	}
	
	public Position getTop() {
		return top;
	}
	
}

class Snake {
	Position head;
	Position tail;
	
	public Snake(Position head, Position tail) {
		this.head = head;
		this.tail = tail;
	}
	
	public Position getHead() {
		return head;
	}
	
	public Position getTail() {
		return tail;
	}
}

class Board {
	private Position[] board;
	private int boardSize;
	private Position end;
	private Snake[] snakes;
	private Ladder[] ladders;
	
	public Board(int boardSize, Snake[] snakes, Ladder[] ladders) {
		board = new Position[boardSize];
		this.boardSize = boardSize;
		end = new Position(0, boardSize);
		this.snakes = snakes;
		this.ladders = ladders;
	}
	
	public Ladder[] getLadders() {
		return ladders;
	}
	
	public Snake[] getSnakes() {
		return snakes;
	}
	
	public int getBoardSize() {
		return boardSize;
	}
	
	public Position getEnd() {
		return end;
	}
	
	

}

class Player {
	private Position currentPos;
	private boolean isDone;
	
	public Player() {
		currentPos = new Position(0, 1);
		isDone = false;
	}
	
	public boolean getIsDone() {
		return isDone;
	}
	
	public void movePlayer(int spaces, Board board) {
		int spacesTraveled = 0;
		while (spacesTraveled != spaces) {
			if (currentPos.getX() == board.getBoardSize() && currentPos.getY() % 2 != 0) {
				// The player's position is at far right side of board and needs to go up
				currentPos.incrementY();
			} else if (currentPos.getX() == 1 && currentPos.getY() % 2 == 0) {
				// The player's position is at far left side of board and need to go up
				if (currentPos.getY() == board.getBoardSize()) {
					currentPos.decrementX();
					isDone = true;
					break;
				}
				currentPos.incrementY();
			} else {
				if (currentPos.getY() % 2 == 0) {
					// is moving left across the board
					currentPos.decrementX();
				} else {
					// is moving right across the board
					currentPos.incrementX();	
				}
			}
			spacesTraveled++;
		}
		for (int i = 0; i < board.getLadders().length; i++) {
			if (board.getLadders()[i].getBase().isEqual(currentPos)) {
				currentPos.setX(board.getLadders()[i].getTop().getX());
				currentPos.setY(board.getLadders()[i].getTop().getY());
			}
		}
		for (int i = 0; i < board.getSnakes().length; i++) {
			if (board.getSnakes()[i].getHead().isEqual(currentPos)) {
				currentPos.setX(board.getSnakes()[i].getTail().getX());
				currentPos.setY(board.getSnakes()[i].getTail().getY());
			}
		}
		
	}
	
	public void displayCurrentPosition() {
		System.out.println(" " + currentPos.getX() + " " + currentPos.getY());
		
	}
}