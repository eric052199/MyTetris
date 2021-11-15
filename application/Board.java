package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import application.Shape.Tetrominos;

public class Board extends JPanel implements ActionListener{
	
	private static final int BOARD_WIDTH = 10;
	private static final int BOARD_HEIGHT = 22;
	private static final Color[] COLORS = { new Color(0, 0, 0), 
			new Color(255, 0, 0), new Color(118, 238, 0), 
			new Color(0, 191, 255), new Color(218, 112, 214), 
			new Color(72, 118, 255), new Color(255, 165, 0), 
			new Color(255, 215, 0), new Color(190, 190, 190)};
	private Timer timer;
	private boolean isFallingFinished = false;
	private boolean isStarted = false;
	private boolean isPaused = false;
	private int LinesRemoved = 0;
	private int curX = 0;
	private int curY= 0;
	private JLabel statusBar;
	private JLabel finalBar;
	private Shape curPiece;
	private Tetrominos[] board;
	public Tetris parent;
	public Board(Tetris parent) {
		this.parent = parent;
		setFocusable(true);
		curPiece = new Shape(); 
		timer = new Timer(400, this);
		statusBar = parent.getStatusBar();
		finalBar = parent.getFinalBar();
		board = new Tetrominos[BOARD_WIDTH * BOARD_HEIGHT];
		clearBoard();
		addKeyListener(new MyTetrisAdapter());
	}
	
	public int squareWidth() {
		return (int) getSize().getWidth() / BOARD_WIDTH;
	}
	
	public int squareHeight() {
		return (int) getSize().getHeight() / BOARD_HEIGHT;
	}
	
	private Tetrominos shapeAt(int x, int y) {
		return board[y * BOARD_WIDTH + x];
	}
	
	private void pieceDropped() {
		for(int i = 0; i < 4; i++) {
			int x = curX +curPiece.x(i);
			int y = curY -curPiece.y(i);
			board[y * BOARD_WIDTH + x] = curPiece.getShape();
		}
		
		RemoveFullLines();
		
		if(!isFallingFinished) {
			newPiece();
		}
		
		
	}
	
	public void newPiece() {
		curPiece.setRandomShape();
		curX = BOARD_WIDTH / 2 + 1;
		curY = BOARD_HEIGHT - 1 + curPiece.minY();
		
		if(!tryMove(curPiece, curX, curY - 1)) {
			curPiece.setShape(Tetrominos.NoShape);
			timer.stop();
			isStarted = false;
			finalBar = new JLabel("0");
			add(finalBar, BorderLayout.SOUTH);
			finalBar.setText("Game Over" + " Final Score : " + String.valueOf(LinesRemoved));
			GameOver Over = new GameOver(parent);
			add(Over);
			statusBar.setText("0");
			
		}
	}
	
	private void oneLineDown() {
		if(!tryMove(curPiece, curX, curY - 1)) {
			pieceDropped();
		}
	}
	
	public void clearBoard() {
		for(int i = 0; i < BOARD_HEIGHT * BOARD_WIDTH; i++) {
			board[i] = Tetrominos.NoShape;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(isFallingFinished) {
			isFallingFinished = false;
			newPiece();
			
		}
		else {
			oneLineDown();
		}
	}
	
	
	private void drawSquare(Graphics g, int x, int y, Tetrominos shape) {
		Color color = COLORS[shape.ordinal()];
		g.setColor(color);
		g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);
		g.setColor(color.brighter());
		g.drawLine(x, y + squareHeight() - 1, x, y);
		g.drawLine(x, y, x + squareWidth() - 1, y);
		g.setColor(color.darker());
		g.drawLine(x + 1, y + squareHeight() - 1, x + squareWidth() - 1, y + squareHeight() - 1);
		g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1, x + squareWidth() - 1, y + 1);
	}
	
	
	//paint the board
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Dimension size = getSize();
		
		int boardTop = (int) size.getHeight() - BOARD_HEIGHT * squareHeight();
		
		for (int i = 0; i < BOARD_HEIGHT; i++) {
			for (int j = 0; j < BOARD_WIDTH; j++) {
				Tetrominos shape = shapeAt(j, BOARD_HEIGHT - i - 1);
				
				if(shape != Tetrominos.NoShape) {
					drawSquare(g, j * squareWidth(), boardTop + i * squareHeight(), shape);
				}
			}
		}
		
		if(curPiece.getShape() != Tetrominos.NoShape) {
			for (int i = 0; i < 4; i++) {
				int x = curX + curPiece.x(i);
				int y = curY - curPiece.y(i);
				drawSquare(g, x * squareWidth(), boardTop + (BOARD_HEIGHT - y - 1) * squareHeight(), curPiece.getShape());
			}
		}
		
		
	}
	
	public void start(){
		if(isPaused) {
			return;
		}
		isStarted = true;
		isFallingFinished = false;
		LinesRemoved = 0;
		clearBoard();
		newPiece();
		timer.start();
	}
	
	public void pause() {	
		if(!isStarted) {
			return;
		}
		isPaused = !isPaused;
		if(isPaused) {
			timer.stop();
			statusBar.setText("Paused");
		}
		else {
			timer.start();
			statusBar.setText(String.valueOf(LinesRemoved));
		}
		
		repaint();
	}
	
	private boolean tryMove(Shape newPiece, int newX, int newY) {
		for(int i = 0; i < 4; i ++){
				int x = newX + newPiece.x(i);
				int y = newY - newPiece.y(i);
				
				if(x < 0 || x >= BOARD_WIDTH || y < 0 || y >= BOARD_HEIGHT) {
					return false;
				}
				if(shapeAt(x, y) != Tetrominos.NoShape) {
					return false;
				}
		}
				
		curPiece = newPiece;
		curX = newX;
		curY = newY;
		repaint();
				
		return true;
		
	}
	
	private void RemoveFullLines() {
		int numFullLines = 0;
		
		for(int i = BOARD_HEIGHT - 1; i >= 0; i--) {
			boolean LineFull = true;
			
			for(int j = 0; j < BOARD_WIDTH; j++) {
				if(shapeAt(j, i) == Tetrominos.NoShape) {
					LineFull = false;
					break;
				}
			}
			
			if(LineFull) {
				numFullLines++;
				
				for(int k = i; k < BOARD_HEIGHT - 1; k++) {
					for(int j = 0; j < BOARD_WIDTH; j++) {
						board[k * BOARD_WIDTH + j] = shapeAt(j, k + 1);
					}
				}
			}
			
			if(numFullLines > 0) {
				LinesRemoved += numFullLines;
				statusBar.setText(String.valueOf(LinesRemoved));
				isFallingFinished = true;
				curPiece.setShape(Tetrominos.NoShape);
				repaint();
			}
		}
	}
	
	//add drop faster method
	private void dropFaster() {
		int newY = curY;
		while(newY > 0) {
			if(!tryMove(curPiece, curX, newY - 1)) {
				break;
			}
			newY -= 20;
		}
	}
	
	//add drop down method
	private void dropDown() {
		int newY = curY;
		
		while(newY > 0) {
			if(!tryMove(curPiece, curX, newY - 1)) 
				break;
			
			--newY;
		}
		
		pieceDropped();
	}
	
	class MyTetrisAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent ke) {
			if(!isStarted || curPiece.getShape() == Tetrominos.NoShape) {				
				return;
			}
			
			int keyCode = ke.getKeyCode();
			
			if(keyCode == 'p' || keyCode == 'P') {
				pause();
			}
			
			if(isPaused) {
				return;
			}
			
			switch(keyCode) {
			case KeyEvent .VK_LEFT:
				tryMove(curPiece, curX - 1, curY);
				break;
			case KeyEvent.VK_RIGHT:
				tryMove(curPiece, curX + 1, curY);
				break;
			case KeyEvent.VK_DOWN:
				dropFaster();
				break;
			case KeyEvent.VK_UP:
				tryMove(curPiece.rotateRight(), curX, curY);
				break;
			case KeyEvent.VK_CONTROL:
				tryMove(curPiece.rotateLeft(), curX, curY);
				break;
			case KeyEvent.VK_SPACE:
				dropDown();
				break;
			case 'd':
				oneLineDown();
				break;
			case 'D':
				oneLineDown();
				break;
			
			}
		}
	}

}

