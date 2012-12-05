package com.itesm.movil.fragments;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockListFragment;
import com.itesm.movil.DetailActivity;
import com.itesm.movil.R;
import com.itesm.movil.adapters.CourseAdapter;
import com.itesm.movil.loaders.CourseLoader;
import com.itesm.movil.models.Course;
import com.itesm.movil.models.CoursesManager;

public class CourseListFragment extends SherlockListFragment implements
		LoaderCallbacks<ArrayList<Course>> {
	private static final String STATE_ACTIVATED_POSITION = "activated_position";

	private Callbacks mCallbacks = sDummyCallbacks;
	private int mActivatedPosition = ListView.INVALID_POSITION;

	public boolean isLoaded = false;

	CourseAdapter rAdapter;

	public interface Callbacks {

		public void onItemSelected(String id);
	}

	private static Callbacks sDummyCallbacks = new Callbacks() {
		@Override
		public void onItemSelected(String id) {
		}
	};

	public CourseListFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		rAdapter = new CourseAdapter(getActivity());
		setListAdapter(rAdapter);

		Loader<ArrayList<Course>> mLoader = getLoaderManager().initLoader(0,
				null, this);
		mLoader.forceLoad();

	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		getListView().setDivider(
				new ColorDrawable(getResources().getColor(
						R.color.activity_background)));
		getListView().setDividerHeight(30);

		getView().setVerticalFadingEdgeEnabled(true);

		isLoaded = true;

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (!(activity instanceof Callbacks)) {
			throw new IllegalStateException(
					"Activity must implement fragment's callbacks.");
		}

		mCallbacks = (Callbacks) activity;
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mCallbacks = sDummyCallbacks;
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position,
			long id) {
		super.onListItemClick(listView, view, position, id);

		TextView lblP1 = (TextView) view.findViewById(R.id.lbl_p1);
		TextView lblP2 = (TextView) view.findViewById(R.id.lbl_p2);
		TextView lblP3 = (TextView) view.findViewById(R.id.lbl_p3);

		lblP1.setTypeface(null, Typeface.NORMAL);
		lblP2.setTypeface(null, Typeface.NORMAL);
		lblP3.setTypeface(null, Typeface.NORMAL);

		// Intent intent = new Intent(getActivity(), DetailActivity.class);
		// intent.putExtra("position", position);
		// startActivity(intent);

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mActivatedPosition != AdapterView.INVALID_POSITION) {
			outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
		}
	}

	public void setActivateOnItemClick(boolean activateOnItemClick) {
		getListView().setChoiceMode(
				activateOnItemClick ? AbsListView.CHOICE_MODE_SINGLE
						: AbsListView.CHOICE_MODE_NONE);
	}

	public void setActivatedPosition(int position) {
		if (position == AdapterView.INVALID_POSITION) {
			getListView().setItemChecked(mActivatedPosition, false);
		} else {
			getListView().setItemChecked(position, true);

		}

		mActivatedPosition = position;
	}

	@Override
	public Loader<ArrayList<Course>> onCreateLoader(int arg0, Bundle arg1) {

		return new CourseLoader(getActivity());
	}

	@Override
	public void onLoadFinished(Loader<ArrayList<Course>> loader,
			ArrayList<Course> unis) {

		rAdapter.setItems(unis);

		// The list should now be shown.
		if (isResumed()) {
			setListShown(true);
		} else {
			setListShownNoAnimation(true);
		}

	}

	@Override
	public void onLoaderReset(Loader<ArrayList<Course>> arg0) {
		rAdapter.setItems(null);

	}

}
