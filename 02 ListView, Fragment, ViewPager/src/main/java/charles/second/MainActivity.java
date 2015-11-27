package charles.second;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //옛날방법, 프레그먼트가 나오기 이전 시절.
    //프레그먼트는 3.0(허니컴)에서 등장
    //프레그먼트란?

    public static final String TAG = "MainActivity";

    private static final int PAGE_LAYOUT_1 = 1;
    private static final int PAGE_LAYOUT_2 = 2;
    private static final int PAGE_LAYOUT_3 = 3;
    private static final int PAGE_LAYOUT_4 = 4;


    private ImageButton btn1;
    private ImageButton btn2;
    private ImageButton btn3;
    private ImageButton btn4;
    private LinearLayout layout1;
    private LinearLayout layout2;
    private LinearLayout layout3;
    private LinearLayout layout4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.layout4 = (LinearLayout) findViewById(R.id.layout4);
        this.layout3 = (LinearLayout) findViewById(R.id.layout3);
        this.layout2 = (LinearLayout) findViewById(R.id.layout2);
        this.layout1 = (LinearLayout) findViewById(R.id.layout1);
        this.btn4 = (ImageButton) findViewById(R.id.btn4);
        this.btn3 = (ImageButton) findViewById(R.id.btn3);
        this.btn2 = (ImageButton) findViewById(R.id.btn2);
        this.btn1 = (ImageButton) findViewById(R.id.btn1);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.equals(btn1)) {
            showLayout(PAGE_LAYOUT_1);
        } else if (v.equals(btn2)) {
            showLayout(PAGE_LAYOUT_2);
        } else if (v.equals(btn3)) {
            showLayout(PAGE_LAYOUT_3);
        } else if (v.equals(btn4)) {
            showLayout(PAGE_LAYOUT_4);
        }
    }

    public void showLayout(int page) {

        layout1.setVisibility(View.GONE);
        layout2.setVisibility(View.GONE);
        layout3.setVisibility(View.GONE);
        layout4.setVisibility(View.GONE);


        switch (page) {
            case PAGE_LAYOUT_1:
                layout1.setVisibility(View.VISIBLE);
                break;
            case PAGE_LAYOUT_2:
                layout2.setVisibility(View.VISIBLE);
                break;
            case PAGE_LAYOUT_3:
                layout3.setVisibility(View.VISIBLE);
                break;
            case PAGE_LAYOUT_4:
                layout4.setVisibility(View.VISIBLE);
                break;
        }
    }
}
