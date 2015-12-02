package charles.fragment2.example2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import charles.fragment2.R;
import charles.fragment2.example1.Fragment2;

public class ExampleActivity2 extends AppCompatActivity implements Fragment3.OnButtonClicked{

    public static final String TAG = "ExampleActivity1";

    private Fragment3 fragment3;//프레그먼트 A
    private Fragment2 fragment2;//프레그먼트 B

    private android.support.v4.app.FragmentManager fm;
    private RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        //라디오그룹
        rg = (RadioGroup) findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb1:
                        //프레그먼트1은 보여주고 2는 가리고
                        fm.beginTransaction().hide(fragment2).commit();
                        fm.beginTransaction().show(fragment3).commit();
                        break;
                    case R.id.rb2:
                        fm.beginTransaction().hide(fragment3).commit();
                        fm.beginTransaction().show(fragment2).commit();
                        break;
                }
            }
        });


        //프레그먼트 매니저 얻기
        fm = getSupportFragmentManager();

        //프레그먼트 생성
        fragment3 = new Fragment3();
        fragment2 = new Fragment2();

        //이 클래스와 리스너 연결
        fragment3.setOnButtonClickedListener(this);

        //백스택에 프레그먼트 2개를 미리 넣어둠,
        fm.beginTransaction().add(R.id.fragment_container, fragment3).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragment2).commit();
        //처음엔 안보이게 하기위해서 프레그먼트 둘다 가림
        fm.beginTransaction().hide(fragment3).commit();
        fm.beginTransaction().hide(fragment2).commit();
    }

    //커스텀 리스너 구현부, 클래스에 implements 시킴
    @Override
    public void onClicked(String msg) {
        fragment2.setMessage(msg);
    }
}
