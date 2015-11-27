package charles.second;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

public class CustomListActivity extends AppCompatActivity {

    public static final String TAG = "CustomListActivity";

    private ListView listView;

    private CustomAdapter mAdapter;

    private ArrayList<MyData> data = new ArrayList<>();
    private Button add;
    private Button remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);
        this.remove = (Button) findViewById(R.id.remove);
        this.add = (Button) findViewById(R.id.add);
        initData();
        Log.e(TAG, "size:" + data.size());
        this.listView = (ListView) findViewById(R.id.listView);
        mAdapter = new CustomAdapter(this);
        listView.setAdapter(mAdapter);
        mAdapter.setItems(data);


        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.remove(0);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyData d = new MyData();
                d.setName("야호");
                d.setProfile_path("blue");
                mAdapter.add(d);
            }
        });

    }

    private void initData() {

        for (int i = 0; i < 100; i++) {
            MyData d = new MyData();
            d.setName("홍길동" + i);
            Random r = new Random();
            int num = r.nextInt(100000);
            if (num % 2 == 0) {
                d.setProfile_path("red");
            } else {
                d.setProfile_path("blue");
            }
            data.add(d);
        }
    }
}
