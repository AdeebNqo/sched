package com.adeeb.sched;

import java.util.Timer;
import java.util.TimerTask;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.Toast;

public class Sched extends BroadcastReceiver{
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "Msg received", Toast.LENGTH_LONG).show();
		final AudioManager audio = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
		final int old_mode = audio.getRingerMode();
		//choosing the mode depending on the event type
		Bundle passed_info = intent.getExtras();
		String event_type = (String) passed_info.get("event_type");
		if (event_type.equalsIgnoreCase("lecture")){
			audio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
		}
		else if (event_type.equalsIgnoreCase("tutorial")){
			audio.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
		}
		else{
			audio.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
		}
		
		//setting ringer mode back to what it was
		Timer reseter = new Timer();
		reseter.schedule(new TimerTask(){
			public void run() {
				audio.setRingerMode(old_mode);
			}}, 2700000);
	}
}
