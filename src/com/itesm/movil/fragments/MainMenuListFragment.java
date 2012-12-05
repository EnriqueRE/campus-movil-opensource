package com.itesm.movil.fragments;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockListFragment;
import com.google.android.c2dm.C2DMessaging;
import com.itesm.movil.LoginActivity;
import com.itesm.movil.MainActivity;
import com.itesm.movil.R;
import com.itesm.movil.SettingsActivity;
import com.itesm.movil.adapters.MenuItemAdapter;

public class MainMenuListFragment extends SherlockListFragment {
	private static final String STATE_ACTIVATED_POSITION = "activated_position";

	private Callbacks mCallbacks = sDummyCallbacks;
	private int mActivatedPosition = ListView.INVALID_POSITION;
	private static int save = -1;

	MenuItemAdapter rAdapter;

	public interface Callbacks {

		public void onItemSelected(String id);
	}

	private static Callbacks sDummyCallbacks = new Callbacks() {
		@Override
		public void onItemSelected(String id) {
		}
	};

	public MainMenuListFragment() {
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		TextView txt = (TextView) getActivity()
				.findViewById(R.id.lbl_studentID);
		SharedPreferences settings = getActivity().getSharedPreferences("Settings",
				getActivity().MODE_PRIVATE);

		txt.setText(settings.getString("username", ""));
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		rAdapter = new MenuItemAdapter(getActivity());
		setListAdapter(rAdapter);

		ArrayList<String> menuItems = new ArrayList<String>();
		String[] tempArray = getResources().getStringArray(R.array.main_items);

		Collections.addAll(menuItems, tempArray);

		rAdapter.setItems(menuItems);

	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		getListView().setDivider(null);

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

		Intent intent = null;

		switch (position) {
		case 0:
			intent = new Intent(getActivity(), MainActivity.class);
			break;

		case 1:
			intent = new Intent(getActivity(), SettingsActivity.class);
			break;

		case 2:

			// Clearing User's Data

			SharedPreferences settings = getActivity().getSharedPreferences(
					"Settings", getActivity().MODE_PRIVATE);
			SharedPreferences.Editor editor = settings.edit();

			editor.putString("username", "");
			editor.putString("password", "");

			editor.clear();

			editor.commit();

			C2DMessaging.unregister(getActivity());

			intent = new Intent(getActivity(), LoginActivity.class);

			getActivity().finish();

		}

		startActivity(intent);

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

}
