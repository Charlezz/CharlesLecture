package com.kangjungu.www.thirdapp;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class IUActivity extends AppCompatActivity {

    private android.widget.ImageView image;
    private android.widget.Button iu;
    private android.widget.Button yun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.yun = (Button) findViewById(R.id.yun);
        this.iu = (Button) findViewById(R.id.iu);
        this.image = (ImageView) findViewById(R.id.image);

        image.setImageResource(R.drawable.yun);

        iu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //파일명으로 리소스 아이디 얻는법
                int resID = getResources().getIdentifier("iu", "drawable", getPackageName());
                Drawable drawable = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                    //resID to Drawable
//                    drawable = getResources().getDrawable(resID, getTheme());
                    image.setImageResource(resID);
                } else {
//                    drawable = getResources().getDrawable(resID);
                    image.setImageResource(resID);
                }
            }
        });

        yun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageResource(getResources().getIdentifier("yun", "drawable", getPackageName()));
            }
        });
    }
}
