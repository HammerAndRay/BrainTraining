package com.example.braintraining;

import android.os.Bundle;
import android.app.Activity;
import com.rahimbaraky.braintraining.R;

public class About extends Activity {
	/*
	 * When This class is created it uses the xml "about" for the display 
	 * This class creates the screen that displays the Rules of the game
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
	}

}
