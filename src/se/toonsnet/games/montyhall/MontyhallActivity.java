package se.toonsnet.games.montyhall;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MontyhallActivity extends Activity implements OnClickListener {
	private Button startButton;
	
	private EditText editTextGames;
	
	private ProgressBar progressBar;
	
	private int numberOfGames = 0;
	private int nrOfWinnings = 0;
	private int mProgressStatus = 0;
	
	private final Door[] doorArray = new Door[3];

	private final Random randomGenerator = new Random();
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
    
		editTextGames = (EditText) findViewById(R.id.editTextGames);
		
		startButton = (Button) findViewById(R.id.startButton);
		startButton.setOnClickListener(this);
		
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		progressBar.setClickable(false);
		
		doorArray[0] = new Door(1);
		doorArray[1] = new Door(2);
		doorArray[2] = new Door(3);
	}
 
	@Override
	public void onClick(View v) {
		if ((Button)v == startButton) {
			startGame();
		}
	}
	
	private void resetGame() {
		String stringNumberOfGames = editTextGames.getText().toString();
		numberOfGames = (stringNumberOfGames.isEmpty() ? 1 : Integer.parseInt(stringNumberOfGames));
		
		nrOfWinnings = 0;
		mProgressStatus = 0;
	}
	
	private void initilizeGame() {
		for (Door d : doorArray) {
			d.setPrice(false);
			d.setOpen(false);
			d.setChosen(false);
		}
		
		// We set price
		doorArray[randomNumberGenerator()].setPrice(true);
		
		// Player choose door
		doorArray[randomNumberGenerator()].setChosen(true);
	}
	
	private void startGame() {
		resetGame();
		
		for (int i = 0; i <= numberOfGames; i++) {
			initilizeGame();
			
			// Open the door with no price that has not been chosen
			openFakeDoor();
			
			// Player switch door
			switchDoor();
	
			for (Door d : doorArray) {
				if (d.isChosen() && d.hasPrice()) {
					nrOfWinnings++;
				}
			}
		}
		
		mProgressStatus = ((nrOfWinnings * 100) / numberOfGames);
		progressBar.setProgress(mProgressStatus);
		
		Toast.makeText(getApplicationContext(), mProgressStatus + "%, Wins: " + nrOfWinnings, Toast.LENGTH_SHORT).show();
	}
		
	private int randomNumberGenerator() {
		return randomGenerator.nextInt(3);
	}
	
	private void openFakeDoor() {
		for (Door d : doorArray) {
			if (!d.isChosen() && !d.hasPrice()) {
				d.setOpen(true);
				break;
			}
		}
	}
	
	private void switchDoor() {
		for (Door d : doorArray) {
			if (d.isChosen()) {
				d.setChosen(false);
			} else if (!d.isOpen()) {
				d.setChosen(true);
			}
		}
	}
}
