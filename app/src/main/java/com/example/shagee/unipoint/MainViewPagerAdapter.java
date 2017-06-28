package com.example.shagee.unipoint;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private Context ctx;

    public MainViewPagerAdapter(Context ctx, FragmentManager fm){
        super(fm);
        this.ctx = ctx;
    }

    @Override
    public Fragment getItem(int position)
    {
        Fragment fragment = null;

        if(position == 0){
            fragment = new PointsFragment();
        }
        if(position == 1){
            fragment = new OffersFragment();
        }
        if(position == 2){
            fragment = new CouponsFragment();
        }

        return fragment;
    }

    @Override
    public int getCount()
    {
        return 3;
    }

}
