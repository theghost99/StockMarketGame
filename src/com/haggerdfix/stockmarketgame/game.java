package com.haggerdfix.stockmarketgame;

public class game {
	private gamePiece[] pieces = new gamePiece[6];
	private die[] dice = new die[3];
	private String name;
	
	public game(String n) {
		name = n;
		pieces[0] = new gamePiece("Grain");
		pieces[1] = new gamePiece("Industrial");
		pieces[2] = new gamePiece("Bonds");
		pieces[3] = new gamePiece("Oil");
		pieces[4] = new gamePiece("Silver");
		pieces[5] = new gamePiece("Gold");
		String[] d1 = { "Grain", "Industrial", "Bonds", "Oil", "Silver", "Gold" };
		String[] d2 = { "Up", "Down", "Div" };
		String[] d3 = { "10","20","5" };
		dice[0] = new die(d1);
		dice[1] = new die(d2);
		dice[2] = new die(d3);
	}
	
	public String roll() {
		gamePiece piece = getPiece(dice[0].rollDie());
		String action = dice[1].rollDie();
		if (action == "Div") {
			if (piece.isDiv()) {
				return piece.getName() + " " + action + " " + dice[2].rollDie();
			}
			else {
				return piece.getName() + " Not Paying";
			}
		}
		else if (action == "Up") {
			String roll = dice[2].rollDie();
			if (piece.up(Integer.parseInt(roll))) {
				return piece.getName() + " SPLIT!!";
			}
			else {
				return piece.getName() + " Up " + roll;
			}
		}
		else {
			String roll = dice[2].rollDie();
			if (piece.down(Integer.parseInt(roll))) {
				return piece.getName() + " RESETS!!";
			}
			else {
				return piece.getName() + " Down " + roll;
			}
		}
	}
	
	private gamePiece getPiece(String n) {
		for (int x = 0; x < pieces.length; x++) {
			if (pieces[x].getName() == n) {
				return pieces[x];
			}
		}
		
		return pieces[0];
	}
	
	public gamePiece[] getPieces() {
		return pieces;
	}
	
	public String getName() {
		return name;
	}
}