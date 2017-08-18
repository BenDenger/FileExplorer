package com.example.ben.fileexplorer.Tablayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.ben.fileexplorer.Permissions;
import com.example.ben.fileexplorer.FileExplorer.PathList.FileExplorerFragment;
import com.example.ben.fileexplorer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benjamindenger on 24.02.17.
 */

public class TabLayoutActivity extends AppCompatActivity {
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
        Permissions.verifyStoragePermissions(this);
        initTabLayout();
    }

    private void initTabLayout() {
        TabLayout tabLayout;
        PagerAdapter adapter;
        
        tabLayout = (TabLayout) findViewById(R.id.TabLayout);
        addTabs(tabLayout);
        adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), this, getTabs());
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

    private void addTabs(TabLayout tabLayout) {
        tabLayout.addTab(tabLayout.newTab().setText("Bilder")); // just beautiful! Everyone loves hard coded, un-localized strings!
        tabLayout.addTab(tabLayout.newTab().setText("Videos"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    @Override
    public void onBackPressed() {
        fragmentManager = getSupportFragmentManager();
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        
        if (fragmentManager.getBackStackEntryCount() > 0) {
            if (viewPager.getCurrentItem() == 0) {
                fragmentManager.popBackStack("Pictures", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            } else {
                fragmentManager.popBackStack("Videos", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        } else {
            finish();
        }
    }

    private List<Fragment> getTabs() {
        List<Fragment> fragments;
        FileExplorerFragment pictures;
        FileExplorerFragment videos;
        Bundle picBundle;
        Bundle vidBundle;
        
        fragments = new ArrayList<>();
        pictures = new FileExplorerFragment();
        picBundle = new Bundle();
        picBundle.putString("filetype", "Pictures");
        pictures.setArguments(picBundle);
        fragments.add(pictures);

        videos = new FileExplorerFragment();
        vidBundle = new Bundle();
        
        vidBundle.putString("filetype", "Videos");
        videos.setArguments(vidBundle);
        fragments.add(videos);
        
        return fragments;
    }
}
