package com.haggerdfix.stockmarketgame;

import java.util.LinkedList;

import android.app.Activity;
import android.net.nsd.NsdServiceInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static game game = null;
	private static Player player = null;
	private TextView rollInfo;
	private Handler mUpdateHandler;
	private int localPort;
	NsdHelper mNsdHelper;
    public static final String TAG = "StockTicker";
    LinkedList<ChatConnection> mConnection = new LinkedList<ChatConnection>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		rollInfo = (TextView) findViewById(R.id.rollInfo);
		
		mUpdateHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
            	String chatLine = msg.getData().getString("msg");
            	updateRoll(chatLine);
            }
		};

        mConnection.add(new ChatConnection(mUpdateHandler));
        
		mNsdHelper = new NsdHelper(this);
		mNsdHelper.initializeNsd();
	}
	
	public void joinButton(View v) {
		v = v.getRootView();
		mNsdHelper.discoverServices();
		NsdServiceInfo service = mNsdHelper.getChosenServiceInfo();
        if (service != null) {
            Log.d(TAG, "Connecting.");
            mConnection.getLast().connectToServer(service.getHost(),service.getPort());
            mConnection.getLast().sendMessage("connected");
        } else {
            Log.d(TAG, "No service to connect to!");
        }
		player = new Player(this);
		
		//hide UI buttons
		EditText gameName = (EditText) v.findViewById(R.id.gameNameTxt);
		gameName.setVisibility(View.GONE);
		Button newGameBtn = (Button) v.findViewById(R.id.new_game_button);
		newGameBtn.setVisibility(View.GONE);
		TextView error = (TextView) v.findViewById(R.id.errorLbl);
		error.setVisibility(View.INVISIBLE);
		Button readyBtn = (Button) v.findViewById(R.id.ready_button);
		readyBtn.setVisibility(View.VISIBLE);
		Button joinBtn = (Button) v.findViewById(R.id.join_button);
		joinBtn.setVisibility(View.INVISIBLE);
		TextView cashLbl = (TextView) v.findViewById(R.id.cashLbl);
		cashLbl.setVisibility(View.VISIBLE);
		TextView cashValue = (TextView) v.findViewById(R.id.cashValue);
		cashValue.setVisibility(View.VISIBLE);
		
		updatePlayerView();
	}
	
	public void updatePlayerView() {
		View v = getWindow().getDecorView().getRootView();
		TextView nameLbl = null;
		TextView valueLbl = null;
		ImageButton upBtn = null;
		ImageButton downBtn = null;
		int[] stocks = player.getStocks();
		TextView cashValue = (TextView) v.findViewById(R.id.cashValue);
		cashValue.setText("$" + String.valueOf(player.getCash()));
		TextView netWorth = (TextView) v.findViewById(R.id.netWorthValue);
		netWorth.setText("$" + String.valueOf(player.getNetWorth()));
		for (int x = 0; x < stocks.length; x++) {
			String stock = String.valueOf(stocks[x]);
			switch (x) {
			case 0:
				nameLbl = (TextView) v.findViewById(R.id.stock1Lbl);
				valueLbl = (TextView) v.findViewById(R.id.stock1Value);
				nameLbl.setText(this.getString(R.string.stock1_name) + ":");
				if (nameLbl.getVisibility() == View.INVISIBLE) {
					upBtn = (ImageButton) v.findViewById(R.id.imgButtonUp1);
					downBtn = (ImageButton) v.findViewById(R.id.imgButtonDown1);
					upBtn.setVisibility(View.VISIBLE);
					downBtn.setVisibility(View.VISIBLE);
					nameLbl.setVisibility(View.VISIBLE);
					valueLbl.setVisibility(View.VISIBLE);
				}
				valueLbl.setText(stock);
				break;

			case 1:
				nameLbl = (TextView) v.findViewById(R.id.stock2Lbl);
				valueLbl = (TextView) v.findViewById(R.id.stock2Value);
				nameLbl.setText(this.getString(R.string.stock2_name) + ":");
				if (nameLbl.getVisibility() == View.INVISIBLE) {
					upBtn = (ImageButton) v.findViewById(R.id.imgButtonUp2);
					downBtn = (ImageButton) v.findViewById(R.id.imgButtonDown2);
					upBtn.setVisibility(View.VISIBLE);
					downBtn.setVisibility(View.VISIBLE);
					nameLbl.setVisibility(View.VISIBLE);
					valueLbl.setVisibility(View.VISIBLE);
				}
				valueLbl.setText(stock);
				break;

			case 2:
				nameLbl = (TextView) v.findViewById(R.id.stock3Lbl);
				valueLbl = (TextView) v.findViewById(R.id.stock3Value);
				nameLbl.setText(this.getString(R.string.stock3_name) + ":");
				if (nameLbl.getVisibility() == View.INVISIBLE) {
					upBtn = (ImageButton) v.findViewById(R.id.imgButtonUp3);
					downBtn = (ImageButton) v.findViewById(R.id.imgButtonDown3);
					upBtn.setVisibility(View.VISIBLE);
					downBtn.setVisibility(View.VISIBLE);
					nameLbl.setVisibility(View.VISIBLE);
					valueLbl.setVisibility(View.VISIBLE);
				}
				valueLbl.setText(stock);
				break;

			case 3:
				nameLbl = (TextView) v.findViewById(R.id.stock4Lbl);
				valueLbl = (TextView) v.findViewById(R.id.stock4Value);
				nameLbl.setText(this.getString(R.string.stock4_name) + ":");
				if (nameLbl.getVisibility() == View.INVISIBLE) {
					upBtn = (ImageButton) v.findViewById(R.id.imgButtonUp4);
					downBtn = (ImageButton) v.findViewById(R.id.imgButtonDown4);
					upBtn.setVisibility(View.VISIBLE);
					downBtn.setVisibility(View.VISIBLE);
					nameLbl.setVisibility(View.VISIBLE);
					valueLbl.setVisibility(View.VISIBLE);
				}
				valueLbl.setText(stock);
				break;

			case 4:
				nameLbl = (TextView) v.findViewById(R.id.stock5Lbl);
				valueLbl = (TextView) v.findViewById(R.id.stock5Value);
				nameLbl.setText(this.getString(R.string.stock5_name) + ":");
				if (nameLbl.getVisibility() == View.INVISIBLE) {
					upBtn = (ImageButton) v.findViewById(R.id.imgButtonUp5);
					downBtn = (ImageButton) v.findViewById(R.id.imgButtonDown5);
					upBtn.setVisibility(View.VISIBLE);
					downBtn.setVisibility(View.VISIBLE);
					nameLbl.setVisibility(View.VISIBLE);
					valueLbl.setVisibility(View.VISIBLE);
				}
				valueLbl.setText(stock);
				break;

			case 5:
				nameLbl = (TextView) v.findViewById(R.id.stock6Lbl);
				valueLbl = (TextView) v.findViewById(R.id.stock6Value);
				nameLbl.setText(this.getString(R.string.stock6_name) + ":");
				if (nameLbl.getVisibility() == View.INVISIBLE) {
					upBtn = (ImageButton) v.findViewById(R.id.imgButtonUp6);
					downBtn = (ImageButton) v.findViewById(R.id.imgButtonDown6);
					upBtn.setVisibility(View.VISIBLE);
					downBtn.setVisibility(View.VISIBLE);
					nameLbl.setVisibility(View.VISIBLE);
					valueLbl.setVisibility(View.VISIBLE);
				}
				valueLbl.setText(stock);
				break;

			default:
				break;
			}
		}
		
	}
	
	public void readyButton(View v) {
		v.setVisibility(View.INVISIBLE);
        
        v = v.getRootView();
        
        rollInfo.setVisibility(View.VISIBLE);
        TextView rollLbl = (TextView) v.findViewById(R.id.rollInfoLbl);
        rollLbl.setVisibility(View.VISIBLE);
        TextView netWorthLbl = (TextView) v.findViewById(R.id.netWorthLbl);
        netWorthLbl.setVisibility(View.VISIBLE);
        TextView netWorthValue = (TextView) v.findViewById(R.id.netWorthValue);
        netWorthValue.setVisibility(View.VISIBLE);
        
        updatePlayerView();
	}
	
	public void buyStock(View v) {
		Button upBtn = (Button) v;
		int s = Integer.parseInt(upBtn.getText().toString().substring(upBtn.getText().toString().length() - 1)) - 1;
		v = v.getRootView();
		Button joinBtn = (Button) v.findViewById(R.id.join_button);
		if (joinBtn.getVisibility() == View.VISIBLE && player.getCash() >= 500) {
			player.buyStock(s, 500);
		}
		else {
			
		}
	}
	
	public void sellStock(View v) {
		Button upBtn = (Button) v;
		int s = Integer.parseInt(upBtn.getText().toString().substring(upBtn.getText().toString().length() - 1)) - 1;
		v = v.getRootView();
		Button joinBtn = (Button) v.findViewById(R.id.join_button);
		if (joinBtn.getVisibility() == View.VISIBLE && player.getStockAmount(s) >= 500) {
			player.sellStock(s, 500);
		}
		else {
			
		}
		
	}
	
	public void updateRoll(String line) {

		if (line.equalsIgnoreCase("connected") && game != null) {
	        mConnection.add(new ChatConnection(mUpdateHandler));
	        mNsdHelper.tearDown();
	        registerService();
		}
		else {
			int[] roll = parseRoll(line);
			rollInfo.setText(roll(roll));
		
			if (game != null) {
				refreshAllLabels();
			}
			else if (player != null){
				player.roll(roll);
				updatePlayerView();
			}
		}
    }
	
	public int[] parseRoll(String r) {
		int[] roll = new int[3];
		String[] rollS = r.split(",");
		roll[0] = Integer.parseInt(rollS[0]);
		roll[1] = Integer.parseInt(rollS[1]);
		roll[2] = Integer.parseInt(rollS[2]);
		
		return roll;
	}

	public void createNewGame(View v) {
		v = v.getRootView();
		registerService();
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
			Button startBtn = (Button) v.findViewById(R.id.start_button);
			startBtn.setVisibility(View.VISIBLE);
			Button joinBtn = (Button) v.findViewById(R.id.join_button);
			joinBtn.setVisibility(View.INVISIBLE);
			rollInfo.setText("Don't press 'Start' until everyone\nhas connected and is 'Ready'");
			
			/*Debug stuff!
			TextView rollCount = (TextView) v.findViewById(R.id.rollCount);
			rollCount.setText("0");
			rollCount.setVisibility(View.VISIBLE);*/
			
			refreshAllLabels();

		}
		else {
			error.setText("No Name Entered");
			error.setVisibility(View.VISIBLE);
		}
	}
	

	public void startButton(View v) {
		v = v.getRootView();
		NsdServiceInfo service = mNsdHelper.getChosenServiceInfo();
        if (service != null) {
            Log.d(TAG, "Connecting.");
            mConnection.getLast().connectToServer(service.getHost(),service.getPort());
        } else {
            Log.d(TAG, "No service to connect to!");
        }
		Button rollBtn = (Button) v.findViewById(R.id.roll_button);
		rollBtn.setVisibility(View.VISIBLE);
		Button startBtn = (Button) v.findViewById(R.id.start_button);
		startBtn.setVisibility(View.INVISIBLE);
		int[] roll = game.roll();
		String msg = String.valueOf(roll[0]) + "," + String.valueOf(roll[1]) + "," + String.valueOf(roll[2]);
		if (mConnection.getFirst().isConnected()) {
			for (int x = 0; x < mConnection.size(); x++) {
				if (mConnection.get(x).isConnected()) {
					mConnection.get(x).sendMessage(msg);
				}
			}
		}
		else {
			updateRoll(msg);
		}
	}

	public void rollButton(View v) {
		int[] roll = game.roll();
		String msg = String.valueOf(roll[0]) + "," + String.valueOf(roll[1]) + "," + String.valueOf(roll[2]);
		if (mConnection.getFirst().isConnected()) {
			for (int x = 0; x < mConnection.size(); x++) {
				if (mConnection.get(x).isConnected()) {
					mConnection.get(x).sendMessage(msg);
				}
			}
		}
		else {
			updateRoll(msg);
		}
	}
	
	public String roll(int[] roll) {
		String result = "";
		if (game != null) {
			result = game.getPieces().get(roll[0]).getName() + " ";
		}
		else if (player != null) {
			result = player.getPieces().get(roll[0]).getName() + " ";
		}
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

	public void refreshAllLabels() {
		View v = getWindow().getDecorView().getRootView();
		TextView nameLbl = null;
		TextView valueLbl = null;
		if (rollInfo.getVisibility() == View.INVISIBLE) {
			rollInfo.setVisibility(View.VISIBLE);
		}
		LinkedList<gamePiece> pieces = game.getPieces();
		for (int x = 0; x < pieces.size(); x++) {
			gamePiece piece = pieces.get(x);
			switch (x) {
			case 0:
				nameLbl = (TextView) v.findViewById(R.id.stock1Lbl);
				valueLbl = (TextView) v.findViewById(R.id.stock1Value);
				nameLbl.setText(this.getString(R.string.stock1_name) + ":");
				if (nameLbl.getVisibility() == View.INVISIBLE) {
					nameLbl.setVisibility(View.VISIBLE);
					valueLbl.setVisibility(View.VISIBLE);
				}
				valueLbl.setText(piece.getValue());
				break;

			case 1:
				nameLbl = (TextView) v.findViewById(R.id.stock2Lbl);
				valueLbl = (TextView) v.findViewById(R.id.stock2Value);
				nameLbl.setText(this.getString(R.string.stock2_name) + ":");
				if (nameLbl.getVisibility() == View.INVISIBLE) {
					nameLbl.setVisibility(View.VISIBLE);
					valueLbl.setVisibility(View.VISIBLE);
				}
				valueLbl.setText(piece.getValue());
				break;

			case 2:
				nameLbl = (TextView) v.findViewById(R.id.stock3Lbl);
				valueLbl = (TextView) v.findViewById(R.id.stock3Value);
				nameLbl.setText(this.getString(R.string.stock3_name) + ":");
				if (nameLbl.getVisibility() == View.INVISIBLE) {
					nameLbl.setVisibility(View.VISIBLE);
					valueLbl.setVisibility(View.VISIBLE);
				}
				valueLbl.setText(piece.getValue());
				break;

			case 3:
				nameLbl = (TextView) v.findViewById(R.id.stock4Lbl);
				valueLbl = (TextView) v.findViewById(R.id.stock4Value);
				nameLbl.setText(this.getString(R.string.stock4_name) + ":");
				if (nameLbl.getVisibility() == View.INVISIBLE) {
					nameLbl.setVisibility(View.VISIBLE);
					valueLbl.setVisibility(View.VISIBLE);
				}
				valueLbl.setText(piece.getValue());
				break;

			case 4:
				nameLbl = (TextView) v.findViewById(R.id.stock5Lbl);
				valueLbl = (TextView) v.findViewById(R.id.stock5Value);
				nameLbl.setText(this.getString(R.string.stock5_name) + ":");
				if (nameLbl.getVisibility() == View.INVISIBLE) {
					nameLbl.setVisibility(View.VISIBLE);
					valueLbl.setVisibility(View.VISIBLE);
				}
				valueLbl.setText(piece.getValue());
				break;

			case 5:
				nameLbl = (TextView) v.findViewById(R.id.stock6Lbl);
				valueLbl = (TextView) v.findViewById(R.id.stock6Value);
				nameLbl.setText(this.getString(R.string.stock6_name) + ":");
				if (nameLbl.getVisibility() == View.INVISIBLE) {
					nameLbl.setVisibility(View.VISIBLE);
					valueLbl.setVisibility(View.VISIBLE);
				}
				valueLbl.setText(piece.getValue());
				break;

			default:
				break;
			}
		}
	}
	
	private void registerService() {
		localPort = mConnection.getLast().getLocalPort();
		if(localPort > -1) {
            mNsdHelper.registerService(localPort);
        } else {
            Log.d(TAG, "ServerSocket isn't bound.");
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

    @Override
    protected void onPause() {
        if (mNsdHelper != null) {
            mNsdHelper.stopDiscovery();
        }
        super.onPause();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        if (mNsdHelper != null) {
            mNsdHelper.discoverServices();
        }
    }
    
    @Override
    protected void onDestroy() {
        mNsdHelper.tearDown();
        for (int x = 0; x < mConnection.size(); x++) {
        	mConnection.get(x).tearDown();
        }
        super.onDestroy();
    }

}
