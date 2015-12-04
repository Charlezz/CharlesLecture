package charles.viewpager2.example2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import charles.viewpager2.R;


/**
 * Created by Charles on 15. 12. 2..
 */
public class Fragment3 extends Fragment implements View.OnClickListener {

    private EditText editText1;
    private Button send1;


    private ExamActivity2 activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (ExamActivity2) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment1_layout, container, false);

        editText1 = (EditText) v.findViewById(R.id.edit_text1);
        send1 = (Button) v.findViewById(R.id.send1);

        send1.setOnClickListener(this);


        return v;
    }

    @Override
    public void onClick(View v) {
        if (send1.equals(v)) {
            String msg = editText1.getText().toString();
            activity.sendMessageToFragment4(msg);
            return;
        }
    }


}
