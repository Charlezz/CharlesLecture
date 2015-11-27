package charles.lecture05.mvc;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import charles.lecture05.PhoneBook;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by kangjungu on 2015. 11. 15..
 */
public class CustomAdapter extends BaseAdapter {

    public static final String TAG = "CustomAdapter";

    //DB데이터와 RealmResults(items) 동기화가 자동으로 됨
    //하지만 어댑터는 items의 내용이 바뀐것을 알아차리지 못함
    //items(DB)가 바뀐시점을 알기위해서는 RealmResults 객체에 addChangeListener를 달아줘야함
    //달아준후에는 꼭 remove리스너를 하여서 메모리 누수를 막아야함.

    private RealmResults<PhoneBook> items;

    private Context context;

    public CustomAdapter(Context context) {
        this.context = context;
        items = Realm.getDefaultInstance().allObjects(PhoneBook.class);
    }

    public String getName(int position) {
        return items.get(position).getName();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public PhoneBook getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomView v = null;
        if (convertView == null) {
            v = new CustomView(context);
        } else {
            v = (CustomView) convertView;
        }
        v.setMyData(getItem(position));
        return v;
    }

    public RealmResults<PhoneBook> getResult() {
        return items;
    }

}
