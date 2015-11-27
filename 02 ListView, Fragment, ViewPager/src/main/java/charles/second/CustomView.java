package charles.second;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Charles on 15. 11. 8..
 */
public class CustomView extends FrameLayout {

    private Context mContext;

    private ImageView imageView;
    private TextView textView;

    public CustomView(Context context) {
        super(context);
        mContext = context;
        View v = LayoutInflater.from(context).inflate(R.layout.item_view, this);
        imageView = (ImageView) v.findViewById(R.id.image);
        textView = (TextView) v.findViewById(R.id.title);
    }

    public void setMyData(MyData data) {
        String color = data.getProfile_path();
        if (color.equals("red")) {
            imageView.setImageResource(android.R.color.holo_red_dark);
        } else if (color.equals("blue")) {
            imageView.setImageResource(android.R.color.holo_blue_bright);
        }
        textView.setText(data.getName());
    }


}
