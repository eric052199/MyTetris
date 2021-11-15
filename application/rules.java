package application;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class rules extends JPanel{
	
	private JLabel rulesBar;
	public Tetris parent;
	
	public rules(Tetris parent) {
		this.parent = parent;
		setFocusable(true);
		rulesBar = parent.getSomeRules();
		
		
		
	}
}

