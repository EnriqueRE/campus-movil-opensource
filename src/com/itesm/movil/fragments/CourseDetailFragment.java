package com.itesm.movil.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itesm.movil.R;
import com.itesm.movil.models.Course;
import com.itesm.movil.models.CoursesManager;

public class CourseDetailFragment extends Fragment {

	private int[] books = { R.drawable.b1, R.drawable.b2, R.drawable.b3,
			R.drawable.b4, R.drawable.b5, R.drawable.b6, R.drawable.b7,
			R.drawable.b8, R.drawable.b9, R.drawable.b10, R.drawable.b11 };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_course_detail, container,
				false);

		CoursesManager cm = CoursesManager.getInstance();

		Course course = new Course();

		Bundle extras = getActivity().getIntent().getExtras();
		int position = 0;
		if (extras != null) {
			position = extras.getInt("position");

			course = cm.getCourses().get(position);
		}

		//Getting references from the view.
		
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

		lblCourseName.setText(course.getName());

		//If the campus has 2 partials
		
		if (cm.getNumberOfPartials() == 2) {


			
			// Midterms
			lblP1.setText(course.getP1());
			lblP2.setText(course.getP2());
			lblP3.setText("-");

			// Absenses

			lblF1.setText(course.getF1());
			lblF2.setText(course.getF2());
			lblF3.setText(course.getF3());
			lblF4.setText("-");

			lblFTotal.setText(""
					+ Course.fTotal(course.getF1(), course.getF2(),
							course.getF3(), "0"));

			imgBook.setImageResource(books[position]);

			if (Integer.parseInt(course.getFnl()) != 0) {

				lblAvgFnl.setText(getActivity().getResources().getString(
						R.string.lbl_final));
				lblfinal.setText(course.getFnl());
			} else {

				lblAvgFnl.setText(getActivity().getResources().getString(
						R.string.average));

				String[] grades = {course.getP1(),course.getP2()};
				
				lblfinal.setText(""
						+ Course.calculateAverage(grades));

			}

		} else {

			// Normal 3 Partials State

			// Midterms
			lblP1.setText(course.getP1());
			lblP2.setText(course.getP2());
			lblP3.setText(course.getP3());

			// Absenses

			lblF1.setText(course.getF1());
			lblF2.setText(course.getF2());
			lblF3.setText(course.getF3());
			lblF4.setText(course.getF4());

			lblFTotal.setText(""
					+ Course.fTotal(course.getF1(), course.getF2(),
							course.getF3(), course.getF4()));

			imgBook.setImageResource(books[position]);

			if (Integer.parseInt(course.getFnl()) != 0) {

				lblAvgFnl.setText(getActivity().getResources().getString(
						R.string.lbl_final));
				lblfinal.setText(course.getFnl());

			} else {

				lblAvgFnl.setText(getActivity().getResources().getString(
						R.string.average));

				lblfinal.setText(""
						+ Course.calculateAverage(course.gradesArray()));

			}

		}

		return v;
	}
}
