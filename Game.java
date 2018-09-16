package com;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Game extends JPanel {
    private static final int WINDOW_WIDTH = 1000;
    private static final int WINDOW_HEIGHT = 800;
    private static final int SQUARE_SIZE = 68;
    private static final int X_START = 56;
    private static final int Y_START = 26;
    private static final int NUM_CHECKERS = 24;
    
    Board gameBoard;
	int ySquares;
	int xSquares;

	boolean gameStarted;
	boolean gameWon;
	
	boolean validSquareClicked;

    private Point mousePt;
	
	JFrame frame;
	Graphics g;
	
	public Game() {
		gameBoard = new Board();
		ySquares = gameBoard.getYSquares();
		xSquares = gameBoard.getXSquares();

		gameStarted = true;
		gameWon = false;
		
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            	// When the mouse is clicked, the click has to happen inside the board boundaries for any further action to take place.
            	// Once it has been determined the click is in bounds, the next check will determine if the square clicked contains a checker.
            	// When both conditions are met, a flag is set to indicate a valid checker has been clicked for when the mouse dragged event
            	// handler is called.
            	mousePt = e.getPoint();
            	if(clickInBounds(mousePt) && squareContainsChecker(mousePt)) {
            		validSquareClicked = true;
            	} else {
            		validSquareClicked = false;
            	}

                repaint();}
        });        
        this.addMouseMotionListener(new MouseMotionAdapter() {
        	
            @Override
            public void mouseDragged(MouseEvent e) {
                int dx = e.getX() - mousePt.x;
                int dy = e.getY() - mousePt.y;
                //origin.setLocation(origin.x + dx, origin.y + dy);
                mousePt = e.getPoint();
                //checkerPt = mousePt;
                repaint();                
            }
        });
		
	}

	public void paintComponent (Graphics g) {
		super.paintComponent(g);

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
						if (!gameBoard.isEmpty(y,x )) {
							if(gameBoard.checkerColor(y,x) == "red") {
								g2d.setPaint(Color.RED);
								Ellipse2D.Double circle = new Ellipse2D.Double(currentX,currentY, SQUARE_SIZE, SQUARE_SIZE);
								g2d.fill(circle);
								//g2d.drawOval(currentX, currentY, SQUARE_SIZE, SQUARE_SIZE);

							} else if (gameBoard.checkerColor(y, x) == "white") {
								g2d.setPaint(Color.LIGHT_GRAY);
								Ellipse2D.Double circle = new Ellipse2D.Double(currentX,currentY, SQUARE_SIZE, SQUARE_SIZE);
								g2d.fill(circle);
							}
						}
						currentX += SQUARE_SIZE;						
					}
				} else {
					if(x%2 == 0) {
						g2d.setPaint( Color.BLACK);
						g2d.fill( new Rectangle2D.Double( currentX, currentY, SQUARE_SIZE, SQUARE_SIZE));
						if (!gameBoard.isEmpty(y,x )) {
							if(gameBoard.checkerColor(y,x) == "red") {
								g2d.setPaint(Color.RED);
								Ellipse2D.Double circle = new Ellipse2D.Double(currentX,currentY, SQUARE_SIZE, SQUARE_SIZE);
								g2d.fill(circle);
							} else if (gameBoard.checkerColor(y, x) == "white") {
								g2d.setPaint(Color.LIGHT_GRAY);
								Ellipse2D.Double circle = new Ellipse2D.Double(currentX,currentY, SQUARE_SIZE, SQUARE_SIZE);
								g2d.fill(circle);
							}
						}
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
		
		/*
		for (int i=0; i<NUM_CHECKERS; i++) {
			g2d.setPaint(Color.RED);
			System.out.println("X: " + checkerPt[i].getX() + " Y: " + checkerPt[i].getY());
			g2d.drawOval((int) checkerPt[i].getX(), (int) checkerPt[i].getY(), 60, 60);
		}*/
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
	
	private boolean clickInBounds(Point mousePt) {
		int boardXMax = X_START + (SQUARE_SIZE * 8);
		int boardYMax = Y_START + (SQUARE_SIZE * 8);
		
		int mousePtX = (int) mousePt.getX();
		int mousePtY = (int) mousePt.getY();
		if (mousePtX >= X_START && mousePtX <= boardXMax && mousePtY >= Y_START && mousePtY <= boardYMax) {
        	return true;
        } else {
        	return false;
        }
	}
	
	private boolean squareContainsChecker(Point mousePt) {
		double mousePtX = mousePt.getX();
		double mousePtY = mousePt.getY();
		
		int xSquare = (int) (mousePtX - X_START) / SQUARE_SIZE;
		int ySquare = (int) (mousePtY - Y_START) / SQUARE_SIZE;

		if (!gameBoard.isEmpty(ySquare, xSquare)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String args[]) {
		Game panel = new Game();
	    JFrame frame = new JFrame("Checkers");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.add(panel);
	    frame.pack();
	    frame.setLocationRelativeTo(null);
	    
	    frame.setSize(1000, 800);
	    frame.setVisible(true);
    }
}

