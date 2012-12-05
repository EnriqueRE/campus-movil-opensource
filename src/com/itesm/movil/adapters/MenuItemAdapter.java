package com.itesm.movil.adapters;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itesm.movil.R;

public class MenuItemAdapter extends ArrayAdapter<String> {
	private final LayoutInflater mInflater;

	Context context;

	private int[] menuIcons = { R.drawable.ic_action_view_as_list,
			R.drawable.ic_action_settings, R.drawable.ic_action_exit };

	public MenuItemAdapter(Context context) {
		super(context, R.layout.list_row_courses);
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;
	}

	@TargetApi(11)
	public void setItems(ArrayList<String> items) {
		clear();
		if (items != null) {
			// addAll was added to the API in level 11 (Honeycomb)
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
				addAll(items);
			} else {
				for (String entry : items) {
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
		v = inflater.inflate(R.layout.list_row_main_menu, null);

		String menuItem = getItem(position);

		// Referencing from XML File
		TextView txtItem = (TextView) v.findViewById(R.id.txtMenuItemText);
		ImageView menuIcon = (ImageView)v.findViewById(R.id.imageView1);

		// Setting content to views
		txtItem.setText(menuItem);
		menuIcon.setImageResource(menuIcons[position]);

		return v;
	}
}
