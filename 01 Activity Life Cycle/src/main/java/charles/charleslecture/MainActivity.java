package charles.charleslecture;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by Charles on 15. 11. 1..
 * 액티비티 생성주기 확인예제
 */
public class MainActivity extends Activity implements View.OnClickListener {

    public static final String TAG = "MainActivity";


    private Button startActivity, startActivityForResult, startActivityForResult2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity = (Button) findViewById(R.id.startActivity);
        startActivityForResult = (Button) findViewById(R.id.startActivityForResult);
        startActivityForResult2 = (Button) findViewById(R.id.startActivityForResult2);
        startActivity.setOnClickListener(this);
        startActivityForResult.setOnClickListener(this);
        startActivityForResult2.setOnClickListener(this);

        Log.e(TAG, "onCreate");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }


    private static final int REQUEST_ACTIVITY1 = 1;
    private static final int REQUEST_ACTIVITY2 = 2;

    @Override
    public void onClick(View v) {
        if (v.equals(startActivity)) {
            Intent i1 = new Intent(MainActivity.this, EmptyActivity1.class);
            startActivity(i1);
        } else if (v.equals(startActivityForResult)) {
            Intent i2 = new Intent(MainActivity.this, EmptyActivity1.class);
            startActivityForResult(i2, REQUEST_ACTIVITY1);
        } else if (v.equals(startActivityForResult2)) {
            Intent i3 = new Intent(MainActivity.this, EmptyActivity1.class);
            startActivityForResult(i3, REQUEST_ACTIVITY2);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        /** Standard activity result: operation canceled. */
//        public static final int RESULT_CANCELED    = 0;
//        /** Standard activity result: operation succeeded. */
//        public static final int RESULT_OK           = -1;

        String str = "";

        if(data!=null){
            if(data.hasExtra("data1234")){
                str = data.getStringExtra("data1234");
            }
        }

        Log.e(TAG, "requestCode:" + requestCode + " resultCode:" + resultCode + " data:" + str);
    }
}
