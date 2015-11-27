package charles.lecture05.mvc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import charles.lecture05.PhoneBook;
import charles.lecture05.R;

/**
 * Created by kangjungu on 2015. 11. 15..
 */
public class CustomView extends FrameLayout {
    public static final String TAG = "CustomView";

    private Context mContext;

    private TextView name,number,id;


    public CustomView(Context context) {
        super(context);
        mContext = context;
        View v = LayoutInflater.from(context).inflate(R.layout.itme_view, this);
        name = (TextView) v.findViewById(R.id.name);
        number = (TextView) v.findViewById(R.id.number);
        id = (TextView) v.findViewById(R.id.id);
    }

    public void setMyData(PhoneBook data) {
        name.setText(data.getName());
        number.setText(data.getNumber() + "");
        id.setText(data.getId() + "");
    }


}
