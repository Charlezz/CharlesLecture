package com.kangjungu.www.fourthapp.checkable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.kangjungu.www.fourthapp.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CheckableActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    private ListView listView;

    private CheckableCustomAdapter mAdapter;

    private Button add, single, cancel, singleCheck;
    private Button multiple, multipleCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);

        mAdapter = new CheckableCustomAdapter(this);
        listView.setAdapter(mAdapter);
        add = (Button) findViewById(R.id.add);
        single = (Button) findViewById(R.id.single);
        cancel = (Button) findViewById(R.id.cancel);
        singleCheck = (Button) findViewById(R.id.singlecheck);
        multiple = (Button) findViewById(R.id.multiple);
        multipleCheck = (Button) findViewById(R.id.multipleCheck);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(listView.getChoiceMode()!=ListView.CHOICE_MODE_NONE){
                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckableUser user = new CheckableUser();
                long rrn = new Date().getTime();
                user.setName("홍길동" + rrn);
                user.setAge(30);
                user.setRrn(rrn);
                mAdapter.add(user);
            }
        });

        single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //선택 모드 싱글로 변경
                listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //선택한 아이템들 전부 false로 초기화
                listView.clearChoices();
                //선택 모드 NONE= 모드 취소= 일반상태로 돌림
                listView.setChoiceMode(ListView.CHOICE_MODE_NONE);
            }
        });

        singleCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 *싱글모드일때의 로직
                 * 가장 마지막에 선택한 아이템의 포지션을 가져옴 */
                int position = listView.getCheckedItemPosition();
                Log.e(TAG, mAdapter.getItem(position).getName());


            }
        });

        multiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            }
        });

        multipleCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * 클릭한 아이템들의 모든 목록을 가져옴,
                 * 리스트뷰의 아이템 갯수와 전혀다른 내용
                 * 클릭한 아이템만 가져옴
                 */

                List<Long> rrns = new ArrayList<>();
                SparseBooleanArray array = listView.getCheckedItemPositions();
                for (int i = 0; i < array.size(); i++) {
                    //클릭한(체크한것 또는 체크했다가 해제한것 포함) 아이템의 포지션을 가져옴
                    int position = array.keyAt(i);
                    if (array.get(position)) {
                        Log.e(TAG, mAdapter.getItem(position).getName());
                        rrns.add(mAdapter.getItemId(position));
                    }
//                    Log.e(TAG, "position[" + i + "]:" + position + "=" + array.get(position));
//                    Log.e(TAG, mAdapter.getItem(position).getName());
                }

                for (int i = 0; i < rrns.size(); i++) {
                    long rrn = rrns.get(i);
                    mAdapter.remove(rrn);
                }

            }
        });


    }
}
