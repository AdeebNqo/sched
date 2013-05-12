package com.adeeb.sched;

import android.app.PendingIntent;

public class Event {
	PendingIntent pi;
	String title;
	String type;
	String time; //format aa:bb
	public Event(PendingIntent pi, String title, String type, String time){
		this.pi = pi;
		this.title = title;
		this.type = type;
		this.time = time;
	}
}
