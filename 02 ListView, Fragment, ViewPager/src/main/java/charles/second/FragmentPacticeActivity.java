package charles.second;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

public class FragmentPacticeActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PAGE_LAYOUT_1 = 1;
    private static final int PAGE_LAYOUT_2 = 2;
    private static final int PAGE_LAYOUT_3 = 3;
    private static final int PAGE_LAYOUT_4 = 4;


    private ImageButton btn1;
    private ImageButton btn2;
    private ImageButton btn3;
    private ImageButton btn4;
    private FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_pactice);

        this.container = (FrameLayout) findViewById(R.id.container);
        this.btn4 = (ImageButton) findViewById(R.id.btn4);
        this.btn3 = (ImageButton) findViewById(R.id.btn3);
        this.btn2 = (ImageButton) findViewById(R.id.btn2);
        this.btn1 = (ImageButton) findViewById(R.id.btn1);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, MyFragment.newInstance()).commit();

    }

    @Override
    public void onClick(View v) {
        if (v.equals(btn1)) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, MyFragment.newInstance(PAGE_LAYOUT_1)).commit();
        } else if (v.equals(btn2)) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, MyFragment.newInstance(PAGE_LAYOUT_2)).commit();
        } else if (v.equals(btn3)) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, MyFragment.newInstance(PAGE_LAYOUT_3)).commit();
        } else if (v.equals(btn4)) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, MyFragment.newInstance(PAGE_LAYOUT_4)).commit();
        }
    }

    public static class MyFragment extends Fragment {

        public static final String TAG = "MyFragment";

        private Button fbtn1;
        private int page;


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
                    this.fbtn1 = (Button) v.findViewById(R.id.f_btn1);
                    fbtn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getActivity(), "토스트 뿅", Toast.LENGTH_SHORT).show();
                        }
                    });

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
