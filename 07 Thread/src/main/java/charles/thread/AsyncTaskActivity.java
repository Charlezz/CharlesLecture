package charles.thread;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AsyncTaskActivity extends AppCompatActivity implements View.OnClickListener{

    private android.widget.Button start;
    private android.widget.Button stop;
    private Toast toast;
    private MyAsyncTask myTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asyc_task);
        this.stop = (Button) findViewById(R.id.stop);
        this.start = (Button) findViewById(R.id.start);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);

        toast = Toast.makeText(this,"",Toast.LENGTH_LONG);
        myTask = new MyAsyncTask();

    }


    @Override
    public void onClick(View v) {
        if(v.equals(start)){
            myTask.execute("1000");
            return;
        }
        if(v.equals(stop)){
            myTask.cancel(true);
            return;
        }

    }

    private class MyAsyncTask extends AsyncTask<String,Integer,Long>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(AsyncTaskActivity.this,"시작!!", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Long doInBackground(String... params) {
            String temp = params[0];//첫번째 인자니까 1000이 넘어올것임
            int maxCount = Integer.parseInt(temp);
            for(int i=0;i<maxCount;i++){
                //onProgressUpdate 호출 및 인자(i..)전달
                publishProgress(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //결과 1234로 임의 리턴
            return 1234l;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            toast.setText("카운트" + values[0]);
            toast.show();
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            toast.setText("끝:"+aLong);
            toast.show();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            toast.setText("onCancelled()");
            toast.show();
        }
    }
}
