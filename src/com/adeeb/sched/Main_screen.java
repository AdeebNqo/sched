package com.adeeb.sched;

import java.util.Calendar;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TimePicker;

public class Main_screen extends Activity {

	Button done_button;
	EditText entered_text;
	RadioGroup type;
	TimePicker time_picker;
	AlarmManager alarm_manager;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		done_button = (Button)findViewById(R.id.done);
		done_button.setOnClickListener( new OnClickListener(){
			
			public void onClick(View arg0) {
				schedule();
			}}
		);
		
		entered_text = (EditText)findViewById(R.id.event_name_textbox);
		type = (RadioGroup)findViewById(R.id.radiogroup_buttons);
		time_picker = (TimePicker)findViewById(R.id.timePicker1);
		
		alarm_manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
	}
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_screen, menu);
		return true;
	}
	/*
	 * Method for scheduling a given task
	 * 
	 */
	public void schedule(){
		//getting details of event
		String event_text = entered_text.getText().toString();
		String event_type;
		switch(type.getCheckedRadioButtonId()){
		case R.id.tutorial:
			event_type="tutorial";
			break;
		case R.id.lecture:
			event_type="lecture";
			break;
		default:
			event_type="other";
			break;
		}
		
		//sorting out the time issues
		Calendar cal = Calendar.getInstance();
		long current_time = cal.getTimeInMillis();
		
		cal.set(Calendar.HOUR, time_picker.getCurrentHour());
		cal.set(Calendar.MINUTE, time_picker.getCurrentMinute());
		long added_time = cal.getTimeInMillis();
		
		//setting the alarm
		Intent i = new Intent(getBaseContext(), Sched.class);
		//putting the event alarm details in the intent
		i.putExtra("title", event_text);
		i.putExtra("type", event_type);
		
		PendingIntent pi = PendingIntent.getBroadcast(getBaseContext(), 0, i, PendingIntent.FLAG_ONE_SHOT);
		alarm_manager.setRepeating(AlarmManager.ELAPSED_REALTIME, added_time-current_time, AlarmManager.INTERVAL_DAY, pi);
	}
}