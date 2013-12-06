package com.haggerdfix.stockmarketgame;

import java.util.Random;

public class die {
	private int sides;
	
	public die(int v) {
		sides = v;
	}
	
	public int rollDie() {
		Random gen = new Random();
		int i = gen.nextInt(1000000);
		double prob = 1000000 / sides;
		int result = -1;
		
		for (int x = 1; x <= sides; x++) {
			if (i < x * prob) {
				result =  x;
				break;
			}
		}
		return result;
	}
}
