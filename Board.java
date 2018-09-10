package com;

public class Board {
	public static final int xSquares = 10; //  A checker board has 10 squares on the x axis
	public static final int ySquares = 10; // A checker board has 10 squares on the y axis
	public static final int mumOfPieces = 20; // Each player will have 20 pieces
	
	Piece[][] gameBoard = new Piece[ySquares][xSquares];
	
	public Board () {
		
	}

	private void populateBoard() {
		for (int y=0; y<ySquares; y++) {
			for (int x=0; x<xSquares; x++) {
				
			}
		}
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
	
	private boolean placePiece (int y, int x) {
		if ((y>=0 && y<=3) || (y >=6 && y <= 9) ) {
			if (isBlackSquare(y,x)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
}
