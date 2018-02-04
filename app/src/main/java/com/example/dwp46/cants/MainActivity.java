package com.example.dwp46.cants;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dwp46.cants.Helpers.DownloadFileFromURL;
import com.example.dwp46.cants.Helpers.TimeMapper;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    private ListView mainListView;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();

    }


    protected void init()
    {
        new DownloadFileFromURL().execute("https://www.sas.uminho.pt/uploads/EmentaTC_" +
                new TimeMapper().getMesAno() + ".pdf");
       // new DownloadFileFromURL().execute("https://www.sas.uminho.pt/uploads/EmentaTC_OVL_" +
         //               new TimeMapper().getMesAno() + ".pdf");
        //new DownloadFileFromURL().execute("https://www.sas.uminho.pt/uploads/TAKEAYAYYYA.pdf");
    }



    private void show_omni() {
        mainListView = findViewById(R.id.mainListView);

        // Create and populate a List of planet names.
        String[] planets = new String[]{"Mercury", "Venus", "Earth", "Mars",
                "Jupiter", "Saturn", "Uranus", "Neptune"};
        ArrayList<String> planetList = new ArrayList<>();
        planetList.addAll(Arrays.asList(planets));

        // Create ArrayAdapter using the planet list.
        listAdapter = new ArrayAdapter<String>(this, R.layout.row, planetList);

        // Add more planets. If you passed a String[] instead of a List<String>
        // into the ArrayAdapter constructor, you must not add more items.
        // Otherwise an exception will occur.
        listAdapter.add("Ceres");
        listAdapter.add("Pluto");
        listAdapter.add("Haumea");
        listAdapter.add("Makemake");
        listAdapter.add("Eris");

        // Set the ArrayAdapter as the ListView's adapter.
        mainListView.setAdapter(listAdapter);
    }


    private void show_ovl() {

    }
}


