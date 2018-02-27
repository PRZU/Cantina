package com.example.dwp46.cants.Helpers;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cruz on 04/02/2018.
 */

public class Tk_Prato implements Serializable
{
    private String prato_carne;
    private String prato_peixe;
    private int preco_carne;
    private int preco_peixe;

    private int dia;
    private int mes;


    Tk_Prato(String dia, String almoco, String jantar)
    {
        String pattern_sep = "[A-ZÁÀÉÈÍÌÓÒÚÙ][a-zA-Z-çãõáàÁÀéèÉÈíìÍÌóòÓÒúùÚÙâÂêÊôÔ ]+";
        String pattern_dia = "[^ ]+";
        Pattern r = Pattern.compile(pattern_sep);
        Pattern d = Pattern.compile(pattern_dia);

        Matcher m1 = r.matcher(almoco);
        Matcher m2 = r.matcher(jantar);
        Matcher m3 = d.matcher(dia);
        if (m1.find())
            this.prato_carne = m1.group(0);

        if (m3.find())
            this.dia = Integer.parseInt(m3.group(0));
        if (m3.find())
            this.mes = TimeMapper.valueOf(m3.group()).calculate_i();
    }

    Tk_Prato(JSONObject jsonObject)
    {
        ;

    }


}
