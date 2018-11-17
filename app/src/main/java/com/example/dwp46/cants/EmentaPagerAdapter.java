package com.example.dwp46.cants;

import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dwp46.cants.Helpers.DataLoader;

import java.io.File;
import java.util.concurrent.ExecutionException;

class EmentaPagerAdapter extends FragmentPagerAdapter
{
    EmentaPagerAdapter(FragmentManager fm, MainActivity mainActivity)
    {
        super(fm);
        if(!new File(Environment.getExternalStorageDirectory().
                toString() + "/Cantina/cache.json").exists())
        {
            try {
                new DataLoader(mainActivity).execute("").get();
            } catch (ExecutionException|InterruptedException e) {
                e.printStackTrace();
            }
        }
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