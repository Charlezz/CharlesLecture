package charles.fragment2.example1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import charles.fragment2.R;

/**
 * Created by Charles on 15. 12. 2..
 */
public class Fragment2 extends Fragment {

    public static final String TAG = "Fragment2";

    private TextView tv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG,"onCreateView");
        View v = inflater.inflate(R.layout.fragment2_layout, null);
        tv = (TextView) v.findViewById(R.id.tv);
        return v;
    }

    public void setMessage(String message) {
        tv.setText(message);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, "destroyview");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "destroy");
    }
}
