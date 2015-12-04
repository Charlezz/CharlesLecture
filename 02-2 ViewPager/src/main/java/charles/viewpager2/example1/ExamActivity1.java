package charles.viewpager2.example1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import charles.viewpager2.R;

public class ExamActivity1 extends AppCompatActivity {

    /**
     * 액티비티(어댑터 외부)에서 만든 프레그먼트를 어댑터에게 삽입하는 케이스
     */


    private Fragment1 fragment1;
    private Fragment2 fragment2;


    private ViewPagerAdapter1 mAdapter;

    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam1);

        //뷰페이저 가져오기
        pager = (ViewPager) findViewById(R.id.pager);

        //이곳에서 프레그먼트를 미리 생성.
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();

        //프레그먼트를 어레이리스트에 담음
        ArrayList<Fragment> items = new ArrayList<>();
        items.add(fragment1);
        items.add(fragment2);

        //어댑터 생성
        mAdapter = new ViewPagerAdapter1(getSupportFragmentManager());
        //items 초기화
        mAdapter.setItems(items);
        //어댑터 연결
        pager.setAdapter(mAdapter);

    }

    public void sendMessageToFragment2(String msg) {
        fragment2.setMessage(msg);
    }
}
