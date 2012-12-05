package com.itesm.movil;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.actionbarsherlock.app.SherlockFragmentActivity;

public class DetailActivity extends SherlockFragmentActivity {

	@Override
	protected void onCreate(Bundle arg0) {

		super.onCreate(arg0);

		setContentView(R.layout.activity_detail);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onOptionsItemSelected(
			com.actionbarsherlock.view.MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
