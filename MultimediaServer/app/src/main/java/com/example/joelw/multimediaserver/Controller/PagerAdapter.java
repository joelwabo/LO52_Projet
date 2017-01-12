package com.example.joelw.multimediaserver.Controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.mNumOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                MovieFragment movieTab = new MovieFragment();
                return movieTab;
            case 1:
                ImageFragment serieTab = new ImageFragment();
                return serieTab;
            case 2:
                MusicFragment musicTab = new MusicFragment();
                return musicTab;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}