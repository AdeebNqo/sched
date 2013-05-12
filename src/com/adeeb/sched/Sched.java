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
		
		final AudioManager audio = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
		final int old_mode = audio.getRingerMode();
		//choosing the mode depending on the event type
		Bundle passed_info = intent.getExtras();
		String event_type = (String) passed_info.get("type");
		String event_name = (String) passed_info.get("title");
		Toast.makeText(context, "Changing ringer mode: "+event_name, Toast.LENGTH_LONG).show();
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
