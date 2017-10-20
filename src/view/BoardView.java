package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Board;
import model.Bot;
import model.Player;
import java.awt.Color;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JLabel;

public class BoardView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Board board;
	private int width = 40;
	private int height = 40;
	private Bot bot;
	private Player player;
	private JButton[][] arrayBtn;
	public static int size = 10;
	
	public BoardView() {
		board = new Board(size, size);
		
		this.player = new Player("Player");
    this.bot = new Bot("Bot", player);
		this.arrayBtn = new JButton[size][size];
		// Create frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 638, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Create label
		JLabel label = new JLabel();
		label.setBounds(26, 22, 140, 39);
		contentPane.add(label);
		
		// Create chess board		
		for(int i = 0; i < board.getHeight(); i++) {
			for(int j = 0; j < board.getWidth(); j++) {
				arrayBtn[i][j] = new JButton();
				arrayBtn[i][j].setBackground(Color.WHITE);
				arrayBtn[i][j].setBounds(100 + j*width, 100 + i*height, width, height);
				
				arrayBtn[i][j].addActionListener(
				    new Controller(this,
													 board,
													 arrayBtn,
													 arrayBtn[i][j], 
													 label,
													 new Point(i,j),
													 bot,
													 player));
				contentPane.add(arrayBtn[i][j]);
			}
		}
	}
}
