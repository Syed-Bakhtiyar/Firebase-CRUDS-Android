package com.example.bakhtiyar.simplecrudsapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Bakhtiyar on 12/30/2016.
 */
public class MyFragment extends FragmentPagerAdapter {


    public MyFragment(FragmentManager fm) {
        super(fm);
    }



    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new WriteFragment();
            case 1:
                return new ReadFragment();
            case 2:
                return new SearchFragment();
            default:
                return null;


        }
    }



    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Write Data";
            case 1:
                return "Read Data";
            case 2:
                return "Search Data with name";
            default:
                return null;


        }
    }
}
