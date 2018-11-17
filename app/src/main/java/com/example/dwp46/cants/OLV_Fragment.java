package com.example.dwp46.cants;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.dwp46.cants.Helpers.DataLoader;
import com.example.dwp46.cants.Helpers.Prato;
import com.example.dwp46.cants.Helpers.TimeConvertion;

import java.util.ArrayList;
import java.util.TreeMap;

public class OLV_Fragment extends Fragment {

    private TreeMap<Integer, Prato> ementa_olv = new TreeMap<>();

    public static OLV_Fragment newInstance() {

        OLV_Fragment f = new OLV_Fragment();
        Bundle b = new Bundle();
        f.setArguments(b);
        f.init();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.first_frag, container, false);
        ListView tv = v.findViewById(R.id.mainListView);
        ArrayList<String> ementa = new ArrayList<>();
        int pos = 0;


        for (Prato p : this.ementa_olv.values()) {
            if (p.getDia() == TimeConvertion.getDia())
                pos = ementa.size() + 1;
            ementa.add(p.toString());
        }

        EmentaOVLAdapter adapter = new EmentaOVLAdapter(getActivity(), ementa.toArray(new String[0]));
        tv.setAdapter(adapter);

        tv.setSelection(pos);

        return v;
    }

    private void init() {
        this.ementa_olv = DataLoader.loadFromJSON();
    }
}