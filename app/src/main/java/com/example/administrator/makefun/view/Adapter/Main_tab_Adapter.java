package com.example.administrator.makefun.view.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class Main_tab_Adapter extends FragmentPagerAdapter
    {
        ArrayList<Fragment> fragments;
        ArrayList<String> titles;
        public Main_tab_Adapter(FragmentManager fm,ArrayList<Fragment> fragments,ArrayList<String> titles) {
            super(fm);
            this.fragments = fragments;
            this.titles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position%titles.size());
        }
    }