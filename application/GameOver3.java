package application;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GameOver3 extends JPanel{
	
	GameOver3 GameOver3;
	public GameOver3(Tetris parent) {
		GameOver3 = this;
		JButton restart3 = new JButton("Restart!!!");
		JButton hard1 = new JButton("普通難");
		JButton hard2 = new JButton("有點難");
		this.add(restart3);
		restart3.addActionListener(e->{
			parent.remove(GameOver3);
			Board3 board3 = new Board3(parent);			
			parent.add(board3);
			board3.requestFocus();
			board3.start();
			parent.setResizable(false);
			parent.invalidate();
			parent.validate();
			board3.clearBoard();
			hard1.setVisible(false);
			hard2.setVisible(false);
			restart3.setVisible(false);
		});
		
		this.add(hard1);
		hard1.addActionListener(e->{
			parent.remove(GameOver3);
			Board board = new Board(parent);			
			parent.add(board);
			board.requestFocus();
			board.start();
			parent.setResizable(false);
			parent.invalidate();
			parent.validate();
			board.clearBoard();
			hard1.setVisible(false);
			hard2.setVisible(false);
			restart3.setVisible(false);
		
		});
		
		this.add(hard2);
		hard2.addActionListener(e->{
			parent.remove(GameOver3);
			Board2 board2 = new Board2(parent);			
			parent.add(board2);
			board2.requestFocus();
			board2.start();
			parent.setResizable(false);
			parent.invalidate();
			parent.validate();
			board2.clearBoard();
			hard1.setVisible(false);
			hard2.setVisible(false);
			restart3.setVisible(false);
		
		});
	}

}
