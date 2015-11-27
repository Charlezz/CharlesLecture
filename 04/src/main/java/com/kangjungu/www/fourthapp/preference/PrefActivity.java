package com.kangjungu.www.fourthapp.preference;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kangjungu.www.fourthapp.R;

public class PrefActivity extends AppCompatActivity {



    private EditText name, age;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pref);

        save = (Button) findViewById(R.id.save);
        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strName = name.getText().toString();
                int intAge = Integer.parseInt(age.getText().toString());
                PropertyManager.getInstance().setMyName(strName);
                PropertyManager.getInstance().setMyAge(intAge);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        name.setText(PropertyManager.getInstance().getMyName());
        age.setText(PropertyManager.getInstance().getMyAge()+"");
    }

    //SharedPreference
    //주로 각종 셋팅값같은것 저장하는데 쓰이고 100개미만정도의 정보를 저장하는것이 좋음
    //자료가 적을때는 DB보다 데이터 입출력이 용이하고
    //데이터가 많으면 DB보다 속도가 상대적으로 느리기때문에
    //각종 세팅값정도만 저장한다 (예:소리,진동,아이디,자동로그인 등)
}
