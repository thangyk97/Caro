package model;

import java.awt.Point;

import view.BoardView;

public class Player {
	private String name;
	public static final int NUMBERTOWIN = 3;
	public Player(String name) {
		this.name = name;
	}
	
	public void markSquare(Point point, Square[][] arraySquare) {
		arraySquare[point.x][point.y].setChosen(true);
		arraySquare[point.x][point.y].setMark(this.name);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean checkEndGame(Point point, Square[][] arraySquare) {

		return checkEndGameHorizental( point, arraySquare)||
				checkEndGameVertical( point, arraySquare)||
				checkEndGameLeftDiagonal( point, arraySquare)||
				checkEndGameRightDiagonal( point, arraySquare);
	}

	private boolean checkEndGameRightDiagonal(Point point, 
	                                          Square[][] arraySquare) {
		
		int left = 0;
		int right = 0;
		int i = 1;
		Square square = arraySquare[point.x][point.y];
		if(square.getPoint().x - i >= 0 && square.getPoint().y + i < BoardView.size) {
			while(
		    square.getMark().equals(
		      arraySquare[square.getPoint().x - i][square.getPoint().y + i].getMark())
		    && square.getMark().equals(this.name)) {
			  
				left ++;
				i ++;
				if(square.getPoint().x - i < 0 || square.getPoint().y + i >= BoardView.size)
					break;
			}
		}
		i = 1;
		
		if(square.getPoint().x + i < BoardView.size && square.getPoint().y - i >= 0) {
			while(square.getMark().equals(arraySquare[square.getPoint().x + i][square.getPoint().y - i].getMark()) 
			    && square.getMark().equals(this.name)) {
				right ++;
				i++;
				if(square.getPoint().x + i >= BoardView.size || square.getPoint().y - i < 0)
					break;
			}
		}
		return left + right + 1 >= NUMBERTOWIN;
	}

	private boolean checkEndGameLeftDiagonal(Point point, Square[][] arraySquare) {
		
		int left = 0;
		int right = 0;
		int i = 1;
		Square square = arraySquare[point.x][point.y];
		if(square.getPoint().x - i >= 0 && square.getPoint().y -i >= 0) {
			while(square.getMark().equals(arraySquare[square.getPoint().x - i][square.getPoint().y - i].getMark()) 
			    && square.getMark().equals(this.name)) {
				left ++;
				i ++;
				if(square.getPoint().x - i < 0 || square.getPoint().y - i < 0)
					break;
			}
		}
		i = 1;
		
		if(square.getPoint().x + i < BoardView.size && square.getPoint().y + i < BoardView.size) {
			while(square.getMark().equals(arraySquare[square.getPoint().x + i][square.getPoint().y + i].getMark()) 
			    && square.getMark().equals(this.name)) {
				right ++;
				i++;
				if(square.getPoint().x + i >= BoardView.size || square.getPoint().y + i >= BoardView.size)
					break;
			}
		}
		return left + right + 1 >= NUMBERTOWIN;
	}

	private boolean checkEndGameVertical(Point point, Square[][] arraySquare) {
		
		int left = 0;
		int right = 0;
		int i = 1;
		Square square = arraySquare[point.x][point.y];
		if(square.getPoint().x - i >= 0) {
			while(square.getMark().equals(arraySquare[square.getPoint().x - i][square.getPoint().y].getMark()) 
			    && square.getMark().equals(this.name)) {
				left ++;
				i ++;
				if(square.getPoint().x - i < 0)
					break;
			}
		}
		i = 1;
		
		if(square.getPoint().x + i < BoardView.size) {
			while(square.getMark().equals(arraySquare[square.getPoint().x + i][square.getPoint().y ].getMark()) 
			    && square.getMark().equals(this.name)) {
				right ++;
				i++;
				if(square.getPoint().x + i >= BoardView.size)
					break;
			}
		}
		return left + right + 1 >= NUMBERTOWIN;
	}

	private boolean checkEndGameHorizental(Point point, Square[][] arraySquare) {
		
		int left = 0;
		int right = 0;
		int i = 1;
		Square square = arraySquare[point.x][point.y];
		if(square.getPoint().y - i >= 0) {
			while(square.getMark().equals(arraySquare[square.getPoint().x][square.getPoint().y - i].getMark()) 
			    && square.getMark().equals(this.name)) {
				left ++;
				i ++;
				if(square.getPoint().y - i < 0)
					break;
			}
		}
		i = 1;
		
		if(square.getPoint().y + i < BoardView.size) {
			while(square.getMark().equals(arraySquare[square.getPoint().x][square.getPoint().y + i].getMark()) 
			    && square.getMark().equals(this.name)) {
				right ++;
				i++;
				if(square.getPoint().y + i >= BoardView.size)
					break;
			}
		}
		return left + right + 1 >= NUMBERTOWIN;
		
	}

}

















