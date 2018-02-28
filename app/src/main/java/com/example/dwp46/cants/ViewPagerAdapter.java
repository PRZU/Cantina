package com.example.dwp46.cants;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class ViewPagerAdapter extends FragmentPagerAdapter
{

    ViewPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos)
    {
        switch (pos)
        {

            case 0:
                return Meat_Fragment.newInstance();
            case 1:
                return OLV_Fragment.newInstance();
            //  case 2:
            //     return TakeAway_Fragment.newInstance();
            default:
                return Meat_Fragment.newInstance();
        }
    }

    @Override
    public int getCount()
    {
        return 2;
    }

}