package com.kangjungu.www.thirdapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by kangjungu on 2015. 11. 15..
 */
public class CustomView extends FrameLayout {
    public static final String TAG = "CustomView";

    private Context mContext;
    private FrameLayout frameLayout;

    public CustomView(Context context) {
        super(context);
        mContext = context;
        View v = LayoutInflater.from(context).inflate(R.layout.itme_view, this);
        imageView = (ImageView) v.findViewById(R.id.chat_image);
        textView_title = (TextView) v.findViewById(R.id.chat_title);
        textView_body = (TextView) v.findViewById(R.id.chat_body);

        frameLayout = (FrameLayout) v.findViewById(R.id.frameLayout);
    }

    private ImageView imageView;
    private TextView textView_title;
    private TextView textView_body;

    private CustomData data;

    public void setMyData(CustomData data) {
        this.data = data;

        String color = data.getProfile_path();

        if (color.equals("1"))
            imageView.setImageResource(R.drawable.iu);
        else
            imageView.setImageResource(R.drawable.yun);

        textView_title.setText(data.getName());

        //시간은 Date()
        //내부적으로는 long타입으로 관리
        //하지만 텍스트로 보여주려면 date -> String
        //이걸 도와주는 녀석이 SimpleDateFormat

//
//        yyyy-MM-dd 1969-12-31
//        yyyy-MM-dd 1970-01-01
//        yyyy-MM-dd HH:mm 1969-12-31 16:00
//        yyyy-MM-dd HH:mm 1970-01-01 00:00
//        yyyy-MM-dd HH:mmZ 1969-12-31 16:00-0800
//        yyyy-MM-dd HH:mmZ 1970-01-01 00:00+0000
//        yyyy-MM-dd HH:mm:ss.SSSZ 1969-12-31 16:00:00.000-0800
//        yyyy-MM-dd HH:mm:ss.SSSZ 1970-01-01 00:00:00.000+0000
//        yyyy-MM-dd'T'HH:mm:ss.SSSZ 1969-12-31T16:00:00.000-0800
//        yyyy-MM-dd'T'HH:mm:ss.SSSZ 1970-01-01T00:00:00.000+0000

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);


//        textView_body.setText("long:"+date.getTime());
        textView_body.setText("date:" + sdf.format(date));


        frameLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onMyButtonClick(CustomView.this.data);
                }
            }
        });
    }

    public interface OnMyClickListener {
        void onMyButtonClick(CustomData data);
    }

    private OnMyClickListener mListener;

    public void setOnMyButtonClickListener(OnMyClickListener listener) {
        mListener = listener;
    }

}
