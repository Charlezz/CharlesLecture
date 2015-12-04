package charles.viewpager2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import charles.viewpager2.example1.ExamActivity1;
import charles.viewpager2.example2.ExamActivity2;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button example1, example2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        example1 = (Button) findViewById(R.id.example1);
        example2 = (Button) findViewById(R.id.example2);
        example1.setOnClickListener(this);
        example2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.example1:
                startActivity(new Intent(MainActivity.this, ExamActivity1.class));
                break;

            case R.id.example2:
                startActivity(new Intent(MainActivity.this, ExamActivity2.class));
                break;
        }

    }
}
