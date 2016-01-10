package com.tistory.charlezz.alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;

import java.io.File;

public class ShowActivity extends AppCompatActivity {

    private Vibrator vi;
    private NotificationManager nm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        //진동
        vi = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long pattern[] = {1000, 1000};
//        vi.vibrate(pattern, 0);

        //노티피케이션
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("제목")
                .setContentText("내용")
                .setAutoCancel(true)
                .setContentIntent(PendingIntent.getActivity(this, 0, new Intent(), 0))
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setTicker("알람이 울렸습니다");

        Notification notification = builder.build();

        nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(0, notification);

        //미디어 플레이어

        Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/download/sample.mp3"));
        MediaPlayer mp = MediaPlayer.create(this, uri);
        mp.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        vi.cancel();
        nm.cancel(0);
    }
}
