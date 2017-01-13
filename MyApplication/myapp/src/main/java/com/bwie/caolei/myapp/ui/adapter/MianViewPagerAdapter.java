package com.bwie.caolei.myapp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * autour: 曹磊
 * date: 2017/1/5 11:10
 * update: 2017/1/5
 */


public class MianViewPagerAdapter extends FragmentPagerAdapter {


    private ArrayList<String> fm_tab;
    private List<Fragment> fragment_list = new ArrayList<Fragment>();


    public MianViewPagerAdapter(FragmentManager fm, List<Fragment> fragment_list, ArrayList<String> fm_tab) {

        super(fm);
        this.fragment_list = fragment_list;
        this.fm_tab=fm_tab;
    }


    @Override
    public Fragment getItem(int position) {
        return fragment_list.get(position);
    }

    @Override
    public int getCount() {
        return fragment_list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fm_tab.get(position%fm_tab.size());
    }
}
