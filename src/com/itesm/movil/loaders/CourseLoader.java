package com.itesm.movil.loaders;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.TrafficStats;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.net.TrafficStatsCompat;
import android.util.Log;

import com.itesm.movil.models.Course;
import com.itesm.movil.models.CoursesManager;
import com.itesm.movil.utils.XMLHandler;

public class CourseLoader extends AsyncTaskLoader<ArrayList<Course>> {

	Context context;

	public CourseLoader(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	public ArrayList<Course> loadInBackground() {

		ArrayList<Course> courses = new ArrayList<Course>();

		// Getting user's data from the shared preferences
		SharedPreferences settings = context.getSharedPreferences("Settings",
				context.MODE_PRIVATE);

		String username = settings.getString("username", "");
		String password = settings.getString("password", "");
		String registrationId = settings.getString("regID", "");

		String urlStr = "https://dl.dropbox.com/u/7668868/campus%20movil/sample.xml";

		CoursesManager cm = CoursesManager.getInstance();

		// Checking if the singleton object has courses in it.
		if (cm.getCourses() == null) {
			// Log.v("url", urlStr);

			try {

				// Logging data for network traffic

				TrafficStatsCompat.setThreadStatsTag(0xF00D);

				URL url = new URL(urlStr);
				BufferedReader in = new BufferedReader(new InputStreamReader(
						url.openStream()));

				SAXParserFactory spf = SAXParserFactory.newInstance();
				SAXParser sp = spf.newSAXParser();
				XMLReader xr = sp.getXMLReader();

				XMLHandler xmlhandler = new XMLHandler();
				xr.setContentHandler(xmlhandler);
				InputSource source = new InputSource(in);
				xr.parse(source);

				in.close();
				courses = cm.getCourses();
			} catch (Exception e) {

				e.printStackTrace();
			} finally {

				TrafficStatsCompat.clearThreadStatsTag();

			}
		} else {
			courses = cm.getCourses();
		}

		String[] gradesP1 = new String[cm.getCourses().size()];
		String[] gradesP2 = new String[cm.getCourses().size()];
		String[] gradesP3 = new String[cm.getCourses().size()];
		String[] gradesFnl = new String[cm.getCourses().size()];

		for (int i = 0; i < courses.size(); i++) {
			
			gradesP1[i] = cm.getCourses().get(i).getP1();
			gradesP2[i] = cm.getCourses().get(i).getP2();
			gradesP3[i] = cm.getCourses().get(i).getP3();
			gradesFnl[i] = cm.getCourses().get(i).getFnl();

		}

		SharedPreferences.Editor editor = settings.edit();
		editor.putInt("AP1", Course.calculateAverage(gradesP1));
		editor.putInt("AP2", Course.calculateAverage(gradesP2));
		editor.putInt("AP3", Course.calculateAverage(gradesP3));
		editor.putInt("AFinal", Course.calculateAverage(gradesFnl));
		
		editor.commit();
		
		return courses;
	}
}