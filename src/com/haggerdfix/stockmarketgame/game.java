package com.haggerdfix.stockmarketgame;

import java.util.LinkedList;

import android.content.Context;

public class game {
	private LinkedList<gamePiece> pieces = new LinkedList<gamePiece>();
	private LinkedList<die> dice = new LinkedList<die>();
	private String name;
	private LinkedList<int[]> history = new LinkedList<int[]>();
	private Context context;
	
	public game(String n, Context c) {
		context = c;
		name = n;
		pieces.add(new gamePiece(context.getString(R.string.stock1_name)));
		pieces.add(new gamePiece(context.getString(R.string.stock2_name)));
		pieces.add(new gamePiece(context.getString(R.string.stock3_name)));
		pieces.add(new gamePiece(context.getString(R.string.stock4_name)));
		pieces.add(new gamePiece(context.getString(R.string.stock5_name)));
		pieces.add(new gamePiece(context.getString(R.string.stock6_name)));
		dice.add(new die(6));
		dice.add(new die(3));
		dice.add(new die(3));
	}
	
	public int[] roll() {
		int[] result = new int[3];
		result[0] = (dice.get(0).rollDie()) - 1;
		gamePiece piece = pieces.get(result[0]);
		result[1] = dice.get(1).rollDie() - 1;
		if (result[1] == 0) {
			if (piece.isDiv()) {
				result[2] = dice.get(2).rollDie();
			}
			else {
				result[2] = 0;
			}
		}
		else if (result[1] == 1) {
			result[2] = dice.get(2).rollDie();
			if (piece.up(result[2])) {
				result[2] =  0;
			}
		}
		else {
			result[2] = dice.get(2).rollDie();
			if (piece.down(result[2])) {
				result[2] = 0;
			}
		}
		history.add(result);
		return result;
	}
	
	public LinkedList<gamePiece> getPieces() {
		return pieces;
	}
	
	public String getName() {
		return name;
	}
	
	public LinkedList<int[]> getHistory() {
		return history;
	}
}
