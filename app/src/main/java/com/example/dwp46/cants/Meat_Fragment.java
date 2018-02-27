package com.example.dwp46.cants;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dwp46.cants.Helpers.DownloadFileFromURL;
import com.example.dwp46.cants.Helpers.PdfParser;
import com.example.dwp46.cants.Helpers.Prato;
import com.example.dwp46.cants.Helpers.TimeConvertion;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

public class Meat_Fragment extends Fragment
{
    private TreeMap<Integer, Prato> ementa_carne = new TreeMap<>();
    private final String workingPath = android.os.Environment.getExternalStorageDirectory() +
            "/Cantina/";
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.first_frag, container, false);
        this.view = v;
        ListView tv = v.findViewById(R.id.mainListView);
        ArrayList<String> ementa = new ArrayList<>();
        int pos = 0;

        for (Prato p : this.ementa_carne.values())
        {
            if(p.getDia() == TimeConvertion.getDia())
                pos = ementa.size() + 1;
            ementa.add(p.toString());
        }

        EmentaAdapter adapter = new EmentaAdapter(getActivity(), ementa.toArray(new String[0]));
        tv.setAdapter(adapter);

        tv.setSelection(pos);

        return v;
    }


    private void init()
    {
        DownloadFileFromURL dfl = new DownloadFileFromURL();

        if (new File(workingPath + "EmentaTC_" + TimeConvertion.getMesAno() + ".json").exists())
        {
            try
            {
                this.ementa_carne = PdfParser.jsonLoad(workingPath + "EmentaTC_" +
                        TimeConvertion.getMesAno() + ".json");
                dfl.execute("http://www.sas.uminho.pt/uploads/EmentaTC_" +
                        TimeConvertion.getMesAno() + ".pdf").get();
            }
            catch (ExecutionException | InterruptedException e1)
            {
                e1.printStackTrace();
            }

        }
        else
        {
            dfl = new DownloadFileFromURL();
            try
            {
                dfl.execute("http://www.sas.uminho.pt/uploads/EmentaTC_" +
                        TimeConvertion.getMesAno() + ".pdf").get();
            }
            catch (ExecutionException | InterruptedException e)
            {
                e.printStackTrace();
            }
            PdfParser.pdfParseSave(workingPath + "EmentaTC_" + TimeConvertion.getMesAno() + ".pdf");
            this.ementa_carne = PdfParser.jsonLoad(workingPath + "EmentaTC_" + TimeConvertion.getMesAno() + ".json");
        }
    }

    public static Meat_Fragment newInstance()
    {

        Meat_Fragment f = new Meat_Fragment();
        Bundle b = new Bundle();
        f.setArguments(b);
        f.init();
        return f;
    }
}