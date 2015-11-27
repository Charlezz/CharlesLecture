package charles.lecture05;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    private ArrayAdapter<String> mAdapter;

    private EditText name, number;
    private Button save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(mAdapter);


        name = (EditText) findViewById(R.id.name);
        number = (EditText) findViewById(R.id.number);

        save = (Button) findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strName = name.getText().toString();
                int intNumber = Integer.parseInt(number.getText().toString());

                //렘(DB)객체 얻기
                Realm realm = Realm.getInstance(MainActivity.this);

                //트랜잭션을 시작하겠다, 명시적으로 알림
                realm.beginTransaction();

                //객체 생성과 동시에 DB에 넣겠다는 뜻
                PhoneBook pb = realm.createObject(PhoneBook.class);
                pb.setName(strName);
                pb.setNumber(intNumber);

                //트랜잭션을 끝내겠다 알림.
                realm.commitTransaction();

            }
        });

        Realm realm = Realm.getInstance(MainActivity.this);

        //테이블 클래스의 모든 레코드 조회
        RealmResults<PhoneBook> results = realm.allObjects(PhoneBook.class);

        for(PhoneBook pb: results){
            String info = pb.getName()+" : "+pb.getNumber();
            mAdapter.add(info);
        }

    }
}
