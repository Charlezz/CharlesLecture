package charles.second;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Charles on 15. 11. 8..
 */
public class MyData implements Parcelable {

    public MyData() {
    }

    private String profile_path;
    private String name;

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.profile_path);
        dest.writeString(this.name);
    }
    protected MyData(Parcel in) {
        this.profile_path = in.readString();
        this.name = in.readString();
    }

    public static final Creator<MyData> CREATOR = new Creator<MyData>() {
        public MyData createFromParcel(Parcel source) {
            return new MyData(source);
        }

        public MyData[] newArray(int size) {
            return new MyData[size];
        }
    };

}
