package com.itesm.movil.adapters;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itesm.movil.R;
import com.itesm.movil.models.Course;
import com.itesm.movil.models.CoursesManager;

public class CourseAdapter extends ArrayAdapter<Course> {
	private final LayoutInflater mInflater;

	Context context;

	CoursesManager cm = CoursesManager.getInstance();

	private int[] books = { R.drawable.b1, R.drawable.b2, R.drawable.b3,
			R.drawable.b4, R.drawable.b5, R.drawable.b6, R.drawable.b7,
			R.drawable.b8, R.drawable.b9, R.drawable.b10, R.drawable.b11 };

	public CourseAdapter(Context context) {
		super(context, R.layout.list_row_courses);
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;

	}

	@TargetApi(11)
	public void setItems(ArrayList<Course> items) {
		clear();
		if (items != null) {
			// addAll was added in api level 11
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
				addAll(items);
			} else {
				for (Course entry : items) {
					add(entry);
				}
			}
		}

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v;

		if (convertView == null) {

			v = mInflater.inflate(R.layout.list_row_courses, null);

		} else {
			v = convertView;
		}

		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		v = inflater.inflate(R.layout.list_row_courses, null);

		Course course = getItem(position);

		// Referencing from XML File
		TextView lblCourseName = (TextView) v
				.findViewById(R.id.lbl_course_name);

		TextView lblP1 = (TextView) v.findViewById(R.id.lbl_p1);
		TextView lblP2 = (TextView) v.findViewById(R.id.lbl_p2);
		TextView lblP3 = (TextView) v.findViewById(R.id.lbl_p3);

		TextView lblF1 = (TextView) v.findViewById(R.id.lbl_f1);
		TextView lblF2 = (TextView) v.findViewById(R.id.lbl_f2);
		TextView lblF3 = (TextView) v.findViewById(R.id.lbl_f3);
		TextView lblF4 = (TextView) v.findViewById(R.id.lbl_f4);
		TextView lblFTotal = (TextView) v.findViewById(R.id.lbl_fTotal);

		TextView lblfinal = (TextView) v.findViewById(R.id.lbl_final);

		TextView lblAvgFnl = (TextView) v.findViewById(R.id.lbl_avgfn);

		ImageView imgBook = (ImageView) v.findViewById(R.id.imageView1);

		// Assigning data.
		
		//Grades
		
		lblCourseName.setText(course.getName());

		lblP1.setText(course.getP1());
		lblP2.setText(course.getP2());

		if (cm.getNumberOfPartials() == 2) {

			lblP3.setText("-");
			// checking for updated grade
			if (course.isNewData()) {
				String[] grades = { course.getP1(), course.getP2() };

				String currentPeriod = course.getCurrent(grades);

				if (currentPeriod.equals("P1")) {
					lblP1.setTypeface(null, Typeface.BOLD);
				}

				if (currentPeriod.equals("P2")) {
					lblP2.setTypeface(null, Typeface.BOLD);
				}
			}

		} else {

			lblP3.setText(course.getP3());
			// checking for updated grade
			if (course.isNewData()) {
				String[] grades = { course.getP1(), course.getP2() };

				String currentPeriod = course.getCurrent(grades);

				if (currentPeriod.equals("P1")) {
					lblP1.setTypeface(null, Typeface.BOLD);
				}

				if (currentPeriod.equals("P2")) {
					lblP2.setTypeface(null, Typeface.BOLD);
				}
				if (currentPeriod.equals("P3")) {
					lblP3.setTypeface(null, Typeface.BOLD);
				}
			}
		}
		
		//done with grades now absences.

		lblF1.setText(course.getF1());
		lblF2.setText(course.getF2());
		if (cm.getNumberOfPartials() == 2) {

			lblF3.setText(course.getF3());

			lblF4.setText("-");

			lblFTotal.setText(""
					+ Course.fTotal(course.getF1(), course.getF2(),
							course.getF3(), "0"));

		} else {

			lblF3.setText(course.getF3());
			lblF4.setText(course.getF4());
			lblFTotal.setText(""
					+ Course.fTotal(course.getF1(), course.getF2(),
							course.getF3(), course.getF4()));

		}
		imgBook.setImageResource(books[position]);

		if (Integer.parseInt(course.getFnl()) != 0) {

			lblAvgFnl.setText(context.getResources().getString(
					R.string.lbl_final));
			lblfinal.setText(course.getFnl());
			lblAvgFnl.setTypeface(null, Typeface.BOLD);
			
		} else {

			// Average/Final label.
			
			lblAvgFnl.setText(context.getResources()
					.getString(R.string.average));

			lblfinal.setText("" + Course.calculateAverage(course.gradesArray()));

		}

		return v;
	}
}
