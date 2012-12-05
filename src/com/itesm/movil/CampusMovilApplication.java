package com.itesm.movil;

import com.itesm.movil.models.CoursesManager;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class CampusMovilApplication extends Application {

	SharedPreferences settings;

	@Override
	public void onCreate() {
		super.onCreate();

		Context mContext = this.getApplicationContext();
		settings = mContext.getSharedPreferences("Settings", MODE_PRIVATE);
		
	}

	public boolean getFirstRun() {

		return settings.getBoolean("firstRun", true);

	}

	public void setRunned() {

		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean("firstRun", false);
		editor.commit();

	}

}
