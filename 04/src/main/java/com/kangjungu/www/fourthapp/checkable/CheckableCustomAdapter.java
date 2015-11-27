package com.kangjungu.www.fourthapp.checkable;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Charles on 15. 11. 22..
 */
public class CheckableCustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CheckableUser> items = new ArrayList<>();

    public void add(CheckableUser user) {
        items.add(user);
        notifyDataSetChanged();
    }

    public void remove(CheckableUser user) {
        items.remove(user);
        notifyDataSetChanged();
    }

    public void remove(long rrn) {
        //items를 순회하여서 주민등록번호가 일치하는 녀석만 삭제 하는 로직
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getRrn() == rrn) {
                remove(getItem(i));
                //찾았으면 지우고 리턴, for문 빠져나옴
                return;
            }
        }

    }

    public void update(int position, CheckableUser user) {
        items.set(position, user);
    }

    public void setItems(ArrayList<CheckableUser> items) {
        this.items = items;
    }


    public CheckableCustomAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public CheckableUser getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getRrn();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CheckableCustomView cv = null;

        if (convertView == null) {
            cv = new CheckableCustomView(context);
        } else {
            cv = (CheckableCustomView) convertView;
        }

        cv.setData(getItem(position));

        return cv;
    }
}
