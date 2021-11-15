package application;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.javafx.css.Rule;



public class menu extends JPanel{
	
	menu menu;
	rules rule;
	public Tetris parent;
	//tutorial tutorial;
	public menu(Tetris parent) {
		this.parent = parent;	
		menu = this;

		JButton back = new JButton("back");
		back.addActionListener(e->{						
			parent.removeAll();
			parent.revalidate();
			menu menu = new menu(parent);
			parent.add(menu);	
			menu.requestFocus();
			parent.setResizable(false);
			parent.invalidate();
			parent.validate();
		});
		
		JButton start = new JButton("普通難");
		this.add(start);
		start.addActionListener(e1->{
			parent.remove(menu);
			Board board = new Board(parent);			
			parent.add(board);
			board.requestFocus();
			board.start();
			parent.setResizable(false);
			parent.invalidate();
			parent.validate();
		
		});
		
		JButton start2 = new JButton("有點難");
		this.add(start2);
		start2.addActionListener(e2->{
			parent.remove(menu);
			Board2 board2 = new Board2(parent);			
			parent.add(board2);
			board2.requestFocus();
			board2.start();
			parent.setResizable(false);
			parent.invalidate();
			parent.validate();
		
		});
		
		JButton start3 = new JButton("超級難");
		this.add(start3);
		start3.addActionListener(e->{
			parent.remove(menu);
			Board3 board3 = new Board3(parent);			
			parent.add(board3);
			board3.requestFocus();
			board3.start();
			parent.setResizable(false);
			parent.invalidate();
			parent.validate();
		
		});
		
		
		JButton TutorialButton = new JButton("tutorial");
		this.add(TutorialButton);
		TutorialButton.addActionListener(e->{
			
			parent.remove(menu);
			rules rule = new rules(parent);			
			parent.add(rule);
			rule.requestFocus();				
			//parent.add(back);
			parent.setResizable(false);
			parent.invalidate();
			parent.validate();
		
		});
	}

}
