package application;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GameOver2 extends JPanel{
	
	GameOver2 GameOver2;
	public GameOver2(Tetris parent) {
		GameOver2 = this;
		JButton restart2 = new JButton("Restart!!");
		JButton hard1 = new JButton("´¶³qÃø");
		JButton hard3 = new JButton("¶W¯ÅÃø");
		this.add(restart2);
		restart2.addActionListener(e->{
			parent.remove(GameOver2);
			Board2 board2 = new Board2(parent);			
			parent.add(board2);
			board2.requestFocus();
			board2.start();
			parent.setResizable(false);
			parent.invalidate();
			parent.validate();
			board2.clearBoard();
			hard1.setVisible(false);
			hard3.setVisible(false);
			restart2.setVisible(false);
		});
		
		
		this.add(hard3);
		hard3.addActionListener(e->{
			parent.remove(GameOver2);
			Board3 board3 = new Board3(parent);			
			parent.add(board3);
			board3.requestFocus();
			board3.start();
			parent.setResizable(false);
			parent.invalidate();
			parent.validate();
			board3.clearBoard();
			hard1.setVisible(false);
			hard3.setVisible(false);
			restart2.setVisible(false);
		});
		
		
		this.add(hard1);
		hard1.addActionListener(e->{
			parent.remove(GameOver2);
			Board board = new Board(parent);			
			parent.add(board);
			board.requestFocus();
			board.start();
			parent.setResizable(false);
			parent.invalidate();
			parent.validate();
			board.clearBoard();
			hard1.setVisible(false);
			hard3.setVisible(false);
			restart2.setVisible(false);
		
		});
		
		
	}
	

}
