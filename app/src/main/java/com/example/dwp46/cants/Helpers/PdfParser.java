package com.example.dwp46.cants.Helpers;

import android.annotation.SuppressLint;

import com.google.gson.Gson;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cruz on 03/02/2018.
 */

public class PdfParser
{

    public static void pdfParseSave(String filename)
    {
        PdfReader reader = null;
        String pattern_dia = "(?<=Dia )(.*)";
        String pattern_alm = "(?<=Almoço )(.*)";
        String pattern_jnt = "(?<=Jantar )(.*)";
        Pattern r1 = Pattern.compile(pattern_dia);
        Pattern r2 = Pattern.compile(pattern_alm);
        Pattern r3 = Pattern.compile(pattern_jnt);

        String aux_dia = null;
        String alm = null;
        String jnt = null;

        @SuppressLint("UseSparseArrays") HashMap<String, Prato> ementa = new HashMap<>();

        try
        {
            reader = new PdfReader(filename);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        for (int i = 1; i <= reader.getNumberOfPages(); i++)
        {
            try
            {
                String[] lines = PdfTextExtractor.getTextFromPage(reader, i).split("\n");
                for (String l : lines)
                {
                    Matcher m1 = r1.matcher(l);
                    Matcher m2 = r2.matcher(l);
                    Matcher m3 = r3.matcher(l);

                    if (m1.find())
                        aux_dia = m1.group(0);
                    if (m2.find())
                        alm = m2.group(0);
                    if (m3.find())
                        jnt = m3.group(0);
                    if (aux_dia != null && alm != null && jnt != null)
                    {
                        ementa.put(aux_dia, new Prato(aux_dia, alm, jnt));
                        aux_dia = null;
                        alm = null;
                        jnt = null;
                    }
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        // Saving JSON with pdf content
        Gson gson = new Gson();

        try
        {
            File x = new File(filename.substring(0, filename.indexOf('.')) + ".json");
            x.createNewFile();
            FileWriter ol = new FileWriter(x);
            JSONObject jsonObject = new JSONObject();

            for (Object o : ementa.entrySet())
            {
                Map.Entry pairs = (Map.Entry) o;
                jsonObject.put((String) pairs.getKey(), gson.toJson(pairs.getValue()));
            }
            ol.write(String.valueOf(jsonObject));
            ol.close();
        } catch (JSONException | IOException e)
        {
            e.printStackTrace();
        }
        File pdf = new File(filename);
        pdf.delete();
    }

    public static HashMap<String, Prato> jsonLoad(String param)
    {
        HashMap<String, Prato> ementa = new HashMap<>();
        byte[] data = null;

        try
        {
            File file = new File(param);
            FileInputStream fis = new FileInputStream(file);
            data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e1)
        {
            e1.printStackTrace();
        }

        try
        {
            JSONObject jObject = new JSONObject(String.valueOf(new String(data, "UTF-8")));
            Iterator<?> keys = jObject.keys();

            while (keys.hasNext())
            {
                String key = (String) keys.next();
                Gson gson = new Gson();
                Prato prato = gson.fromJson(jObject.getString(key), Prato.class);
                ementa.put(key, prato);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return ementa;
    }


}
