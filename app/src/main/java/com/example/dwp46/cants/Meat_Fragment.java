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

public class Meat_Fragment extends Fragment {
    private TreeMap<Integer, Prato> ementa_carne = new TreeMap<>();

    public static Meat_Fragment newInstance() {

        Meat_Fragment f = new Meat_Fragment();
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

        for (Prato p : this.ementa_carne.values()) {
            if (p.getDia() == TimeConvertion.getDia())
                pos = ementa.size() + 1;
            ementa.add(p.toString());
        }

        EmentaMeatAdapter adapter = new EmentaMeatAdapter(getActivity(), ementa.toArray(new String[0]));
        tv.setAdapter(adapter);

        tv.setSelection(pos);

        return v;
    }

    private void init() {
        this.ementa_carne = DataLoader.loadFromJSON();
    }
}