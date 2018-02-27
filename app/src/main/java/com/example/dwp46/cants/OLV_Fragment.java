package com.example.dwp46.cants;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

public class OLV_Fragment extends Fragment
{
    private TreeMap<Integer, Prato> ementa_olv = new TreeMap<>();
    private final String workingPath = android.os.Environment.getExternalStorageDirectory() +
            "/Cantina/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.first_frag, container, false);

        ListView tv = v.findViewById(R.id.mainListView);
        ArrayList<String> ementa = new ArrayList<>();


        for (Prato p : this.ementa_olv.values())
        {
            ementa.add(p.toString());
        }

        final ArrayAdapter adapter = new ArrayAdapter<>(getActivity(),
                R.layout.row,
                ementa);

        tv.setAdapter(adapter);

        return v;
    }


    private void init()
    {
        DownloadFileFromURL dfl = new DownloadFileFromURL();

        if (new File(workingPath + "EmentaTC_OVL_" + TimeConvertion.getMesAno() + ".json").exists())
        {
            try
            {
                this.ementa_olv = PdfParser.jsonLoad(workingPath + "EmentaTC_OVL_" +
                        TimeConvertion.getMesAno() + ".json");
                dfl.execute("http://www.sas.uminho.pt/uploads/EmentaTC_OVL_" +
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
                dfl.execute("http://www.sas.uminho.pt/uploads/EmentaTC_OVL_" +
                        TimeConvertion.getMesAno() + ".pdf").get();
            }
            catch (ExecutionException | InterruptedException e)
            {
                e.printStackTrace();
            }
            PdfParser.pdfParseSave(workingPath + "EmentaTC_OVL_" + TimeConvertion.getMesAno() + ".pdf");
            this.ementa_olv = PdfParser.jsonLoad(workingPath + "EmentaTC_OVL_" + TimeConvertion.getMesAno() + ".json");
        }
    }

    public static OLV_Fragment newInstance()
    {

        OLV_Fragment f = new OLV_Fragment();
        Bundle b = new Bundle();
        f.setArguments(b);
        f.init();
        return f;
    }
}