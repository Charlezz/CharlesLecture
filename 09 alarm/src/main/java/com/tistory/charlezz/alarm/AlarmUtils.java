package com.tistory.charlezz.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Charles on 16. 1. 10..
 */
public class AlarmUtils {
    private static AlarmUtils ourInstance = new AlarmUtils();

    public static AlarmUtils getInstance() {
        return ourInstance;
    }

    private Context context;

    private AlarmUtils() {
        context = MyApp.getContext();
    }


    public void registerAlarm() {
        Toast.makeText(context, "5초뒤 알람이 등록됨", Toast.LENGTH_SHORT).show();
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        //알람타입 종류 4가지
//        AlarmManager.RTC_WAKEUP //현재시간 기준,
//        AlarmManager.RTC//현재시간 ,
//        AlarmManager.ELAPSED_REALTIME_WAKEUP //부팅후,
//        AlarmManager.ELAPSED_REALTIME //부팅후 부터 시간계산

        Intent intent = new Intent(context, ShowActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

//        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 1 * 1000, pi);
        //하루에 한번 매일
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 1 * 1000,AlarmManager.INTERVAL_DAY, pi);

    }
}
