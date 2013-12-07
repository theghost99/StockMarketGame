package com.haggerdfix.stockmarketgame;

import java.util.LinkedList;

import android.content.Context;

public class Player {
	
	private int cash;
	private int[] stocks;
	private LinkedList<gamePiece> pieces = new LinkedList<gamePiece>();
	private Context context;
	
	public Player(Context c) {
		context = c;
		cash = 5000;
		stocks = new int[]{0,0,0,0,0,0};
		pieces.add(new gamePiece(context.getString(R.string.stock1_name)));
		pieces.add(new gamePiece(context.getString(R.string.stock2_name)));
		pieces.add(new gamePiece(context.getString(R.string.stock3_name)));
		pieces.add(new gamePiece(context.getString(R.string.stock4_name)));
		pieces.add(new gamePiece(context.getString(R.string.stock5_name)));
		pieces.add(new gamePiece(context.getString(R.string.stock6_name)));
	}
	
	public int[] getStocks() {
		return stocks;
	}
	
	public LinkedList<gamePiece> getPieces() {
		return pieces;
	}
	
	public int getCash() {
		return cash;
	}
	
	public void roll(int[] r) {
		if (r[1] != 0) {
			if (r[1] == 1) {
				if (pieces.get(r[0]).up(r[2])) {
					stocks[r[0]] = stocks[r[0]] * 2;
				}
			}
			else if (r[1] == 2) {
				if (pieces.get(r[0]).down(r[2])) {
					stocks[r[0]] = 0;
				}
			}
		}
		else if (r[2] != 0) {
			double div = 0;
			switch (r[2]) {
				case 1:
					div = 0.05;
					break;
					
				case 2:
					div = 0.10;
					break;
					
				case 3:
					div = 0.20;
					break;
				
				default:
					break;
			}
			cash += (int)(stocks[r[0]] * div);
		}
	}
	
	public int getStockAmount(int s) {
		return stocks[s];
	}
	
	public int[] getAllStocks() {
		return stocks;
	}
	
	public boolean sellStock(int s, int a) {
		boolean result = false;
		int cost = (int)(Double.parseDouble(pieces.get(s).getValue()) / 100) * a;
		if (stocks[s] >= a) {
			cash += cost;
			stocks[s] -= a;
			result = true;
		}
		return result;
	}
	
	public boolean buyStock(int s, int a) {
		boolean result = false;
		int cost = (int)(Double.parseDouble(pieces.get(s).getValue()) / 100) * a;
		if (cost <= cash) {
			cash -= cost;
			stocks[s] += a;
			result = true;
		}
		return result;
	}
	
	public int getNetWorth() {
		int netWorth = 0;
		
		for (int x = 0; x < pieces.size(); x++) {
			gamePiece p = pieces.get(x);
			netWorth = (int)((Double.parseDouble(p.getValue()) / 100) * stocks[x]);
		}
		
		return netWorth;
	}
}
