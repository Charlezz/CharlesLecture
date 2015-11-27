package com.kangjungu.www.fourthapp.checkable;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kangjungu.www.fourthapp.R;

/**
 * Created by Charles on 15. 11. 22..
 */

//체커블을 구현하기 위해서  implement시킴
public class CheckableCustomView extends FrameLayout implements Checkable {

    private TextView name;
    private TextView age;
    private LinearLayout layout;

    public CheckableCustomView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.custom_view, this);
        name = (TextView) findViewById(R.id.name);
        age = (TextView) findViewById(R.id.age);
        layout = (LinearLayout) findViewById(R.id.layout);

    }

    public void setData(CheckableUser user) {
        name.setText(user.getName());
        age.setText("나이:" + user.getAge());
    }


    private boolean isChecked;

    @Override
    public void setChecked(boolean checked) {
        isChecked = checked;
        if (isChecked) {
            layout.setBackgroundResource(android.R.color.holo_purple);
        } else {
            layout.setBackgroundResource(android.R.color.transparent);
        }
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {
        isChecked = !isChecked;
//        setChecked(isChecked);
    }
}
