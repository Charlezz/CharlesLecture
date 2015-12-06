package charles.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        tv = (TextView) findViewById(R.id.tv);

        String action = getIntent().getStringExtra("action");
        tv.setText(action);

        findViewById(R.id.register).setOnClickListener(this);
        findViewById(R.id.unregister).setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerAirplaneReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //로컬리시버에 의한 메모리 누수 방지
        unregisterAirplaneReceiver();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        //로컬 리시버 활용법
        switch (v.getId()) {
            case R.id.register:
                Toast.makeText(MainActivity.this, "register", Toast.LENGTH_SHORT).show();
                registerAirplaneReceiver();
                return;
            case R.id.unregister:
                Toast.makeText(MainActivity.this, "unregister", Toast.LENGTH_SHORT).show();
                unregisterAirplaneReceiver();
                return;
        }
    }

    private void registerAirplaneReceiver() {

        if (br == null) {
            br = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String action = intent.getAction();
                    if (action.equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)) {
                        //에어플레인 메시지 수신 가능
                        Toast.makeText(MainActivity.this, "에어플레인 모드 변경 되었음", Toast.LENGTH_SHORT).show();
                    }
                }
            };
            IntentFilter iFilter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
            //복수개 등록시 iFilter.addAction(액션);
            registerReceiver(br, iFilter);
        }
    }

    private void unregisterAirplaneReceiver() {
        if (br != null) {
            unregisterReceiver(br);
            br = null;
        }
    }

    private BroadcastReceiver br;
}
