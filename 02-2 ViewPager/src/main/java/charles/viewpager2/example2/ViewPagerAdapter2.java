package charles.viewpager2.example2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Charles on 15. 12. 4..
 */
public class ViewPagerAdapter2 extends FragmentStatePagerAdapter {

    //msg 변수를 꼭 어댑터내부에 두라는것이 아님
    //이 예제에서는 어떤식으로 인자전달을 받는지를 파악하는게 중요
    private String msg = "";

    public ViewPagerAdapter2(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                //일반적인 인자전달이 필요없는경우 new 로 생성
                return new Fragment3();
            case 1:
                //정적메소드로 생성
                return Fragment4.newInstance(msg);
        }
        return null;
    }


    /**
     * PagerAdapter의 getItemPosition( Object object ), 여기서 POSITION_NONE 값을 return 한다.
     * 이 값이 들어가면 ViewPager 는 notifyDataSetChanged() 가 불릴 때마다 모든 View 를 다시 그린다.
     * 따라서 효율성이 떨어지긴 하지만 어쨌든 해결은 된다.
     * 권장할만한 방법은 아니다. 단순히 프레그먼트 생성시 정적 메소드 때문에 이예제를 만든것이기 때문에...
     * 정적메소드에 대한 이해를 하기 바람
     */
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;

    }


    @Override
    public int getCount() {
        //이미 보여줄 페이지가 두개뿐이라는 사실을 알고 있기 떄문에 상수값으로 채워넣음
        return 2;
    }

    public void setMessageToF4(String msg) {
        this.msg = msg;
    }
}
