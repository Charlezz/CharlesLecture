package charles.viewpager2.example2;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import charles.viewpager2.R;

public class ExamActivity2 extends AppCompatActivity {

    /**
     * 프레그먼트를 직접 new하지 않고 정적 메소드를 이용하여 생성 하는 케이스
     * 이유 : 일반적으로 프레그먼트는 구조적 측면상 생성자에 인자를 직접적으로 입력받지 않도록 하고 있다
     * 그러므로 정적 메소드를 통해 인자를 입력받고 생성한다. 자세한건 프레그먼트와 어댑터 참조
     */

    private ViewPagerAdapter2 mAdapter;

    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam1);

        pager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new ViewPagerAdapter2(getSupportFragmentManager());
        pager.setAdapter(mAdapter);

    }

    public void sendMessageToFragment4(String msg) {
        mAdapter.setMessageToF4(msg);

        //프레그먼트가 어댑터에서 내부적으로 다시 생성될것이며, 뷰도 새로 그림
        mAdapter.notifyDataSetChanged();

    }
}
