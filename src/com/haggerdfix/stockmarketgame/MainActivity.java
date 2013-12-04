package com.haggerdfix.stockmarketgame;

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
			game = new game(gameName.getText().toString());
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
			
			refreshLabels(v.getRootView());

		}
		else {
			error.setText("No Name Entered");
			error.setVisibility(View.VISIBLE);
		}
	}

	public void rollButton(View v) {
		v = v.getRootView();
		TextView rollInfo = (TextView) v.findViewById(R.id.rollInfo);
		rollInfo.setText(game.roll());
		if (rollInfo.getVisibility() == View.INVISIBLE) {
			rollInfo.setVisibility(View.VISIBLE);
		}
		/*TextView rollCount = (TextView) v.findViewById(R.id.rollCount);
		int i = Integer.parseInt(rollCount.getText().toString());
		i++;
		rollCount.setText(String.valueOf(i));*/
		refreshLabels(v);
	}

	public void refreshLabels(View v) {
		TextView valueLbl = null;
		TextView nameLbl = null;
		gamePiece[] pieces = game.getPieces();
		for (int x = 0; x < pieces.length; x++) {
			switch (x) {
			case 0:
				nameLbl = (TextView) v.findViewById(R.id.grainLbl);
				valueLbl = (TextView) v.findViewById(R.id.grainValue);
				nameLbl.setVisibility(View.VISIBLE);
				valueLbl.setVisibility(View.VISIBLE);
				valueLbl.setText(pieces[x].getValue());
				break;

			case 1:
				nameLbl = (TextView) v.findViewById(R.id.industrialLbl);
				valueLbl = (TextView) v.findViewById(R.id.industrialValue);
				nameLbl.setVisibility(View.VISIBLE);
				valueLbl.setVisibility(View.VISIBLE);
				valueLbl.setText(pieces[x].getValue());
				break;

			case 2:
				nameLbl = (TextView) v.findViewById(R.id.bondsLbl);
				valueLbl = (TextView) v.findViewById(R.id.bondsValue);
				nameLbl.setVisibility(View.VISIBLE);
				valueLbl.setVisibility(View.VISIBLE);
				valueLbl.setText(pieces[x].getValue());
				break;

			case 3:
				nameLbl = (TextView) v.findViewById(R.id.oilLbl);
				valueLbl = (TextView) v.findViewById(R.id.oilValue);
				nameLbl.setVisibility(View.VISIBLE);
				valueLbl.setVisibility(View.VISIBLE);
				valueLbl.setText(pieces[x].getValue());
				break;

			case 4:
				nameLbl = (TextView) v.findViewById(R.id.silverLbl);
				valueLbl = (TextView) v.findViewById(R.id.silverValue);
				nameLbl.setVisibility(View.VISIBLE);
				valueLbl.setVisibility(View.VISIBLE);
				valueLbl.setText(pieces[x].getValue());
				break;

			case 5:
				nameLbl = (TextView) v.findViewById(R.id.goldLbl);
				valueLbl = (TextView) v.findViewById(R.id.goldValue);
				nameLbl.setVisibility(View.VISIBLE);
				valueLbl.setVisibility(View.VISIBLE);
				valueLbl.setText(pieces[x].getValue());
				break;

			default:
				break;
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
