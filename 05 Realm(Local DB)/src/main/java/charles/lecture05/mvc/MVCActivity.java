package charles.lecture05.mvc;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import charles.lecture05.PhoneBook;
import charles.lecture05.R;
import io.realm.Realm;
import io.realm.RealmChangeListener;

public class MVCActivity extends AppCompatActivity {

    private ListView listView;
    private CustomAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc);

        //렘에다가 직접적으로 리스너를 달아버림
        Realm.getDefaultInstance().addChangeListener(changeListener);


        listView = (ListView) findViewById(R.id.listView);
        mAdapter = new CustomAdapter(this);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DBManager.getInstance().delete(mAdapter.getItemId(position));
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //리스너해제 메모리 누수 방지
//        Realm.getDefaultInstance().removeChangeListener(changeListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //xml로 만든 메뉴를 인플레이팅
        getMenuInflater().inflate(R.menu.mvc_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //메뉴의 이벤트를 받아오는부분
        switch (item.getItemId()) {
            //id 판별
            case R.id.menu_add:
                //input_add.xml(커스텀뷰)을 활용하기위한 인플레이팅 작업
                final View customView = LayoutInflater.from(this).inflate(R.layout.input_add, null);

                //빌더패턴으로 다이얼로그생성
                AlertDialog dialog = new AlertDialog.Builder(MVCActivity.this)
                        .setTitle("추가")
                        .setView(customView)
                        .setPositiveButton("저장", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //다이얼로그 내에 커스텀뷰를 부모로하는 에디트텍스트 객체 가져오는법
                                        EditText editName = (EditText) customView.findViewById(R.id.name);
                                        EditText editNumber = (EditText) customView.findViewById(R.id.number);


                                        //고유아이디 찾기? 생성
                                        long id = DBManager.getInstance().generateId();

                                        //폰북 객체 미리 생성
                                        PhoneBook pb = new PhoneBook();
                                        pb.setId(id);
                                        pb.setName(editName.getText().toString());
                                        pb.setNumber(Integer.parseInt(editNumber.getText().toString()));

                                        //미리 만들어진 객체를 MVC패턴에 의거해서
                                        //DBManager에게 위탁함
                                        DBManager.getInstance().add(pb);
                                        //데이터베이스에 저장은 되지만 저장이 완료되는 시점을 모름
                                        //그러므로 리스너가 필요함
                                    }
                                }

                        ).create();//create는 빌더로 만든 다이얼로그 객체만 생성
                ///show를 하여 다이얼로그 보여줌
                dialog.show();

                break;
            case R.id.menu_update:
                // TODO: 15. 11. 25. layout/input_update.xml과 AlertDialog를 활용하여 id,name,number가 수정되게끔 코드를 작성해보세요. update 메소드는 메니저에 미리 만들어놨어요.
                Toast.makeText(MVCActivity.this, "직접 구현해보세요^^", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public static final String TAG = "MVCActivity";

    //렘은 이너클래스중 익명클래스를 통한 약한 참조를 허용을 안함
    //리스너를 통하여 어댑터내의 items의 내용이 변환되었음을 알수 있음
    private RealmChangeListener changeListener = new RealmChangeListener() {
        @Override
        public void onChange() {
            //어댑터는 여전히 데이터(items)가 변경되었다는 사실을 모름
            mAdapter.notifyDataSetChanged();
            //그러므로 어댑터에게 노티를 알려서 리스트뷰가 갱신될수 있도록 도와줌
        }
    };
}
