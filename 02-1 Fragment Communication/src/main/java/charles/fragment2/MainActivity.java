package charles.fragment2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import charles.fragment2.example1.ExampleActivity1;
import charles.fragment2.example2.ExampleActivity2;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button example1, example2, example3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        example1 = (Button) findViewById(R.id.example1);
        example2 = (Button) findViewById(R.id.example2);
        example3 = (Button) findViewById(R.id.example3);

        example1.setOnClickListener(this);
        example2.setOnClickListener(this);
        example3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        /**중요
         *프레그먼트간의 직접적인 통신은 불가능하므로
         *꼭 모체가 되는 액티비티를 거쳐서 통신을 해야한다
         * */
        switch (v.getId()) {
            case R.id.example1:
                startActivity(new Intent(MainActivity.this, ExampleActivity1.class));
                break;
            case R.id.example2:
                startActivity(new Intent(MainActivity.this, ExampleActivity2.class));
                break;
        }
    }
}
