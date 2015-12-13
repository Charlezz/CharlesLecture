package charles.thread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SimpleUiUpdateActivity extends AppCompatActivity implements View.OnClickListener,Runnable{

    private android.widget.Button start;

    private Thread subThread;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_ui_update);
        this.start = (Button) findViewById(R.id.start);

        start.setOnClickListener(this);

        toast = Toast.makeText(SimpleUiUpdateActivity.this, "", Toast.LENGTH_SHORT);


    }

    @Override
    public void onClick(View v) {
        if(v.equals(start)){
            subThread = new Thread(this);
            subThread.start();
            return;
        }
    }

    @Override
    public void run() {
        for(int i =0;i<100;i++){
            final int finalI = i;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    toast.setText("카운트:" + finalI);
                    toast.show();
                }
            });

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
