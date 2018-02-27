package com.example.dwp46.cants;

import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dwp46.cants.Helpers.DownloadFileFromURL;
import com.example.dwp46.cants.Helpers.PdfParser;
import com.example.dwp46.cants.Helpers.Prato;
import com.example.dwp46.cants.Helpers.TimeConvertion;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewpager);

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
    }
}


