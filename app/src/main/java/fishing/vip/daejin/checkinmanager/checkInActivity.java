package fishing.vip.daejin.checkinmanager;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import fishing.vip.daejin.Fragment.AddCrewFragment;
import fishing.vip.daejin.Fragment.RegisterFragment;

public class checkInActivity extends AppCompatActivity {
    private static final int ADD_CREW = 10297;
    private static final int REGISTER = 10298;

    private ContentsPagerAdapter pagerAdapter;
    private Context context;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);
        this.context = getApplicationContext();

        //init tab
        this.tabLayout = (TabLayout) findViewById(R.id.tab_main);
        addTabs();

        //init pager
        this.viewPager = (ViewPager)findViewById(R.id.pager_content);
        pagerAdapter = new ContentsPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




    }

    private void addTabs(){
        addTab(ADD_CREW);
        addTab(REGISTER);
    }


    private void addTab(int type){
        String title = "";
        TabLayout.Tab tab;

        if(type==ADD_CREW){
            title = "명부 작성";
        }else if(type == REGISTER){
            title = "정보 등록";
        }

        tab = tabLayout.newTab();
        tab.setText(title);
        tabLayout.addTab(tab);
    }
}

class ContentsPagerAdapter extends FragmentStatePagerAdapter{

    private int pageCnt;

    public ContentsPagerAdapter(FragmentManager fm, int pageCount){
        super(fm);
        pageCnt = pageCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new AddCrewFragment();
            case 1:
                return new RegisterFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return this.pageCnt;
    }
}
