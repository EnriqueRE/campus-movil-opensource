package com.itesm.movil;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.widget.TextView;

import com.google.android.c2dm.C2DMessaging;

public class SettingsActivity extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);

		/* Register/Unregister from notifications */

		final CheckBoxPreference notify = (CheckBoxPreference) findPreference("notifications");
		Preference mail = findPreference("mail");
		Preference about = findPreference("about");

		// SharedPreferences prefs =
		// PreferenceManager.getDefaultSharedPreferences(this);

		notify.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			@Override
			public boolean onPreferenceClick(Preference preference) {
				// TODO Actual Register/Unregister with 3rd party server
				if (notify.isChecked() == false) {
					Log.e("Unregister", "Unregister");
					C2DMessaging.unregister(getApplicationContext());
				} else {
					Log.e("Register", "Register");
					C2DMessaging.register(getApplicationContext(),
							"android.calificacionesapp@gmail.com");
				}
				return false;
			}
		});
		/* Send email */
		mail.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			@Override
			public boolean onPreferenceClick(Preference preference) {

				sendEmail();
				return false;
			}
		});

		about.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			@Override
			public boolean onPreferenceClick(Preference preference) {
				// TODO Auto-generated method stub

				aboutDialog();

				return false;
			}
		});

	}

	public void sendEmail() {

		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		String aEmailList[] = { getResources()
				.getString(R.string.support_email) };

		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, aEmailList);
		emailIntent.setType("plain/text");
		startActivity(emailIntent);

	}

	public void aboutDialog() {

		SpannableString link = new SpannableString(
				"Copyright© 2012 Enrique Ramirez, Lu’s Mata. \n"
						+ "Esta aplicaci—n fue desarrollada por alumnos \n"
						+ "del ITESM Campus Chihuahua \nVisita:\nhttp://www.chi.itesm.mx");
		Linkify.addLinks(link, Linkify.WEB_URLS);

		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle(getResources().getString(R.string.app_name));
		alertDialog.setMessage(link);
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// here you can add functions
			}
		});
		alertDialog.setIcon(R.drawable.ic_launcher);

		alertDialog.show();

		// Make the textview clickable. Must be called after show()
		((TextView) alertDialog.findViewById(android.R.id.message))
				.setMovementMethod(LinkMovementMethod.getInstance());

	}

}