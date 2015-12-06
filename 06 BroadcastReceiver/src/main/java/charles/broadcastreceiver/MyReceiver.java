package charles.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Charles on 15. 12. 6..
 */
public class MyReceiver extends BroadcastReceiver {


    public static final String TAG = "MyReceiver";
    //글로벌방식
    //리시버를 만들어주고 , 메니페스트에 등록한뒤 인텐트필터를 적용

    @Override
    public void onReceive(Context context, Intent intent) {

        //매니페스트에 있는 인텐트필터 내의 해당 액션이 있을 때마다 onReceive가 호출됨
        Log.e(TAG, "onReceived");


        //action메시지 구분하는방법


        String action = intent.getAction();
        Log.e(TAG, "action:" + action);
        Intent i = new Intent(context, MainActivity.class);
        //AndroidRuntimeException이 날때 하나 추가 해주는 인텐트 플래그
        //이유 : 다른 컴포넌트에서 한번도 실행되지 않은 액티비티가 참조되려고 할때  나는 에러
        //requires the FLAG_ACTIVITY_NEW_TASK flag. Is this really what you want? 이런게 보이면 쓰면됨
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("action", action);
        context.startActivity(i);

    }
}
