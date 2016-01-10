package com.tistory.charlezz.alarm;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

public class DateActivity extends AppCompatActivity {

    public static final String TAG = "DateActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //달력 객체 얻고
                final Calendar calendar = Calendar.getInstance();

                int currentYear = calendar.get(Calendar.YEAR);
                int currentMonth = calendar.get(Calendar.MONTH);
                int today = calendar.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dpd = new DatePickerDialog(DateActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Log.e(TAG, "year:" + year + " month:" + monthOfYear + " day:" + dayOfMonth);
                    }
                }, 1980, 10, 30);
//                dpd.show();

                Date date = new Date();
                //calendar->date->long
                Log.e(TAG, "currentTime1:" + calendar.getTime().getTime());
                Log.e(TAG, "currentTime2:" + date.getTime());


                TimePickerDialog tpd = new TimePickerDialog(DateActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar cal = Calendar.getInstance();
                        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        cal.set(Calendar.MINUTE, minute);

                        long currentMills = cal.getTime().getTime();
                        Log.e(TAG, "currentMills:" + currentMills);

                        //예) 시간을 정확하게 알때,  트리거시간 = 설정시간
                        //예) 일정시간 후에 작동시킬때 트리거시간 = 현재시간+일정시간

                    }
                }, 0, 0, true);
                tpd.show();
            }
        });
    }

}
