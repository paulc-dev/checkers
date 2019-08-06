package com;

public class Board {
	// This board object will keep track of the state of the board.
	
	public static final int xSquares = 10; //  A checker board has 10 squares on the x axis
	public static final int ySquares = 10; // A checker board has 10 squares on the y axis
	public static final int mumOfPieces = 20; // Each player will have 20 pieces
	
	Piece[][] gameBoard;
	
	public Board () {
		gameBoard = new Piece[ySquares][xSquares];
		populateBoard();
	}

	public boolean isEmpty(int y, int x) {
		if (gameBoard[y][x] != null) { 
			return false;
		} else {
			return true;
		}
	}
	
	public String checkerColor(int y, int x) {
		if (gameBoard[y][x] != null ) {
			return gameBoard[y][x].getColor();
		} else {
			return null;
		}
	}
	
	public boolean isKinged(int y, int x) {
		if (gameBoard[y][x] != null ) {
			return gameBoard[y][x].isKinged();
		} else {
			return false;
		}
	}
	
	public int getXSquares() {
		return xSquares;
	}
	
	public int getYSquares() {
		return ySquares;
	}
	
	private boolean isBlackSquare(int y, int x) {
		// When y is even, black squares will occur where x is odd
		// When y is odd, black squares will occur where x is even
		if (y%2 == 0) {
			if (x%2 == 0) {
				return false;
			} else {
				return true;
			}
		} else {
			if (x%2 == 0) {
				return true;
			} else {
				return false;
			}
		}
		
	}
	
	private void populateBoard() {
		for (int y=0; y<ySquares; y++) {
			for (int x=0; x<xSquares; x++) {
				if ( (y>=0 && y<=3) && isBlackSquare(y,x)) {
					Piece currentPiece = new Piece("white");
					gameBoard[y][x] = currentPiece;
				} else if ( (y>=6 && y<=9) && isBlackSquare(y,x) ) {
					Piece currentPiece = new Piece("red");
					gameBoard[y][x] = currentPiece;
				} else {
					gameBoard[y][x] = null;
				}	
			}
		}
	}
}
