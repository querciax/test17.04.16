package com.example.oak.test170416;

/**
 * Created by Oak on 17/4/2559.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.CalendarView;

import java.util.Calendar;

/**
 * Created by octoboy on 22/11/2557.
 */
public class AlarmReceiver extends BroadcastReceiver {

    //EXPLICIT
    private CalendarView calendarView;

    @Override
    public void onReceive(Context context, Intent arg1) {
        Intent i = new Intent(context, ShowEvent.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    } //Main Method



}//Main Class