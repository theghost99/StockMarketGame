package com.haggerdfix.stockmarketgame;

public class gamePiece {
	private String name;
	private int value;
	private int[] values = new int[]{5,10,20};
	
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
		value += values[v-1];
		if (value >= 200) {
			value = 100;
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean down(int v) {
		value -= values[v-1];
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
