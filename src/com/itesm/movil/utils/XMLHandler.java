package com.itesm.movil.utils;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.content.Context;
import android.util.Log;

import com.itesm.movil.models.Course;
import com.itesm.movil.models.CoursesManager;
import com.itesm.movil.models.Professor;

public class XMLHandler extends DefaultHandler {

	Course course;
	Course nextCourse;
	static ArrayList<Course> courses = new ArrayList<Course>();
	public static boolean error;
	ArrayList<Professor> professors;
	Professor professor;

	CoursesManager cm = CoursesManager.getInstance();

	private String currentElementValue;

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		currentElementValue += new String(ch, start, length);
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (localName.equalsIgnoreCase("parciales")) {
			cm.setNumberOfPartials(Integer.parseInt(currentElementValue));
		}

		if (localName.equalsIgnoreCase("code")) {
			course.setCode(currentElementValue);
		}

		if (localName.equalsIgnoreCase("group")) {
			course.setGroup(currentElementValue);
		}

		if (localName.equalsIgnoreCase("name")) {
			course.setName(currentElementValue);
		}
		if (localName.equalsIgnoreCase("p1")) {
			course.setP1(currentElementValue);
		}
		if (localName.equalsIgnoreCase("p2")) {
			course.setP2(currentElementValue);
		}
		if (localName.equalsIgnoreCase("p3")) {
			course.setP3(currentElementValue);
		}
		if (localName.equalsIgnoreCase("final")) {
			course.setFnl(currentElementValue);
		}
		if (localName.equalsIgnoreCase("f1")) {
			course.setF1(currentElementValue);
		}
		if (localName.equalsIgnoreCase("f2")) {
			course.setF2(currentElementValue);
		}
		if (localName.equalsIgnoreCase("f3")) {
			course.setF3(currentElementValue);
		}
		if (localName.equalsIgnoreCase("f4")) {
			course.setF4(currentElementValue);
		}
		if (localName.equals("firstname")) {
			professors = new ArrayList<Professor>();
			professor = new Professor();
			professor.setFirstName(currentElementValue);
		}
		if (localName.equals("lastname")) {
			professor.setLastName(currentElementValue);
		}
		if (localName.equals("middlename")) {
			professor.setMiddleName(currentElementValue);
			professors.add(professor);
		}

		if (localName.equalsIgnoreCase("course")) {

			Course temp;
			boolean repetido = false;

			if (courses.size() == 0) {
				courses.add(course);
			}

			for (int i = 0; i < courses.size(); i++) {
				temp = courses.get(i);
				if (temp.getCode().equals(course.getCode())) {
					repetido = true;

				}
			}

			if (repetido == false) {

				courses.add(course);

			}

			cm.setCourses(courses);

		}
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (localName.equalsIgnoreCase("course")) {
			course = new Course();
			error = false;
		}

		if (localName.equalsIgnoreCase("ERROR")) {
			error = true;
		}

		currentElementValue = "";
	}

}
