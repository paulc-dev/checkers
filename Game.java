package com;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Game extends JPanel {
    final int WINDOW_WIDTH = 1000;
    final int WINDOW_HEIGHT = 800;
    
    final int SQUARE_SIZE = 68;
    final int X_START = 56;
    final int Y_START = 26;
    
    Board gameBoard;
	int ySquares;
	int xSquares;

	boolean gameStarted;
	boolean gameWon;
	
	JFrame frame;
	Graphics g;
	
	
	public Game() {
		gameBoard = new Board();
		ySquares = gameBoard.getYSquares();
		xSquares = gameBoard.getXSquares();
		gameStarted = true;
		gameWon = false;
		
	}
	
	public void paintComponent (Graphics g) {
		super.paintComponent( g );

		Graphics2D g2d = (Graphics2D ) g;

		int currentY = Y_START;
		int currentX = X_START;
		for(int y = 0; y < ySquares; y++) {
			for( int x = 0; x < xSquares; x++) {
				if(y%2 == 0) {
					if(x%2 == 0) {
						g2d.setPaint( Color.WHITE);
						g2d.fill( new Rectangle2D.Double( currentX, currentY, SQUARE_SIZE, SQUARE_SIZE));
						currentX += SQUARE_SIZE;
					} else {
						g2d.setPaint( Color.BLACK);
						g2d.fill( new Rectangle2D.Double( currentX, currentY, SQUARE_SIZE, SQUARE_SIZE));
						currentX += SQUARE_SIZE;						
					}
				} else {
					if(x%2 == 0) {
						g2d.setPaint( Color.BLACK);
						g2d.fill( new Rectangle2D.Double( currentX, currentY, SQUARE_SIZE, SQUARE_SIZE));
						currentX += SQUARE_SIZE;						
					} else {
						g2d.setPaint( Color.WHITE);
						g2d.fill( new Rectangle2D.Double( currentX, currentY, SQUARE_SIZE, SQUARE_SIZE));
						currentX += SQUARE_SIZE;						
					}					
				}
            }
			currentY += SQUARE_SIZE;
			currentX = X_START;
        }
	}
	

	
	private void newGame() {
		gameBoard = new Board();
		gameStarted=true;
		
		drawBoard();
	}
	
	private void drawBoard() {

		
		g = frame.getGraphics();
		
		for (int i=0; i<ySquares; i++) {
			for (int j=0; j<xSquares; j++) {
				
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
	public static void main(String args[]) {
	    JFrame frame = new JFrame("Checkers");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    Game panel = new Game();
	    
	    frame.add(panel);

	    frame.setSize(1000, 800);
	    frame.setVisible(true);
    }
}
