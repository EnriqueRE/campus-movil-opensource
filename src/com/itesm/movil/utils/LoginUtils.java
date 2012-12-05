package com.itesm.movil.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.itesm.movil.models.Course;
import com.itesm.movil.models.CoursesManager;

import android.annotation.SuppressLint;
import android.util.Log;

public class LoginUtils {

	/**
	 * Receives any student ID and fills it with A or 0 to match web service.
	 * 
	 * @param studentID
	 *            The student's id to complete.
	 * @return filledID, the student ID filled with A and 0s
	 */
	public static String fillWithZeros(String studentID) {
		String filledID = "";
		String mAux = "";
		studentID = studentID.toUpperCase();
		Log.v("studentID", studentID);

		if (studentID.charAt(0) == 'A') {
			filledID = studentID;
		} else {

			mAux = "A";
			if (studentID.length() < 9) {
				for (int i = 0; i < 8 - studentID.length(); i++) {
					mAux += "0";
				}

				filledID = mAux + studentID;
			}

		}

		return filledID;
	}

	/**
	 * Attempts to connect to the web service in order to get the grades
	 * 
	 * @param studentID
	 *            The student's id.
	 * @param password
	 *            Password used to login to the Web Service
	 * @return <b>True</b>: if login was successful <br/>
	 *         <b>False:</b> if login unsuccessful
	 */
	public static boolean attemptToLogin(String studentID, String password) {

		try {

			String urlStr = "https://dl.dropbox.com/u/7668868/campus%20movil/sample.xml";

			URL url = new URL(urlStr);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));

			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();

			XMLHandler xmlhandler = new XMLHandler();
			xr.setContentHandler(xmlhandler);
			xr.parse(new InputSource(in));
			in.close();
			Log.e("Error", "" + XMLHandler.error);

			return !(XMLHandler.error);

		} catch (Exception e) {

		}
		return false;

	}
}
