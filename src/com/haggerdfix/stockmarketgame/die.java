package com.haggerdfix.stockmarketgame;

import java.util.Random;

public class die {
	private String[] faces;
	
	public die(String[] v) {
		faces = new String[v.length]; 
		for (int x = 0; x < v.length; x++) {
			faces[x] = v[x];
		}
	}
	
	public String rollDie() {
		Random gen = new Random();
		int i = gen.nextInt(1000000);
		double prob = 1000000 / faces.length;
		String result = "";
		
		for (int x = (faces.length - 1); x >= 0; x--) {
			if (i > x * prob) {
				result =  faces[x];
				break;
			}
		}
		return result;
	}
}
