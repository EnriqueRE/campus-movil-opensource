package com.itesm.movil;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.android.c2dm.C2DMBaseReceiver;
import com.itesm.movil.R;

public class C2DMReceiver extends C2DMBaseReceiver {

	int count, i;

	public C2DMReceiver() {
		super("this.is.not@real.biz");
	}

	@Override
	public void onRegistered(Context context, String registrationId) {
		//Log.w("C2DMReceiver-onRegistered", registrationId);

		SharedPreferences settings = getSharedPreferences("Settings",
				MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();

		editor.putString("regID", registrationId);
		editor.commit();

	}

	@Override
	public void onUnregistered(Context context) {
		//Log.w("C2DMReceiver-onUnregistered", "got here!");

	}

	@Override
	public void onError(Context context, String errorId) {
		//Log.w("C2DMReceiver-onError", errorId);
	}

	@Override
	protected void onMessage(Context context, Intent intent) {
		//Log.w("C2DMReceiver", intent.getStringExtra("payload"));
		final String payload = intent.getStringExtra("payload");
		createNotification(context, payload);
		
		if (count == 1) {
			count++;
		} else {
			i++;

		}

	}

	public void createNotification(Context context, String payload) {

		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = new Notification(R.drawable.status_icon,
				"Tec Movil", System.currentTimeMillis());

		notification.defaults |= Notification.DEFAULT_SOUND;
		notification.defaults |= Notification.DEFAULT_VIBRATE;

		// Hide the notification after its selected
		notification.flags |= Notification.FLAG_AUTO_CANCEL;

		Intent intent = new Intent(context, MainActivity.class);
		intent.putExtra("payload", payload);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);
		notification
				.setLatestEventInfo(context,
						"Se han actualizado tus calificaciones", payload,
						pendingIntent);

		notification.number += i;

		notificationManager.notify(0, notification);

	}

}
