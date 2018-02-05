package com.example.dwp46.cants.Helpers;

import android.annotation.SuppressLint;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cruz on 03/02/2018.
 */

public class PdfParser {

    public static void parse_pdf(String filename) {

        PdfReader reader;
        //JSONObject json = new JSONObject();
        String pattern_dia = "(?<=Dia )(.*)" ;
        String pattern_alm = "(?<=Almoço )(.*)";
        String pattern_jnt = "(?<=Jantar )(.*)";
        Pattern r1 = Pattern.compile(pattern_dia);
        Pattern r2 = Pattern.compile(pattern_alm);
        Pattern r3 = Pattern.compile(pattern_jnt);

        String aux_dia = null;
        String alm;
        String jnt;
        try
        {
            reader = new PdfReader(filename);
            @SuppressLint("UseSparseArrays") HashMap<String, Prato> ementa = new HashMap<>();

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

                        {
                            if (m1.find())
                            {
                                aux_dia = m1.group(0);
                                System.out.println("Found value dia: " + m1.group(0));
                            }
                            if (m2.find())
                            {
                                alm = m2.group(0);
                                System.out.println("Found value almoco: " + m2.group(0));
                            }
                            if (m3.find())
                            {
                                jnt = m3.group(0);
                                System.out.println("Found value jantar: " + m3.group(0));
                            }
                            ementa.put(aux_dia, new Prato());
                        }


                    }



                    //System.out.println(new String(reader.getPageContent(i)), StandardCharsets.UTF_8);
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        parse_pdf("EmentaTC_Fev18.pdf");
    }

}
