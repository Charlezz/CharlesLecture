package charles.viewpager2.example2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import charles.viewpager2.R;

/**
 * Created by Charles on 15. 12. 2..
 */
public class Fragment4 extends Fragment {

    public static final String TAG = "Fragment4";
    private String msg;

    public static Fragment4 newInstance(String msg) {
        Fragment4 f = new Fragment4();
        Bundle b = new Bundle();
        b.putString(TAG, msg);
        f.setArguments(b);
        return f;
    }

    private TextView tv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
        msg = getArguments().getString(TAG);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView");
        View v = inflater.inflate(R.layout.fragment2_layout, null);
        tv = (TextView) v.findViewById(R.id.tv);
        tv.setText(msg);
        return v;
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
