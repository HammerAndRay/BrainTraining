package com.example.braintraining;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import com.rahimbaraky.braintraining.R;

/*
 * When is class is created it contains the main game
 * it has a number pad which the uses presses to answer
 * the questions before the timer runs out
 */
public class Game_screen extends Activity implements OnClickListener {
	/*
	 * These global variables are used to store the answer to the question, the
	 * question that the user is on how many correct answers the user has and
	 * the score the users gets per question based on how long they took. There
	 * is also the amount time left after the users answers the question.
	 */
	int Answer = 0;
	int Question = 1;
	int Correct = 0;
	int Score = 0;
	Timer Count;
	int TimeLeft;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_screen);
		View num1 = findViewById(R.id.num1_button);
		View num2 = findViewById(R.id.num2_button);
		View num3 = findViewById(R.id.num3_button);
		View num4 = findViewById(R.id.num4_button);
		View num5 = findViewById(R.id.num5_button);
		View num6 = findViewById(R.id.num6_button);
		View num7 = findViewById(R.id.num7_button);
		View num8 = findViewById(R.id.num8_button);
		View num9 = findViewById(R.id.num9_button);
		View num0 = findViewById(R.id.num0_button);
		View numHash = findViewById(R.id.numHash_button);
		View numDel = findViewById(R.id.numDel_button);
		View numMinus = findViewById(R.id.numMinus_button);
		num1.setOnClickListener(this);
		num2.setOnClickListener(this);
		num3.setOnClickListener(this);
		num4.setOnClickListener(this);
		num5.setOnClickListener(this);
		num6.setOnClickListener(this);
		num7.setOnClickListener(this);
		num8.setOnClickListener(this);
		num9.setOnClickListener(this);
		num0.setOnClickListener(this);
		numHash.setOnClickListener(this);
		numDel.setOnClickListener(this);
		numMinus.setOnClickListener(this);
		// As soon as this class is created it checks if this is a Continued
		// game
		CheckIfContinued();
		// It then starts the Timer
		StartTimer();
		// Then a question is presented to the user based on the difficulty they
		// Selected
		Expresstion_generator_based_on_difficulty();

	}

	/*
	 * This method checks if the game is a new one or if its being Continued
	 */
	public void CheckIfContinued() {
		String IsContinued = getIntent().getStringExtra("Continue");
		/*
		 * If IsContinued is null that means that its a new game
		 */
		if (IsContinued == null) {
		}
		/*
		 * If it isnt null that means the game is being Continued. The values of
		 * Question, Score and Correct and then updated to the last save
		 */
		if (IsContinued != null) {
			Question = getPreferences(MODE_PRIVATE).getInt("WhatQuestion", 0);
			Score = getPreferences(MODE_PRIVATE).getInt("WhatScore", 0);
			Correct = getPreferences(MODE_PRIVATE).getInt("HowManyCorrect", 0);
		}
		TextView SetQuestionText = (TextView) findViewById(R.id.textView5);
		SetQuestionText.setText("" + Question);
	}

	/*
	 * This method starts the timer
	 */
	public void StartTimer() {
		Count = new Timer();
	}

	/*
	 * This method ends the timer
	 */
	public void EndTimer() {
		Count.End();
	}

	/*
	 * This method Calculate how much time is left
	 */
	public void HowMuchTimeLeft() {
		TimeLeft = Count.HowMuchTimeLeft();
	}

	/*
	 * This method stores the data that is Necessary data in order to Continue
	 * the game from the menu. It also pauses the timer should the user press
	 * the home button and do something else.
	 */
	@Override
	protected void onPause() {
		EndTimer();
		String Difficulty = getIntent().getStringExtra("Difficulty");
		if (Difficulty == null) {
			Difficulty = getPreferences(MODE_PRIVATE).getString(
					"WhatDifficulty", "");
		}
		super.onPause();
		getPreferences(MODE_PRIVATE).edit().putInt("WhatQuestion", Question)
				.putInt("HowManyCorrect", Correct)
				.putString("WhatDifficulty", Difficulty)
				.putInt("WhatScore", Score).commit();
	}

	/*
	 * This method restarts the timer when the user returns to the program.
	 */
	@Override
	protected void onResume() {
		super.onResume();
		EndTimer();
		StartTimer();

	}

	/*
	 * This method uses the class Generator to create expressions based on the
	 * Difficulty the user selected
	 */
	public void Expresstion_generator_based_on_difficulty() {
		String Difficulty = getIntent().getStringExtra("Difficulty");
		if (Difficulty == null) {
			Difficulty = getPreferences(MODE_PRIVATE).getString(
					"WhatDifficulty", "");
		}
		if (Difficulty.equals("novice")) {
			novice();
		} else if (Difficulty.equals("easy")) {
			easy();
		} else if (Difficulty.equals("medium")) {
			medium();
		} else if (Difficulty.equals("guru")) {
			guru();
		}
	}

	/*
	 * This method creates an object of the class Generator with the set
	 * Difficulty
	 */
	public void novice() {
		Generator solve = new Generator();
		solve.novice();
		Answer = solve.getEquationResult();
		TextView Equation = (TextView) findViewById(R.id.textView3);
		Equation.setText(solve.getEquation());
	}

	/*
	 * This method creates an object of the class Generator with the set
	 * Difficulty
	 */
	public void easy() {
		Generator solve = new Generator();
		solve.easy();
		Answer = solve.getEquationResult();
		TextView Equation = (TextView) findViewById(R.id.textView3);
		Equation.setText(solve.getEquation());
	}

	/*
	 * This method creates an object of the class Generator with the set
	 * Difficulty
	 */
	public void medium() {
		Generator solve = new Generator();
		solve.medium();
		Answer = solve.getEquationResult();
		TextView Equation = (TextView) findViewById(R.id.textView3);
		Equation.setText(solve.getEquation());

	}

	/*
	 * This method creates an object of the class Generator with the set
	 * Difficulty
	 */
	public void guru() {
		Generator solve = new Generator();
		solve.guru();
		Answer = solve.getEquationResult();
		TextView Equation = (TextView) findViewById(R.id.textView3);
		Equation.setText(solve.getEquation());
	}

	int Tries = 0;

	/*
	 * This method is called when the users presses the # button. First it
	 * checks if the Question limit is up, if it is it will start the Score
	 * activity and pass through the users Score.
	 */
	public void Enter() {
		EndTimer();
		if (Question >= 10) {

			System.out.println("This is Game" + Score);
			Intent TheScore = new Intent(this, Score.class).putExtra("Score",
					Integer.toString(Score));
			EndTimer();
			startActivity(TheScore);
			finish();
		} else {

			EditText editText22 = (EditText) findViewById(R.id.editText1);
			Integer Guess = 0;
			try {
				Guess = Integer.parseInt(editText22.getText().toString());
			} catch (NumberFormatException nfe) {
			}

			TextView CorrectOrNot = (TextView) findViewById(R.id.textView1);
			/*
			 * If the Hints is on it will allow the user to try each question 4
			 * times. Below checks if the Hints is on or off.
			 */
			if (Prefs.getHints(this)) {
				if (Answer == Guess) {
					CorrectOrNot.setTextColor(Color.GREEN);
					CorrectOrNot.setText("Correct");
					Correct++;
					/*
					 * The score is worked out by 100 divided by 10 take away
					 * from how much time was left.
					 */
					Score = Score + 100 / (10 - TimeLeft);
					Question++;
					reset();
					/*
					 * Should the user enter the wrong answer it will allow them
					 * to retry as well as tell them if the answer they entered
					 * is greater or less then the actual answer.
					 */
				} else if (Tries < 4) {
					if (Guess > Answer) {
						CorrectOrNot.setTextColor(Color.MAGENTA);
						CorrectOrNot.setText("Greater");
						Tries = Tries + 1;
						EndTimer();
						StartTimer();

					}
					if (Guess < Answer) {
						CorrectOrNot.setTextColor(Color.MAGENTA);
						CorrectOrNot.setText("less");
						Tries = Tries + 1;
						EndTimer();
						StartTimer();
					}
				}
				/*
				 * Once the 4 tries is over, it will check if the last answer is
				 * correct or not and act accordingly
				 */
				if (Tries >= 4) {
					if (Answer == Guess) {
						CorrectOrNot.setTextColor(Color.GREEN);
						CorrectOrNot.setText("Correct");
						Correct++;
						/*
						 * The score is worked out by 100 divided by 10 take
						 * away from how much time was left.
						 */
						Score = Score + 100 / (10 - TimeLeft);
						Question++;
						reset();
					} else if (Answer != Guess) {
						Question++;
						CorrectOrNot.setTextColor(Color.RED);
						CorrectOrNot.setText("Incorrect");
						Tries = 0;
						reset();
					}
				}
			}

			else {
				if (Answer == Guess) {
					CorrectOrNot.setTextColor(Color.GREEN);
					CorrectOrNot.setText("Correct");
					Correct++;
					/*
					 * The score is worked out by 100 divided by 10 take away
					 * from how much time was left.
					 */
					Score = Score + 100 / (10 - TimeLeft);
					System.out.println(Score);
					Question++;
					System.out.println("Correct " + Correct);
					reset();
				} else {
					Question++;
					CorrectOrNot.setTextColor(Color.RED);
					CorrectOrNot.setText("Incorrect");
					reset();
				}

			}
		}
	}

	/*
	 * This method is used to reset the expression and timer once the question
	 * is answered.
	 */
	public void reset() {
		TextView SetQuestionText = (TextView) findViewById(R.id.textView5);
		SetQuestionText.setText("" + Question);
		EditText editText22 = (EditText) findViewById(R.id.editText1);
		editText22.setText(" ?");
		Expresstion_generator_based_on_difficulty();
		EndTimer();
		StartTimer();
	}

	@Override
	public void onClick(View v) {
		EditText editText = (EditText) findViewById(R.id.editText1);

		switch (v.getId()) {
		case R.id.num1_button:
			if (editText.getText().toString().equals(" ?")) {
				editText.setText("");
			}
			String editTextStr = editText.getText().toString();
			editText.setText(editTextStr + "1");
			break;
		case R.id.num2_button:
			if (editText.getText().toString().equals(" ?")) {
				editText.setText("");
			}
			String editTextStr1 = editText.getText().toString();
			editText.setText(editTextStr1 + "2");
			break;
		case R.id.num3_button:
			if (editText.getText().toString().equals(" ?")) {
				editText.setText("");
			}
			String editTextStr2 = editText.getText().toString();
			editText.setText(editTextStr2 + "3");
			break;
		case R.id.num4_button:
			if (editText.getText().toString().equals(" ?")) {
				editText.setText("");
			}
			String editTextStr3 = editText.getText().toString();
			editText.setText(editTextStr3 + "4");
			break;
		case R.id.num5_button:
			if (editText.getText().toString().equals(" ?")) {
				editText.setText("");
			}
			String editTextStr4 = editText.getText().toString();
			editText.setText(editTextStr4 + "5");
			break;
		case R.id.num6_button:
			if (editText.getText().toString().equals(" ?")) {
				editText.setText("");
			}
			String editTextStr5 = editText.getText().toString();
			editText.setText(editTextStr5 + "6");
			break;
		case R.id.num7_button:
			if (editText.getText().toString().equals(" ?")) {
				editText.setText("");
			}
			String editTextStr6 = editText.getText().toString();
			editText.setText(editTextStr6 + "7");
			break;
		case R.id.num8_button:
			if (editText.getText().toString().equals(" ?")) {
				editText.setText("");
			}
			String editTextStr7 = editText.getText().toString();
			editText.setText(editTextStr7 + "8");
			break;
		case R.id.num9_button:
			if (editText.getText().toString().equals(" ?")) {
				editText.setText("");
			}
			String editTextStr8 = editText.getText().toString();
			editText.setText(editTextStr8 + "9");
			break;
		case R.id.num0_button:
			if (editText.getText().toString().equals(" ?")) {
				editText.setText("");
			}
			String editTextStr9 = editText.getText().toString();
			editText.setText(editTextStr9 + "0");
			break;
		case R.id.numDel_button:
			if (editText.getText().toString().equals(" ?")) {
				editText.setText("");
			}
			String editTextStr10 = editText.getText().toString();
			if (editTextStr10.equals("")) {
			} else {
				editTextStr10 = editTextStr10.substring(0,
						editTextStr10.length() - 1);
				editText.setText(editTextStr10);
			}
			break;
		case R.id.numHash_button:
			if (Question <= 10) {
				HowMuchTimeLeft();
				EndTimer();
				Enter();
			}
			break;
		case R.id.numMinus_button:
			if (editText.getText().toString().equals(" ?")) {
				editText.setText("");
			}
			String editTextStr12 = editText.getText().toString();
			editText.setText(editTextStr12 + "-");
			break;

		}
	}

	/*
	 * This class is used as a timer. It also returns how much time is left
	 * which is used to calulate the score.
	 */
	class Timer {

		final TextView TimeLeft = (TextView) findViewById(R.id.textView7);
		int Left = 0;
		final CountDownTimer timer = new CountDownTimer(10000, 1000) {

			public void onTick(long millisUntilFinished) {
				Left = (int) (millisUntilFinished / 1000);
				TimeLeft.setText("Seconds remaining: " + millisUntilFinished
						/ 1000);
			}

			public void onFinish() {
				TimeLeft.setText("Seconds remaining:  0");
				if (Question <= 10) {
					Enter();
				}

			}

		}.start();

		public void End() {

			timer.cancel();
			timer.equals(null);
		}

		public int HowMuchTimeLeft() {
			int TimeLeft = Left;
			return TimeLeft;

		}
	}
}
