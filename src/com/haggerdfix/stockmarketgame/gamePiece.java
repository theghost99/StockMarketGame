package com.haggerdfix.stockmarketgame;

public class gamePiece {
	private String name;
	private int value;
	
	public gamePiece(String n) {
		name = n;
		value = 100;
	}
	
	public boolean isDiv() {
		if ( value < 100 ) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public String getName() {
		return name;
	}
	
	public boolean up(int v) {
		value += v;
		if (value >= 200) {
			value = 100;
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean down(int v) {
		value -= v;
		if (value <= 0) {
			value = 100;
			return true;
		}
		else {
			return false;
		}
	}
	
	public String getValue() {
		return String.valueOf(value);
	}
}
