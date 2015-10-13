package com.example.braintraining;

import android.os.Bundle;
import android.preference.*;
import android.content.*;
import com.rahimbaraky.braintraining.R;

public class Prefs extends PreferenceActivity {

	private static final String OPT_HINTS = "hints";
	private static final boolean OPT_HINTS_DEF = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
	}

	public static boolean getHints(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean(OPT_HINTS, OPT_HINTS_DEF);
	}

}
