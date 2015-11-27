package com.kangjungu.www.fourthapp.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Charles on 15. 11. 22..
 */
public class PropertyManager {

    public static final String TAG = "PropertyManager";

    private static PropertyManager ourInstance = new PropertyManager();

    public static PropertyManager getInstance() {
        return ourInstance;
    }


    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private PropertyManager() {
        Log.e(TAG, "생성됨");
        pref = MyApplication.getContext().getSharedPreferences("my_pref", Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    /**
     * 이름 저장
     */
    private static String MY_NAME = "my_name";

    public boolean setMyName(String name) {
        return editor.putString(MY_NAME, name).commit();
    }

    public String getMyName() {
        return pref.getString(MY_NAME, "홍길동");
    }


    /**
     * 나이 저장
     */
    private static String MY_AGE = "my_age";

    public boolean setMyAge(int age) {
        return editor.putInt(MY_AGE, age).commit();
    }

    public int getMyAge() {
        return pref.getInt(MY_AGE, -1);
    }

}
