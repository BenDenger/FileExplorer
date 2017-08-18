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
    private Context _context;
    private int _count;
    public List<Fragment> _fragmentList;

    public PagerAdapter(FragmentManager fm, int count, Context context, List<Fragment> fragments) {
        super(fm);
        _count = count;
        _context = context;
        _fragmentList = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return _fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return _count;
    }
}
