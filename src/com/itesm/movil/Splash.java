package com.itesm.movil;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;

public class Splash extends Activity {

	private String username;
	private String password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		new CheckForDataTask().execute();

	}

	private class CheckForDataTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			SharedPreferences settings = getSharedPreferences("Settings",
					MODE_PRIVATE);

			username = settings.getString("username", "");
			password = settings.getString("password", "");

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			Intent intent;

			if ((username.equals("")) && (password.equals(""))) {
				intent = new Intent(getBaseContext(), LoginActivity.class);
			} else {
				intent = new Intent(getBaseContext(), MainActivity.class);
			}
			startActivity(intent);
			super.onPostExecute(result);
		}
	}

}
