package com.itesm.movil;

import java.util.ArrayList;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.view.MenuItem;
import com.google.android.c2dm.C2DMessaging;
import com.itesm.movil.fragments.AverageFragment;
import com.itesm.movil.fragments.CourseListFragment;
import com.itesm.movil.fragments.MainMenuListFragment;
import com.itesm.movil.models.Course;
import com.itesm.movil.models.CoursesManager;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity implements
		CourseListFragment.Callbacks, MainMenuListFragment.Callbacks {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setBehindContentView(R.layout.main_menu);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		SlidingMenu sm = getSlidingMenu();
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setBehindOffsetRes(R.dimen.actionbar_home_width);

		// get first run of the app
		SharedPreferences settings = getSharedPreferences("Settings",
				MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();

		CoursesManager cm = CoursesManager.getInstance();

		ArrayList<Course> courses = cm.getCourses();

		if (((CampusMovilApplication) getApplication()).getFirstRun()) {

			C2DMessaging.register(getApplicationContext(),
					"android.calificacionesapp@gmail.com");
			((CampusMovilApplication) getApplication()).setRunned();

			for (int i = 0; i < courses.size(); i++) {

				Course c = courses.get(i);
				String p1 = c.getP1();
				String p2 = c.getP2();
				String p3 = c.getP3();

				String[] grades = { p1, p2, p3 };

				editor.putInt(c.getName(), c.lameChecksum(grades));
				// Log.w("Introducing", "" + c.lameChecksum(grades));
				editor.commit();
			}

		} else {

			// check for changes on grades

			if (courses != null) {
				for (int i = 0; i < courses.size(); i++) {

					Course c = courses.get(i);
					String p1 = c.getP1();
					String p2 = c.getP2();
					String p3 = c.getP3();
					String[] grades = { p1, p2, p3 };

					int originalSum = settings.getInt(c.getName(), 0);
					int actualSum = c.lameChecksum(grades);

					if (originalSum != actualSum) {
						c.setNewData(true);
						editor.putInt(c.getName(), actualSum);
						editor.commit();
					}

				}
			}

		}

	}

	@Override
	public void onItemSelected(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			toggle();
			return true;
		}
		return true;
	}

}
