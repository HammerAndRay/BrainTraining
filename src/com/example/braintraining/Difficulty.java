package com.example.braintraining;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.content.Intent;
import com.rahimbaraky.braintraining.R;

public class Difficulty extends Activity implements OnClickListener {
	/*
	 * This class when created gives the user 4 difficulty setting that
	 * they can select. 
	 */
/////////////////////////////////////////////////////////////////////
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.difficulty);

		View novicebutton = findViewById(R.id.novice_button);
		novicebutton.setOnClickListener(this);

		View easybutton = findViewById(R.id.easy_button);
		easybutton.setOnClickListener(this);

		View mediumbutton = findViewById(R.id.medium_button);
		mediumbutton.setOnClickListener(this);

		View gurubutton = findViewById(R.id.guru_button);
		gurubutton.setOnClickListener(this);

	}
	/////////////////////////////////////////////////////////////////////
/*
 * When a difficulty is selected the Game_screen activity is created
 * and the difficulty is passed through as a string.
 */
/////////////////////////////////////////////////////////////////////
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.novice_button:
			Intent noviceDifficulty = new Intent(this, Game_screen.class).putExtra("Difficulty", "novice");
			startActivity(noviceDifficulty);
			finish();
			break;
		case R.id.easy_button:
			Intent easyDifficulty = new Intent(this, Game_screen.class).putExtra("Difficulty", "easy");
			startActivity(easyDifficulty);
			finish();
			break;
		case R.id.medium_button:
			Intent mediumDifficulty = new Intent(this, Game_screen.class).putExtra("Difficulty", "medium");
			startActivity(mediumDifficulty);
			finish();
			break;
		case R.id.guru_button:
			Intent guruDifficulty = new Intent(this, Game_screen.class).putExtra("Difficulty", "guru");
			startActivity(guruDifficulty);
			finish();
			break;

		}
/////////////////////////////////////////////////////////////////////
		
	}
}
