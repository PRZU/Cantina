package com.example.dwp46.cants;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dwp46.cants.Helpers.DownloadFileFromURL;
import com.example.dwp46.cants.Helpers.PdfParser;
import com.example.dwp46.cants.Helpers.Prato;
import com.example.dwp46.cants.Helpers.TimeConvertion;
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
import java.util.HashMap;


public class MainActivity extends AppCompatActivity
{

    private ListView mainListView;
    private ArrayAdapter<String> listAdapter;
    private final String workingPath = android.os.Environment.getExternalStorageDirectory() +
            "/Cantina/";
    private HashMap<String, Prato> ementa_carne = new HashMap<>();
    private HashMap<String, Prato> ementa_vegan = new HashMap<>();
    private HashMap<String, Prato> ementa_takey = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();
show_omni();
    }


    protected void init()
    {
        if (new File(workingPath+ "EmentaTC_"+TimeConvertion.getMesAno() + ".json").exists())
        {
            try
            {
                this.ementa_carne = PdfParser.jsonLoad(workingPath + "EmentaTC_" + TimeConvertion.getMesAno() + ".json");
            }
            catch (Exception e)
            {
                e.printStackTrace();
                new DownloadFileFromURL().execute("https://www.sas.uminho.pt/uploads/EmentaTC_" +
                        TimeConvertion.getMesAno() + ".pdf");
                PdfParser.pdfParseSave(workingPath+ "EmentaTC_"+TimeConvertion.getMesAno() + ".pdf");
                this.ementa_carne = PdfParser.jsonLoad(workingPath + "EmentaTC_" + TimeConvertion.getMesAno() + ".json");
            }
        }
        else
        {
            new DownloadFileFromURL().execute("https://www.sas.uminho.pt/uploads/EmentaTC_" +
                    TimeConvertion.getMesAno() + ".pdf");
            PdfParser.pdfParseSave(workingPath+ "EmentaTC_"+TimeConvertion.getMesAno() + ".pdf");
            this.ementa_carne = PdfParser.jsonLoad(workingPath + "EmentaTC_" + TimeConvertion.getMesAno() + ".json");
        }
        /*if (new File(workingPath+ "EmentaTC_OVL_"+TimeConvertion.getMesAno() + ".json").exists())
            //load de json :)
            return;
        else
            new DownloadFileFromURL().execute("https://www.sas.uminho.pt/uploads/EmentaTC_OVL_" +
                    TimeConvertion.getMesAno() + ".pdf");
        if (new File(workingPath+ "EmentaTC_««takeway»»"+TimeConvertion.getMesAno() + ".json").exists())
            //load de json :)
            return;
        else
            new DownloadFileFromURL().execute("https://www.sas.uminho.pt/uploads/EmentaTC_«takeway»»" +
                    TimeConvertion.getMesAno() + ".pdf");
                    */
    }


    private void show_omni()
    {
        mainListView = findViewById(R.id.mainListView);

        // Create and populate a List of planet names.
        String[] planets = new String[]{"Mercury", "Venus", "Earth", "Mars",
                "Jupiter", "Saturn", "Uranus", "Neptune"};
        ArrayList<String> planetList = new ArrayList<>();
        ArrayList<String> eme = new ArrayList<>();

        //planetList.addAll(Arrays.asList(planets));

        for (Prato p: this.ementa_carne.values())
        {
            //planetList.add("ola");
            //System.out.println(p.toString());
            eme.add(p.toString());
        }

        String[] array = eme.toArray(new String[0]);
        planetList.addAll(Arrays.asList(array));
        // Create ArrayAdapter using the planet list.
        listAdapter = new ArrayAdapter<>(this, R.layout.row, planetList);

        // Add more planets. If you passed a String[] instead of a List<String>
        // into the ArrayAdapter constructor, you must not add more items.
        // Otherwise an exception will occur.
        /*listAdapter.add("Ceres");
        listAdapter.add("Pluto");
        listAdapter.add("Haumea");
        listAdapter.add("Makemake");
        listAdapter.add("Eris");
*/
        // Set the ArrayAdapter as the ListView's adapter.
        mainListView.setAdapter(listAdapter);
    }


    private void show_ovl()
    {

    }
}


