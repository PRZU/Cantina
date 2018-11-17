package com.example.dwp46.cants.Helpers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;

import com.example.dwp46.cants.GCalendarAuth;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;


/**
 * Created by cruz on 03/02/2018.
 */

public class DataLoader extends AsyncTask<String, String, String> {
    private final Context is; /* Shouldn't be done like this, but ... */

    public DataLoader(Context is) {
        this.is = is;
    }

    public static TreeMap<Integer, Prato> loadFromJSON() {
        TreeMap<Integer, Prato> ementa = new TreeMap<>();
        byte[] data = null;

        try {
            File file = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Cantina/cache.json");
            FileInputStream fis = new FileInputStream(file);
            data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONObject jObject = new JSONObject(String.valueOf(new String(data, "UTF-8")));
            Iterator<?> keys = jObject.keys();

            while (keys.hasNext()) {
                String key = (String) keys.next();
                Gson gson = new Gson();
                Prato prato = gson.fromJson(jObject.getString(key), Prato.class);
                ementa.put(Integer.valueOf(key), prato);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ementa;
    }

    @SuppressLint("NewApi")
    @Override
    public String doInBackground(String... f_url) {
        try {
            is.getAssets().open("EmentasUM-5029bdb036ca.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);

        cal.set(Calendar.DAY_OF_MONTH, 1);
        DateTime monthInit = new DateTime(cal.getTime());
        cal.add(Calendar.MONTH, 1);
        DateTime monthEnd = new DateTime(cal.getTime());

        String IDmeatA = "5ttsisforihpn2o3blhe3s4tlo@group.calendar.google.com";
        String IDovlA = "o1n262ugsh3tg96jn6t75f8fnc@group.calendar.google.com";
        String IDmeatJ = "uinm3kojaoe3llod88ma22o78s@group.calendar.google.com";
        String IDovlJ = "l1suartp8gksjb6k0fosk7uo60@group.calendar.google.com";

        List<Event> meatA = null;
        List<Event> ovlA = null;
        List<Event> meatJ = null;
        List<Event> ovlJ = null;
        try {
            meatA = GCalendarAuth.getService(this.is).events().list(IDmeatA)
                    .setMaxResults(31)
                    .setOrderBy("startTime")
                    .setTimeMin(monthInit)
                    .setTimeMax(monthEnd)
                    .setSingleEvents(true)
                    .execute().getItems();
            ovlA = GCalendarAuth.getService(this.is).events().list(IDovlA)
                    .setMaxResults(31)
                    .setOrderBy("startTime")
                    .setTimeMin(monthInit)
                    .setTimeMax(monthEnd)
                    .setSingleEvents(true)
                    .execute().getItems();
            meatJ = GCalendarAuth.getService(this.is).events().list(IDmeatJ)
                    .setMaxResults(31)
                    .setOrderBy("startTime")
                    .setTimeMin(monthInit)
                    .setTimeMax(monthEnd)
                    .setSingleEvents(true)
                    .execute().getItems();
            ovlJ = GCalendarAuth.getService(this.is).events().list(IDovlJ)
                    .setMaxResults(31)
                    .setOrderBy("startTime")
                    .setTimeMin(monthInit)
                    .setTimeMax(monthEnd)
                    .setSingleEvents(true)
                    .execute().getItems();


        } catch (IOException e) {
            e.printStackTrace();
        }

        HashMap<String, String> meatAlm = new HashMap<>();
        HashMap<String, Tuple<String, String>> ovlAlm = new HashMap<>();
        HashMap<String, String> meatJan = new HashMap<>();
        HashMap<String, Tuple<String, String>> ovlJan = new HashMap<>();

        for (Event a : Objects.requireNonNull(meatA)) {
            String dt = a.getStart().getDateTime().toString();
            int last = dt.indexOf('T');
            meatAlm.put(dt.substring(0, last), a.getSummary());
        }

        for (Event a : Objects.requireNonNull(ovlA)) {
            String dt = a.getStart().getDateTime().toString();
            int last = dt.indexOf('T');
            String[] food = a.getSummary().split(";");
            ovlAlm.put(dt.substring(0, last), new Tuple<>(food[0], food[1]));
        }

        for (Event a : Objects.requireNonNull(meatJ)) {
            String dt = a.getStart().getDateTime().toString();
            int last = dt.indexOf('T');
            meatJan.put(dt.substring(0, last), a.getSummary());
        }

        for (Event a : Objects.requireNonNull(ovlJ)) {
            String dt = a.getStart().getDateTime().toString();
            int last = dt.indexOf('T');
            String[] food = a.getSummary().split(";");
            ovlJan.put(dt.substring(0, last), new Tuple<>(food[0], food[1]));
        }
        HashMap<String, Prato> ementa = new HashMap<>();

        for (String k : ovlAlm.keySet()) {
            int day = Integer.parseInt(k.split("-")[2]);
            int mes = Integer.parseInt(k.split("-")[1]);
            ementa.put(String.valueOf(day), new Prato(day, mes, meatAlm.get(k), Objects.requireNonNull(ovlAlm.get(k)).x, Objects.requireNonNull(ovlAlm.get(k)).y, meatJan.get(k), ovlJan.get(k).x,
                    Objects.requireNonNull(ovlJan.get(k)).y));

        }

        Gson gson = new Gson();

        try {
            File x = new File(Environment
                    .getExternalStorageDirectory().toString() + "/Cantina/" + "cache" + ".json");
            x.createNewFile();
            FileWriter ol = new FileWriter(x);
            JSONObject jsonObject = new JSONObject();

            for (Object o : ementa.entrySet()) {
                Map.Entry pairs = (Map.Entry) o;
                jsonObject.put(String.valueOf(pairs.getKey()), gson.toJson(pairs.getValue()));
            }
            ol.write(String.valueOf(jsonObject));
            ol.close();
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
