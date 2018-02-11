package com.example.dwp46.cants.Helpers;

import android.annotation.SuppressLint;
import android.os.Environment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
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

public class PdfParser {

    public static void parse_pdf(String filename) {

        PdfReader reader = null;
        JSONObject ementa_json;
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

        try {
            reader = new PdfReader(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            try {
                String[] lines = PdfTextExtractor.getTextFromPage(reader, i).split("\n");
                for (String l : lines) {
                    Matcher m1 = r1.matcher(l);
                    Matcher m2 = r2.matcher(l);
                    Matcher m3 = r3.matcher(l);


                    if (m1.find())
                       aux_dia = m1.group(0);
                        //System.out.println("Found value dia: " + m1.group(0));
                    if (m2.find())
                        alm = m2.group(0);
                        //System.out.println("Found value almoco: " + m2.group(0));

                    if (m3.find()) {
                        jnt = m3.group(0);
                        //System.out.println("Found value jantar: " + m3.group(0));
                    if (aux_dia != null && alm != null && jnt != null)
                        ementa.put(aux_dia, new Prato(aux_dia, alm, jnt));
                    aux_dia = null; alm = null; jnt = null;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            ObjectMapper mapper = new ObjectMapper();


            File x = new File(Environment.getExternalStorageDirectory()
                    , "/" + "o.txt");
            FileWriter ol = new FileWriter(x);
            x.createNewFile();




            // TODO: read and write to json
            JSONObject jsonObject = new JSONObject();
            Iterator it = ementa.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pairs = (Map.Entry) it.next();

                jsonObject.put((String) pairs.getKey(), pairs.getValue());
            }


            System.out.println(jsonObject);

                ol.write(String.valueOf(jsonObject));
                ol.close();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
    }

         public static void main (String[]args){
            parse_pdf("EmentaTC_Fev18.pdf");
        }

    }
