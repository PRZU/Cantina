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
import java.util.TreeMap;
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
        int number_of_pages = 0;
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
            number_of_pages = reader.getNumberOfPages();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        for (int i = 1; i <= number_of_pages; i++)
        {
            try
            {
                String[] lines = PdfTextExtractor.getTextFromPage(reader, i).split("\n");
                for (String l : lines)
                {
                    System.out.println("Line: " + l);
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
                        ementa.put((aux_dia.split(" ")[0]), new Prato(aux_dia, alm, jnt));
                        aux_dia = null;
                        alm = null;
                        jnt = null;
                    }
                }
            }
            catch (IOException e)
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
                System.out.println("whip " + pairs.getKey());
                jsonObject.put(String.valueOf(pairs.getKey()), gson.toJson(pairs.getValue()));
            }
            ol.write(String.valueOf(jsonObject));
            ol.close();
        }
        catch (JSONException | IOException e)
        {
            e.printStackTrace();
        }
        File pdf = new File(filename);
        pdf.delete();
    }

    /*
    private static HashMap<String, Tk_Prato> extractTk(String d, String carnes, String peixes)
    {
        // ...... //
        TakeAwayEmenta ementatk = new TakeAwayEmenta();
        HashMap<String, String> ementa_preco = ementatk.getTakeAwayEmenta();
        Set<String> pratos = ementa_preco.keySet();
        // ...... //

        HashMap<String, Tk_Prato> prts = new HashMap<>();


        //indice em que cada prato foi encontrado na string
        HashMap<Integer, String> carne = new HashMap<>();
        HashMap<Integer, String> peixe = new HashMap<>();
        boolean aux1 = false, aux2 = false;

        for (String prato : pratos)
        {
            System.out.println(prato);
            System.out.println(carnes);
            int temp1 = carnes.indexOf(prato);
            int temp2 = peixes.indexOf(prato);

            if (temp1 != -1)
            {
                carne.put(temp1, prato);
            }
            if (temp2 != -1)
            {
                peixe.put(temp2, prato);
            }
        }

        String[] dias = d.split(" ");

        System.out.println(carne.entrySet());
        return prts;
    }
    */

    /**
     * A forma que o pdf do take-away foi construida nao permite fazer parse do conteúdo de forma eficaz,
     * assim sendo, apenas vão ser mostrados os pratos de carne e peixe, bem como os respectivos preços.
     */
   /* public static void pdfParseSave_tk(String filename)
    {

        PdfReader reader = null;
        int number_of_pages = 0;
        String pattern_dia = "[0-9]+ [0-9]+ ?([0-9]+)? ?([0-9]+)? ?([0-9]+)?";
        Pattern r1 = Pattern.compile(pattern_dia);

        String aux_dia = null;
        String carne;
        String peixe = null;

        boolean carne_isnext = false;
        boolean peixe_isnext = false;

        @SuppressLint("UseSparseArrays") HashMap<String, Tk_Prato> ementa = new HashMap<>();

        try
        {
            reader = new PdfReader(filename);
            number_of_pages = reader.getNumberOfPages();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        for (int i = 1; i <= number_of_pages; i++)
        {
            try
            {
                String[] lines = PdfTextExtractor.getTextFromPage(reader, i).split("\n");
                System.out.println(Arrays.toString(lines));
                for (String l : lines)
                {
                    System.out.println("Line: " + l + "|");
                    Matcher m1 = r1.matcher(l);

                    if (m1.find())
                        aux_dia = m1.group(0);

                    if (carne_isnext)
                    {
                        carne_isnext = false;
                        carne = l;
                        ementa.putAll(extractTk(aux_dia, carne, peixe));
                    }

                    if (peixe_isnext)
                    {
                        peixe_isnext = false;
                        peixe = l;
                    }

                    if (l.equals("Carne "))
                    {
                        carne_isnext = true;
                    }

                    if (l.equals("Pescado"))
                    {
                        peixe_isnext = true;
                    }

                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        // Saving JSON with pdf content
        Gson gson = new Gson();

//            try
//            {
//                File x = new File(filename.substring(0, filename.indexOf('.')) + ".json");
//                x.createNewFile();
//                FileWriter ol = new FileWriter(x);
//                JSONObject jsonObject = new JSONObject();
//
//                for (Object o : ementa.entrySet())
//                {
//                    Map.Entry pairs = (Map.Entry) o;
//                    jsonObject.put((String) pairs.getKey(), gson.toJson(pairs.getValue()));
//                }
//                ol.write(String.valueOf(jsonObject));
//                ol.close();
//            } catch (JSONException | IOException e)
//            {
//                e.printStackTrace();
//            }
//            File pdf = new File(filename);
//            pdf.delete();
    }
    */

    public static TreeMap<Integer, Prato> jsonLoad(String param)
    {
        TreeMap<Integer, Prato> ementa = new TreeMap<>();
        byte[] data = null;

        try
        {
            File file = new File(param);
            FileInputStream fis = new FileInputStream(file);
            data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e1)
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
                ementa.put(Integer.valueOf(key), prato);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return ementa;
    }

    /*
    public static HashMap<String, Prato> jsonLoad_tk(String param)
    {
        return null;
    }
    */
}
