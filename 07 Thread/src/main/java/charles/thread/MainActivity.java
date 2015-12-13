package charles.thread;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_thread, btn_asynctask,btn_runOnUiUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_thread = (Button) findViewById(R.id.btn_thread);
        btn_asynctask = (Button) findViewById(R.id.btn_asynctask);
        btn_runOnUiUpdate = (Button) findViewById(R.id.btn_runOnUiUpdate);

        btn_thread.setOnClickListener(this);
        btn_asynctask.setOnClickListener(this);
        btn_runOnUiUpdate.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.equals(btn_thread)) {
            startActivity(new Intent(MainActivity.this, ThreadActivity.class));
            return;
        }

        if (v.equals(btn_asynctask)) {
            startActivity(new Intent(MainActivity.this, AsyncTaskActivity.class));
            return;
        }
        if (v.equals(btn_runOnUiUpdate)) {
            startActivity(new Intent(MainActivity.this, SimpleUiUpdateActivity.class));
            return;
        }

    }
}
