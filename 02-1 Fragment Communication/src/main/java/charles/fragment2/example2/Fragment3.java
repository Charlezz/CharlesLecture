package charles.fragment2.example2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import charles.fragment2.R;

/**
 * Created by Charles on 15. 12. 2..
 */
public class Fragment3 extends Fragment implements View.OnClickListener {

    private EditText editText1;
    private Button send1;


    private ExampleActivity2 activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (ExampleActivity2) context;
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

    /**
     * 커스텀 리스너 (인터페이스를 통한 통신) 설계
     */
    //버튼을 눌렀을때 보내고 싶은 데이터의 타입을 메소드로 설계해준다. 밑의예제는 문자열을 보냄
    public interface OnButtonClicked {
        void onClicked(String msg);
    }

    //이프레그먼트와 리스너(인터페이스)로 통신할 변수 선언
    private OnButtonClicked onButtonClickedListener;

    //리스너를 등록하기 위한 메소드
    public void setOnButtonClickedListener(OnButtonClicked listener) {
        //이 메소드를 사용할 클래스와 리스너를 연결
        onButtonClickedListener = listener;
    }

    /**
     * 커스텀 리스너 (인터페이스를 통한 통신) 설계 끝
     */

    @Override
    public void onClick(View v) {
        if (send1.equals(v)) {
            //이 프레그먼트에서 클릭을 받으면 에디트 텍스트의 내용을 리스너를 통해 전달한다.
            //리스너가 null이면 아직 setOnButtonClickedListener메소드를 통해 연결된 몸체가 없는것이므로
            //데이터를 전송하지 않음, null체크를 안하면 null포인터 익셉션이 남
            if(onButtonClickedListener !=null){
                //에디트 텍스트로 부터 메시지 만듦
                String msg = editText1.getText().toString();

                //이 인터페이스를 구현한 클래스에게 msg를 전송하게됨
                onButtonClickedListener.onClicked(msg);
            }
            return;
        }
    }


}
