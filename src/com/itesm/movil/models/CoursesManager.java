package com.itesm.movil.models;

import java.util.ArrayList;

import android.util.Log;

public class CoursesManager {

	private static CoursesManager singletonObject;

	private ArrayList<Course> courses;

	private int numberOfPartials;

	private boolean hasData;

	private CoursesManager() {

	}

	public static synchronized CoursesManager getInstance() {

		if (singletonObject == null) {
			singletonObject = new CoursesManager();
		}

		return singletonObject;

	}

	public Object clone() throws CloneNotSupportedException {

		throw new CloneNotSupportedException();
	}

	public static CoursesManager getSingletonObject() {
		return singletonObject;
	}

	public static void setSingletonObject(CoursesManager singletonObject) {
		CoursesManager.singletonObject = singletonObject;
	}

	public synchronized ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}

	public int getNumberOfPartials() {
		return numberOfPartials;
	}

	public void setNumberOfPartials(int numberOfPartials) {
		this.numberOfPartials = numberOfPartials;
	}

	public boolean isHasData() {
		return hasData;
	}

	public void setHasData(boolean hasData) {
		this.hasData = hasData;
	}

}
