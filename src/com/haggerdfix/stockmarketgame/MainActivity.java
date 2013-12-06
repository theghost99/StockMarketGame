package com.haggerdfix.stockmarketgame;

import java.util.LinkedList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static game game;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void createNewGame(View v) {
		v = v.getRootView();
		EditText gameName = (EditText) v.findViewById(R.id.gameNameTxt);
		TextView error = (TextView) v.findViewById(R.id.errorLbl);
		if (gameName != null && !gameName.getText().toString().equals("")) {
			game = new game(gameName.getText().toString(), this);
			gameName.setVisibility(View.GONE);
			TextView gameNameLbl = (TextView) v.findViewById(R.id.gameNameLbl);
			gameNameLbl.setText(game.getName());
			gameNameLbl.setVisibility(View.VISIBLE);
			Button newGameBtn = (Button) v.findViewById(R.id.new_game_button);
			newGameBtn.setVisibility(View.GONE);
			error.setVisibility(View.INVISIBLE);
			Button rollBtn = (Button) v.findViewById(R.id.roll_button);
			rollBtn.setVisibility(View.VISIBLE);
			/*TextView rollCount = (TextView) v.findViewById(R.id.rollCount);
			rollCount.setText("0");
			rollCount.setVisibility(View.VISIBLE);*/
			
			refreshAllLabels(v.getRootView());

		}
		else {
			error.setText("No Name Entered");
			error.setVisibility(View.VISIBLE);
		}
	}

	public void rollButton(View v) {
		v = v.getRootView();
		TextView rollInfo = (TextView) v.findViewById(R.id.rollInfo);
		rollInfo.setText(roll());
		if (rollInfo.getVisibility() == View.INVISIBLE) {
			rollInfo.setVisibility(View.VISIBLE);
		}
		refreshAllLabels(v);
	}
	
	public String roll() {
		String result = "";
		int[] roll = game.roll();
		
		result = game.getPieces().get(roll[0]).getName() + " ";
		if (roll[2] == 0) {
			switch (roll[1]) {
			case 0:
				result = result + "Isn't Paying!";
				break;
			
			case 1:
				result = result + "SPLIT!";
				break;
				
			case 2:
				result = result + "RESET!";
				break;
			}
		}
		else {
			switch (roll[1]) {
			case 0:
				result = result + "DIV ";
				break;
			
			case 1:
				result = result + "UP ";
				break;
				
			case 2:
				result = result + "DOWN ";
				break;
			}
			switch (roll[2]) {
			case 1:
				result = result + "5";
				break;
				
			case 2:
				result = result + "10";
				break;
				
			case 3:
				result = result + "20";
				break;
			}
		}
		
		return result;
	}

	public void refreshAllLabels(View v) {
		TextView nameLbl = null;
		TextView valueLbl = null;
		LinkedList<gamePiece> pieces = game.getPieces();
		int x = 5;
		for (gamePiece piece = pieces.pop(); piece != null; piece = pieces.pop()) {
			switch (x) {
			case 0:
				nameLbl = (TextView) v.findViewById(R.id.stock1Lbl);
				valueLbl = (TextView) v.findViewById(R.id.stock1Value);
				nameLbl.setText(this.getString(R.string.stock1_name));
				nameLbl.setVisibility(View.VISIBLE);
				valueLbl.setVisibility(View.VISIBLE);
				valueLbl.setText(piece.getValue());
				break;

			case 1:
				nameLbl = (TextView) v.findViewById(R.id.stock2Lbl);
				valueLbl = (TextView) v.findViewById(R.id.stock2Value);
				nameLbl.setVisibility(View.VISIBLE);
				nameLbl.setText(this.getString(R.string.stock2_name));
				valueLbl.setVisibility(View.VISIBLE);
				valueLbl.setText(piece.getValue());
				break;

			case 2:
				nameLbl = (TextView) v.findViewById(R.id.stock3Lbl);
				valueLbl = (TextView) v.findViewById(R.id.stock3Value);
				nameLbl.setText(this.getString(R.string.stock3_name));
				nameLbl.setVisibility(View.VISIBLE);
				valueLbl.setVisibility(View.VISIBLE);
				valueLbl.setText(piece.getValue());
				break;

			case 3:
				nameLbl = (TextView) v.findViewById(R.id.stock4Lbl);
				valueLbl = (TextView) v.findViewById(R.id.stock4Value);
				nameLbl.setText(this.getString(R.string.stock4_name));
				nameLbl.setVisibility(View.VISIBLE);
				valueLbl.setVisibility(View.VISIBLE);
				valueLbl.setText(piece.getValue());
				break;

			case 4:
				nameLbl = (TextView) v.findViewById(R.id.stock5Lbl);
				valueLbl = (TextView) v.findViewById(R.id.stock5Value);
				nameLbl.setText(this.getString(R.string.stock5_name));
				nameLbl.setVisibility(View.VISIBLE);
				valueLbl.setVisibility(View.VISIBLE);
				valueLbl.setText(piece.getValue());
				break;

			case 5:
				nameLbl = (TextView) v.findViewById(R.id.stock6Lbl);
				valueLbl = (TextView) v.findViewById(R.id.stock6Value);
				nameLbl.setText(this.getString(R.string.stock6_name));
				nameLbl.setVisibility(View.VISIBLE);
				valueLbl.setVisibility(View.VISIBLE);
				valueLbl.setText(piece.getValue());
				break;

			default:
				break;
			}
			x--;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
