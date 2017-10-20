package model;

import java.awt.Point;

public class Square {
	private Point point;
	private boolean isChosen;
	private String mark;
	
	public Square(Point point) {
		this.point = point;
		this.isChosen = false;
	}

	public boolean isChosen() {
		return isChosen;
	}

	public void setChosen(boolean isChosen) {
		this.isChosen = isChosen;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}
	
}
