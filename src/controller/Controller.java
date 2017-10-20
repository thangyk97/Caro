package controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.Board;
import model.Bot;
import model.Player;
import view.BoardView;

public class Controller implements ActionListener {
	private JButton button;
	private Point point;
	private Bot bot;
	private Player player;
	private Board board;
	private JButton[][] arrayBtn;
	private JLabel label;
	private BoardView boardView;
	
	public Controller(BoardView boardView,
			Board board,
			JButton[][] arrayBtn,
			JButton button,
			JLabel label,
			Point point,
			Bot bot,
			Player player) {
		super();
		this.boardView = boardView;
		this.arrayBtn = arrayBtn;
		this.button = button;
		this.point = point;
		this.bot = bot;
		this.player = player;
		this.board = board;
		this.label = label;		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Check button is marked
		if(arrayBtn[point.x][point.y].getIcon() != null ) {
			return;
		}
		
		// Set icon 
		button.setIcon(new ImageIcon( getClass().getClassLoader().getResource("imgs/x.jpg")) );
		player.markSquare(point, board.getArraySquare());
		
		if(player.checkEndGame(point, board.getArraySquare())) {
			JOptionPane.showMessageDialog(null, "player win !");
			BoardView newBoardView = new BoardView();
			newBoardView.setVisible(true);
			boardView.dispose();
			return ;
		}
		
		label.setText(bot.getName());
		
		Point square = bot.findBestMoveAlphaBeta(board.getArraySquare());
		
		bot.markSquare(square, board.getArraySquare());
		arrayBtn[square.x][square.y].setIcon(new ImageIcon(getClass().getClassLoader().getResource("imgs/o.jpg")));
		
		if(bot.checkEndGame(square, board.getArraySquare())) {
			JOptionPane.showMessageDialog(null, "bot win !");
			BoardView newBoardView = new BoardView();
			newBoardView.setVisible(true);
			boardView.dispose();
			return ;
		}
		
		label.setText(player.getName());
	}

}
