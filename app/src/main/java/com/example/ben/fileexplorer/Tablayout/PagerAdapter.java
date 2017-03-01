package com.example.ben.fileexplorer.Tablayout;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Ben on 09.02.2017.
 */

public class PagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private int count;
    public List<Fragment> fragmentList;

    public PagerAdapter(FragmentManager fm, int Count, Context c, List<Fragment> fragments) {
        super(fm);
        count = Count;
        context = c;
        fragmentList = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return count;
    }
}
