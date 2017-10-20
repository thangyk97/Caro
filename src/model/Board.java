package model;

import java.awt.Point;

public class Board {
	
	private int height;
	private int width;
	private Square[][] arraySquare;	
	
	// Contructor
	public Board(int width, int height) {
		
		this.width = width;
		this.height = height;
		
		arraySquare = new Square[height][width];
		
		for (int i = 0; i < height; i ++) {
			for (int j = 0; j < width; j ++) {
				
				arraySquare[i][j] = new Square(new Point(i,j));
				
			}
		}
	}
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Square[][] getArraySquare() {
		return arraySquare;
	}

	public void setArraySquare(Square[][] arraySquare) {
		this.arraySquare = arraySquare;
	}


	

	
}
