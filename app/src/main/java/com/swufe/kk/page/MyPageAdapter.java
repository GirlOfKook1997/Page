package com.swufe.kk.page;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.RadioButton;

public class MyPageAdapter extends FragmentPagerAdapter{

    private String[] title=new String[]{"Animal","Construction","Celebrity"};
    public MyPageAdapter(FragmentManager manager){
        super(manager);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];

    }

    //得到Fragment组件
    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new FirstFragment();
        }else if(position==1){
            return new SecondFragment();
        }else{
            return new ThirdFragment();
        }
    }

    //Fragment的个数
    @Override
    public int getCount() {
        return 3;
    }
}
