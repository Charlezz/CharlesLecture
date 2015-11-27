package charles.second;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

public class ViewPagerActivity extends AppCompatActivity {

    public static final String TAG = "ViewPagerActivity";

    private static final int PAGE_LAYOUT_1 = 1;
    private static final int PAGE_LAYOUT_2 = 2;
    private static final int PAGE_LAYOUT_3 = 3;
    private static final int PAGE_LAYOUT_4 = 4;
    private ViewPager pager;
    private TabLayout slidingtabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        this.slidingtabs = (TabLayout) findViewById(R.id.sliding_tabs);
        this.pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new MyPagerAdaper(getSupportFragmentManager()));


        //자동으로 텝 레이아웃과 뷰페이저 연결, 한방에
//        slidingtabs.setupWithViewPager(pager);

        //수동으로 서로 조인
        slidingtabs.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager));
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(slidingtabs));

        slidingtabs.addTab(slidingtabs.newTab().setIcon(R.mipmap.ic_launcher));
        slidingtabs.addTab(slidingtabs.newTab().setText("Tab2"));
        slidingtabs.addTab(slidingtabs.newTab().setIcon(R.mipmap.ic_launcher).setText("Tab3"));
        slidingtabs.addTab(slidingtabs.newTab().setIcon(R.mipmap.ic_launcher).setText("Tab4"));

    }


    public static class MyPagerAdaper extends FragmentStatePagerAdapter {

        public MyPagerAdaper(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return MyFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 4;
        }

    }

    public static class MyFragment extends Fragment {

        public static final String TAG = "MyFragment";

        private android.widget.Button fbtn1;
        private int page;

        private ArrayList<MyData> items = new ArrayList<>();


        public static MyFragment newInstance() {
            return new MyFragment();
        }

        public static MyFragment newInstance(int page) {
//            this.page = page;//틀린 방법
            MyFragment fragment = new MyFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(TAG, page);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                page = getArguments().getInt(TAG);
            }


        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = null;

            switch (page) {
                case PAGE_LAYOUT_1:
                    v = inflater.inflate(R.layout.layout1, container, false);

                    ListView listView = (ListView) v.findViewById(R.id.listView);
                    CustomAdapter adapter = new CustomAdapter(getActivity());
                    listView.setAdapter(adapter);

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
                        items.add(d);
                    }
                    adapter.setItems(items);
                    break;
                case PAGE_LAYOUT_2:
                    v = inflater.inflate(R.layout.layout2, container, false);
                    break;
                case PAGE_LAYOUT_3:
                    v = inflater.inflate(R.layout.layout3, container, false);
                    break;
                case PAGE_LAYOUT_4:
                    v = inflater.inflate(R.layout.layout4, container, false);
                    break;

            }
            return v;
        }

        @Override
        public void onDetach() {
            super.onDetach();
            Log.e(TAG, "프레그먼트가 액티비티에서 떨어짐=메모리에서 수거됨:" + page);
        }
    }
}
