package com;

public class Piece {
	String color;
	boolean kinged;
	public Piece() {
		this.color = "undefined";
		this.kinged = false;
	}
	
	public Piece(String color) {
		this.color = color;
		this.kinged = false;
	}
	
	public void setKinged() {
		kinged = true;
	}
	
	public boolean isKinged() {
		return kinged;
	}
	
	public String getColor() {
		return color;
	}
}
