package com.example.dwp46.cants.Helpers;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBuffer;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by cruz on 03/02/2018.
 */

public class PdfParser {

    public void parse_pdf(String filename)
    {

    PDDocument pdDoc = null;
    COSDocument cosDoc = null;

    String parsedText;
    try
    {
        System.out.println("oignite");
        PDFParser parser = new PDFParser(new RandomAccessFile(new File(filename),"rw"));
        System.out.println("ola");
        parser.parse();
        System.out.println("oile");
        cosDoc = parser.getDocument();
        PDFTextStripper pdfStripper = new PDFTextStripper();
        System.out.println("joad");
        pdDoc = new PDDocument(cosDoc);
        System.out.println("1");

        // é este amigo que está a dar problemas

        parsedText = pdfStripper.getText(pdDoc);


        System.out.println("south");
        System.out.println(parsedText.replaceAll("[^A-Za-z0-9. ]+", ""));
        System.out.println("OMG");
    }
    catch(Exception e)
    {
        e.printStackTrace();
        try {
            if (cosDoc != null)
                cosDoc.close();
            if (pdDoc != null)
                pdDoc.close();
        } catch (Exception e1) {
            e.printStackTrace();
        }

    }
}
}
