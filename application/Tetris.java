package application;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;

public class Tetris extends JFrame{
	private JLabel statusBar;
	private JLabel finalBar;
	private JLabel rulesBar;
	public Tetris() {
		statusBar = new JLabel("0");//to display line numbers
		statusBar.setFont(new Font("", Font.BOLD, 13));	
		add(statusBar, BorderLayout.SOUTH);
		
		
		menu myMenu = new menu(this);
		
		add(myMenu);
		
		setSize(200, 400);
		setTitle("My Tetris :D");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public JLabel getStatusBar() {
		return statusBar;
	}
	public JLabel getFinalBar() {		
		return finalBar;
	}
	public JLabel getSomeRules() {	
		rulesBar = new JLabel("<html><body><p>Hello and thanks for playing this game. Here are some rules you have to know before playing."
				+ "</p><br>1. the left button: move left" + "</p><br>2. the right button: move right"
				+ "</p><br>3. the up button: rotate right" + "</p><br>4. the ctrl button: rotate left"
				+ "</p><br>5. the down button: soft drop" + "</p><br>6. the space button: hard drop"
				+ "</p><br><p>7. the p button: pause game (press p again to start)" + "</p><br><p>Hope you enjoy this game! :D</p><body></html>");
		rulesBar.setFont(new Font("", Font.BOLD, 13));
		add(rulesBar, BorderLayout.NORTH);
		return rulesBar;
	}
	public static void main(String[] args) {
		Tetris myTetris = new Tetris();
		myTetris.setLocationRelativeTo(null);
		myTetris.setVisible(true);
	}
	
}

