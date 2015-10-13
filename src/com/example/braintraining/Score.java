package com.example.braintraining;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.rahimbaraky.braintraining.R;

public class Score extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.score);
		SetScore();
	}

	public void SetScore() {
		String Score = getIntent().getStringExtra("Score");
		System.out.println(Score);
		TextView SetTheScore = (TextView) findViewById(R.id.textView2);
		SetTheScore.setText(Score);
	}
}
