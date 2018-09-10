package com;

public class Game {
	Board gameBoard;
	boolean gameStarted;
	boolean gameWon;
	
	public Game() {
		gameBoard = new Board();
		gameStarted=false;
		gameWon=false;
		
		newGame();
	}
	
	private void newGame() {
		gameBoard = new Board();
		gameStarted=true;
		
		drawBoard();
	}
	
	private void drawBoard() {
		int y = gameBoard.getYSquares();
		int x = gameBoard.getXSquares();
		
		for (int i=0; i<y; i++) {
			for (int j=0; j<x; j++) {
				
				if (gameBoard.isEmpty(i,j) ) {
					
					System.out.print("| |");
				} else {
					String color = gameBoard.checkerColor(i, j);
					boolean kinged = gameBoard.isKinged(i, j);
					if (color == "red") {
						System.out.print("|R|");
					} else {
						System.out.print("|W|");
					}
				}
			}
			System.out.print("\n");
		}
	}
	public static void main(String[] args) {
		System.out.println("Welcome");
		Game myGame = new Game();
	}
}
