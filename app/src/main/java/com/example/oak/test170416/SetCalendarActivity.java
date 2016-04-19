package com.example.oak.test170416;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class SetCalendarActivity extends AppCompatActivity {

    //EXPLICIT
    private CalendarView calendarView;
    private TimePickerDialog timePickerDialog;
    private int dayAnInt, monthAnInt, yearAnInt;
    private ListView listAlarm;
    public static ArrayList<String> listValue;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_calendar);

        listAlarm = (ListView)findViewById(R.id.listView);
        listValue = new ArrayList<String>();

        calendarView = (CalendarView) findViewById(R.id.calendarView);

        //Get Event for Click Calendar
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {

                Toast.makeText(SetCalendarActivity.this, day + "/" + (month + 1) + "/" + year, Toast.LENGTH_LONG).show();

                setMyAlarm(day, month, year);

            } // onSelected
        });

    }//Main method

    private void setMyAlarm(int day, int month, int year) {

        String tag = "18April";
        Calendar currentCalendar = Calendar.getInstance();

        dayAnInt = day;
        monthAnInt = month;
        yearAnInt = year;

        timePickerDialog = new TimePickerDialog(SetCalendarActivity.this,
                onTimeSetListener,
                currentCalendar.get(Calendar.HOUR_OF_DAY),
                currentCalendar.get(Calendar.MINUTE),
                false);

            timePickerDialog.setTitle("โปรดเลือกเวลา");
            timePickerDialog.show();



    } // setMyAlarm Method



    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            Calendar calendar = Calendar.getInstance();
            Calendar cloneCalendar1 = (Calendar) calendar.clone();

            cloneCalendar1.set(Calendar.DAY_OF_MONTH, dayAnInt);
            cloneCalendar1.set(Calendar.MONTH, monthAnInt);
            cloneCalendar1.set(Calendar.YEAR, yearAnInt);
            cloneCalendar1.set(Calendar.HOUR_OF_DAY, hourOfDay);
            cloneCalendar1.set(Calendar.MINUTE, minute);
            cloneCalendar1.set(Calendar.SECOND, 0);

            Log.d("18April", "cloneCalendar1 ==> " + cloneCalendar1.getTime());
            mySetToAlarm(cloneCalendar1);
        } // OnTimeSet
    };

    private void mySetToAlarm(Calendar mySetCalendar1) {

        listValue.add(mySetCalendar1.getTime()+"");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listValue);
        listAlarm.setAdapter(adapter);

        final int _id = (int) System.currentTimeMillis();

        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), _id, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, mySetCalendar1.getTimeInMillis(), pendingIntent);

    } // mySetToAlarm mothod

} // Main Class
