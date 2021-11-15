package application;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GameOver extends JPanel{
	
	GameOver GameOver;
	public GameOver(Tetris parent) {
		GameOver = this;
		JButton restart = new JButton("Restart!");
		JButton hard2 = new JButton("有點難");
		JButton hard3 = new JButton("超級難");
		this.add(restart);
		restart.addActionListener(e->{
			parent.remove(GameOver);
			Board board = new Board(parent);			
			parent.add(board);
			board.requestFocus();
			board.start();
			parent.setResizable(false);
			parent.invalidate();
			parent.validate();
			board.clearBoard();
			hard3.setVisible(false);
			hard2.setVisible(false);
			restart.setVisible(false);
		});
		
		this.add(hard2);
		hard2.addActionListener(e->{
			parent.remove(GameOver);
			Board2 board2 = new Board2(parent);			
			parent.add(board2);
			board2.requestFocus();
			board2.start();
			parent.setResizable(false);
			parent.invalidate();
			parent.validate();
			board2.clearBoard();
			hard3.setVisible(false);
			hard2.setVisible(false);
			restart.setVisible(false);
		
		});
		
		this.add(hard3);
		hard3.addActionListener(e->{
			parent.remove(GameOver);
			Board3 board3 = new Board3(parent);			
			parent.add(board3);
			board3.requestFocus();
			board3.start();
			parent.setResizable(false);
			parent.invalidate();
			parent.validate();
			board3.clearBoard();
			hard3.setVisible(false);
			hard2.setVisible(false);
			restart.setVisible(false);
		
		});
	}

}
