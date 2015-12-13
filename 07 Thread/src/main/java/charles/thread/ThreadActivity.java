package charles.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class ThreadActivity extends AppCompatActivity implements View.OnClickListener {

    private Thread subThread;
    private android.widget.Button start;
    private android.widget.Button stop;

    private Toast toast;
    private MyHandler handler;

    private boolean threadFlag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        this.stop = (Button) findViewById(R.id.stop);
        this.start = (Button) findViewById(R.id.start);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);


        handler = new MyHandler(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                threadFlag = false;
                subThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 1; i <= 10; i++) {
                            //플래그 이용하는 방법
                            if (threadFlag) {
                                break;
                            }

                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            handler.sendEmptyMessage(i);

                        }
                    }
                });
                subThread.start();
                break;
            case R.id.stop:
                /**
                 * 쓰레드 멈추는법
                 * 1. flag
                 *
                 * 2. interrupt
                 *
                 * 3. 프로세스 강제 종료
                 *
                 * 4. Daemon
                 * */


                //          1번방법

                threadFlag = true;



                //2번 방법
                //subThread.interrupt();


                //3번 프로세스 강제종료
//                android.os.Process.killProcess(android.os.Process.myPid());

                //4번
                //subThread.setDaemon(true);
                break;
        }
    }

    private void handleMessage(Message msg){

        int count = msg.what;
        toast.setText("카운트:"+count);
        toast.show();

    }

    private static class MyHandler extends Handler{

        private WeakReference<ThreadActivity> threadActivityWeakReference;

        public MyHandler(ThreadActivity activity){
            threadActivityWeakReference = new WeakReference<ThreadActivity>(activity);
        }


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            threadActivityWeakReference.get().handleMessage(msg);
        }
    }


}
