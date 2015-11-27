package com.kangjungu.www.fourthapp.checkable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Charles on 15. 11. 22..
 */
public class CheckableUser implements Parcelable {
    private long rrn;
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getRrn() {
        return rrn;
    }

    public void setRrn(long rrn) {
        this.rrn = rrn;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.rrn);
        dest.writeString(this.name);
        dest.writeInt(this.age);
    }

    public CheckableUser() {
    }

    protected CheckableUser(Parcel in) {
        this.rrn = in.readLong();
        this.name = in.readString();
        this.age = in.readInt();
    }

    public static final Parcelable.Creator<CheckableUser> CREATOR = new Parcelable.Creator<CheckableUser>() {
        public CheckableUser createFromParcel(Parcel source) {
            return new CheckableUser(source);
        }

        public CheckableUser[] newArray(int size) {
            return new CheckableUser[size];
        }
    };
}
