package com.kangjungu.www.thirdapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kangjungu on 2015. 11. 15..
 */
public class CustomData implements Parcelable {

    public CustomData() {
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String profile_path;
    private String name;

    //멀티타입 리스트에서 뷰타입 정의
    private int viewType;

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.profile_path);
        dest.writeString(this.name);
        dest.writeInt(viewType);
    }

    protected CustomData(Parcel in) {
        this.profile_path = in.readString();
        this.name = in.readString();
        viewType = in.readInt();
    }

    public static final Parcelable.Creator<CustomData> CREATOR = new Parcelable.Creator<CustomData>() {
        public CustomData createFromParcel(Parcel source) {
            return new CustomData(source);
        }

        public CustomData[] newArray(int size) {
            return new CustomData[size];
        }
    };
}
