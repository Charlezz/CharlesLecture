package com.kangjungu.www.thirdapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by kangjungu on 2015. 11. 15..
 */
public class CustomAdapter extends BaseAdapter {

    public static final int VIEW_TYPE_A = 0;
    public static final int VIEW_TYPE_B = 1;
    private static final int VIEW_TYPE_COUNT = 2;

    private ArrayList<CustomData> items = new ArrayList<>();
    private Context context;

    public CustomAdapter(Context context) {
        this.context = context;
    }

    public void setItems(ArrayList<CustomData> data) {
        items = data;
        notifyDataSetChanged();
    }


    //멀티타입을 구현하기 위한 두가지 필수 메소드
    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        int type = getItem(position).getViewType();
        return type;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public CustomData getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = null;
        switch (getItemViewType(position)) {
            case VIEW_TYPE_A:
                if (convertView != null && convertView instanceof CustomView) {
                    v = convertView;
                } else {
                    v = new CustomView(context);
                }
                ((CustomView) v).setMyData(getItem(position));
                ((CustomView) v).setOnMyButtonClickListener(new CustomView.OnMyClickListener() {
                    @Override
                    public void onMyButtonClick(CustomData data) {
                        if(mListener!=null){
                            mListener.onMyButtonClicked(data);
                        }
                    }
                });
                break;
            case VIEW_TYPE_B:
                if (convertView != null && convertView instanceof CustomView2) {
                    v = convertView;
                } else {
                    v = new CustomView2(context);
                }
                ((CustomView2) v).setMyData(getItem(position));
                break;
        }
        return v;
    }

    public interface OnMyButtonClickedListener{
        void onMyButtonClicked(CustomData data);
    }

    private OnMyButtonClickedListener mListener;

    public void setOnMyButtonClickedListener(OnMyButtonClickedListener listener){
        mListener = listener;
    }


}
