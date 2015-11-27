package charles.second;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SimpleListViewActivity extends AppCompatActivity {

    public static final String TAG = "SimpleListView";
    private ListView listView;

    private ArrayList<String> items = new ArrayList<>();
    private ArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        this.listView = (ListView) findViewById(R.id.listView);
        //아이템들 초기화
        initArray();
        //어댑터 생성 및 뷰와 연결
        mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);
        //리스트뷰와 어댑터연결
        listView.setAdapter(mAdapter);
    }

    private void initArray() {
        for (int i = 0; i < 100000; i++) {
            items.add("아이템 : " + i);
        }
    }
}
