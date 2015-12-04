package charles.viewpager2.example1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by Charles on 15. 12. 4..
 */
public class ViewPagerAdapter1 extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> items = new ArrayList<>();

    public ViewPagerAdapter1(FragmentManager fm) {
        super(fm);
    }

    public void setItems(ArrayList<Fragment> items) {
        this.items = items;
    }

    @Override
    public Fragment getItem(int position) {
        //items에 보여줄 프레그먼트가 없으면 null을 리턴
        if (items.size() == 0) {
            return null;
        }

        //리스트에 있는 프레그먼트를 포지션순으로 리턴해서 컨테이너에 보여줌
        return items.get(position);
    }

    @Override
    public int getCount() {
        //이미 보여줄 페이지가 두개뿐이라는 사실을 알고 있기 떄문에 상수값으로 채워넣음
        return 2;
    }
}
