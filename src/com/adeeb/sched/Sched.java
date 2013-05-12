package com.adeeb.sched;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.widget.Toast;

public class Sched extends BroadcastReceiver{
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "Msg received", Toast.LENGTH_LONG).show();
		// TODO Auto-generated method stub
		AudioManager audio = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
		audio.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
	}
}
