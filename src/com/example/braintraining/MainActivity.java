package com.example.braintraining;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View.OnClickListener;
import com.rahimbaraky.braintraining.R;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		View newbutton = findViewById(R.id.new_button);
		newbutton.setOnClickListener(this);
		View conbutton = findViewById(R.id.con_button);
		conbutton.setOnClickListener(this);
		View aboutbutton = findViewById(R.id.about_button);
		aboutbutton.setOnClickListener(this);
		View exitbutton = findViewById(R.id.exit_button);
		exitbutton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.settings:
			startActivity(new Intent(this, Prefs.class));
			return true;
		}
		return false;
	}

	// ///////////////////////////////////////////////////////////////////////////////////////////
	public void onBackPressed() {
		/*
		 * When the back button is pressed a Alert box will appear asking
		 * weather the user wants to save the game before exiting
		 */
		new AlertDialog.Builder(this)
				.setTitle("Save?")
				.setMessage("Do you wish to save the current game?")
				.setPositiveButton(android.R.string.yes,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								getPreferences(MODE_PRIVATE).edit()
										.putString("NoSave", "save").commit();
								finish();
							}
						})
				.setNegativeButton("NO", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						getPreferences(MODE_PRIVATE).edit()
								.putString("NoSave", "Dontsave").commit();
						finish();
					}
				}).show();
		return;
	}

	// ///////////////////////////////////////////////////////////////////////////////////////////
	public void onClick(View v) {
		switch (v.getId()) {
		// ///////////////////////////////////////////////////////////////////////////////////////////
		case R.id.new_button:
			Intent i = new Intent(this, Difficulty.class);
			startActivity(i);
			getPreferences(MODE_PRIVATE).edit()
					.putString("NewGame", "NewGameMade").commit();
			break;
		// ///////////////////////////////////////////////////////////////////////////////////////////
		case R.id.con_button:
			/*
			 * when the user presses the Continue button it checks weather the
			 * user saved the last game, if the user didnt save the last game a
			 * alert box will popup saying there is no save game.
			 * 
			 * If the user did save the last game then it will go stright into
			 * the game and start where he left off.
			 */
			String NoSave = getPreferences(MODE_PRIVATE)
					.getString("NoSave", "");

			if (NoSave.equals("save")) {
				Intent Game = new Intent(this, Game_screen.class).putExtra(
						"Continue", "Continued");
				startActivity(Game);
			}

			if (NoSave.equals("Dontsave")) {
				new AlertDialog.Builder(this)
						.setTitle("No Saved Game")
						.setMessage("There is no saved game")
						.setPositiveButton(android.R.string.yes,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
									}
								}).show();
			}
			break;
		// ///////////////////////////////////////////////////////////////////////////////////////////
		case R.id.about_button:
			Intent i1 = new Intent(this, About.class);
			startActivity(i1);
			break;
		// ///////////////////////////////////////////////////////////////////////////////////////////
		case R.id.exit_button:
			/*
			 * When the exit button is pressed a Alert box will appear asking
			 * weather the user wants to save the game before exiting
			 */
			new AlertDialog.Builder(this)
					.setTitle("Save?")
					.setMessage("Do you wish to save the current game?")
					.setPositiveButton(android.R.string.yes,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									getPreferences(MODE_PRIVATE).edit()
											.putString("NoSave", "save")
											.commit();
									finish();
								}
							})
					.setNegativeButton("NO",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									getPreferences(MODE_PRIVATE).edit()
											.putString("NoSave", "Dontsave")
											.commit();
									finish();
								}
							}).show();
			break;
		}
	}
}