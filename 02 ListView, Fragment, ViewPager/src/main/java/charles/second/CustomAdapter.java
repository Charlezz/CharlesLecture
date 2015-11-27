package charles.second;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Charles on 15. 11. 8..
 */
//커스텀 어댑터 만들때는 BaseAdapter를 상속
//상속하면 4가지 메소드를 꼭 구현해줘야함
public class CustomAdapter extends BaseAdapter {

    private ArrayList<MyData> items = new ArrayList<>();
    private Context context;

    public CustomAdapter(Context context) {
        this.context = context;
    }

    public void add(MyData d){
        items.add(0,d);

        notifyDataSetChanged();
    }
    public void remove(MyData d){
        items.remove(d);
        notifyDataSetChanged();
    }
    public void remove(int position){
        items.remove(position);
        notifyDataSetChanged();
    }

    public void setItems(ArrayList<MyData> data) {
        items = data;
        notifyDataSetChanged();// 데이터가 갱신되었음을 알림
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public MyData getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = null;

        if (convertView == null) {
            v = new CustomView(context);
        } else {
            v = convertView;
        }
        ((CustomView) v).setMyData(getItem(position));
        return v;
    }
}
