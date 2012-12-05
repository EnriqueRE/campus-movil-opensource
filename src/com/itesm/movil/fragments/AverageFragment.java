package com.itesm.movil.fragments;

import java.util.ArrayList;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.itesm.movil.R;
import com.itesm.movil.models.Course;
import com.itesm.movil.models.CoursesManager;

public class AverageFragment extends SherlockFragment {

	View v;
	CoursesManager cm;
	ArrayList<Course> courses;
	String[] gradesP1;
	String[] gradesP2;
	String[] gradesP3;
	String[] gradesFnl;

	Handler mHandler;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.fragment_general_average, container,
				false);

		mHandler = new Handler();

		cm = CoursesManager.getInstance();
		
		SharedPreferences settings = getActivity().getSharedPreferences(
				"Settings", getActivity().MODE_PRIVATE);

		TextView txtP1 = (TextView) v.findViewById(R.id.lbl_p1);
		TextView txtP2 = (TextView) v.findViewById(R.id.lbl_p2);
		TextView txtP3 = (TextView) v.findViewById(R.id.lbl_p3);
		TextView txtFinal = (TextView) v.findViewById(R.id.lbl_final);

		txtP1.setText("P1: " + settings.getInt("AP1", 0));
		txtP2.setText("P2: " + settings.getInt("AP2", 0));
		if (cm.getNumberOfPartials() == 2) {
			txtP3.setText("P3: -");
		} else {
			txtP3.setText("P3: " + settings.getInt("AP3", 0));
		}

		txtFinal.setText("Final: " + settings.getInt("AFinal", 0));

		return v;
	}

}
