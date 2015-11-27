package com.kangjungu.www.thirdapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class Main2Activity extends AppCompatActivity {

    private android.widget.ListView listView;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);
        this.listView = (ListView) findViewById(R.id.listView);


        CustomAdapter adapter = new CustomAdapter(this);
        listView.setAdapter(adapter);
        ArrayList<CustomData> items = new ArrayList<>();


        for (int num1 = 0; num1 < 100; num1++) {
            CustomData a = new CustomData();


            if (num1 % 2 == 0) {
                a.setViewType(CustomAdapter.VIEW_TYPE_A);
            } else {
                a.setViewType(CustomAdapter.VIEW_TYPE_B);
            }

            a.setName("홍길동");
            Random r = new Random();
            int num = r.nextInt(2);
            a.setProfile_path(num + "");
            items.add(a);
        }

        adapter.setItems(items);


        toast = Toast.makeText(Main2Activity.this, "", Toast.LENGTH_SHORT);

        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toast.setText("클릭:" + position);
                toast.show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //AlertDialog는 프로그래밍 디자인패턴중 빌더 패턴으로 설계됨

                //1번 title and message
//                AlertDialog dialog1 = new AlertDialog.Builder(Main2Activity.this).setTitle("제목").setMessage("내용내용내용내용내용내용내용내용내용")
//                        .create();
//                dialog1.show();

//                new AlertDialog.Builder(Main2Activity.this).setTitle("제목").setMessage("내용내용내용내용내용내용내용내용내용").show();

                //2번 아이템 선택 모드
                //String == CharSequence
//                final String[] items = {"아이템1", "아이템2", "아이템3"};
//                new AlertDialog.Builder(Main2Activity.this).setTitle("Title").setCancelable(false).setItems(items, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int position) {
//                        Toast.makeText(Main2Activity.this, "선택됨:" + items[position], Toast.LENGTH_SHORT).show();
////                        dialog.dismiss();
//                    }
//                }).show();
//
                //확인 취소 모드
                new AlertDialog.Builder(Main2Activity.this).setTitle("제목").setMessage("메시지")
                        .setNeutralButton("cancel1", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("OK1", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("NONO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                toast.setText("롱클릭:" + position);
                toast.show();
                return true;
            }
        });
    }
}
