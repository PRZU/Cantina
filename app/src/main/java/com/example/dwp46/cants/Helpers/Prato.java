package com.example.dwp46.cants.Helpers;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cruz on 04/02/2018.
 */

public class Prato implements Serializable
{
    private String almoco_prato;
    private String jantar_prato;
    private String almoco_sopa;
    private String jantar_sopa;

    private int dia;
    private int mes;


    Prato(String dia, String almoco, String jantar)
    {
        String pattern_sep = "[A-ZÁÀÉÈÍÌÓÒÚÙ][a-zA-Z-çãõáàÁÀéèÉÈíìÍÌóòÓÒúùÚÙâÂêÊôÔ ]+";
        String pattern_dia = "[^ ]+";
        Pattern r = Pattern.compile(pattern_sep);
        Pattern d = Pattern.compile(pattern_dia);

        Matcher m1 = r.matcher(almoco);
        Matcher m2 = r.matcher(jantar);
        Matcher m3 = d.matcher(dia);
        if (m1.find())
            this.almoco_sopa = m1.group(0);
        if (m1.find())
            this.almoco_prato = m1.group(0);

        if (m2.find())
            this.jantar_sopa = m2.group(0);
        if (m2.find())
            this.jantar_prato = m2.group(0);

        if (m3.find())
            this.dia = Integer.parseInt(m3.group(0));
        if (m3.find())
            this.mes = TimeMapper.valueOf(m3.group()).calculate_i();
    }

    Prato(JSONObject jsonObject)
    {
        try
        {
            this.almoco_prato = jsonObject.getString("almoco_prato");
            this.almoco_prato = jsonObject.getString("almoco_sopa");
            this.almoco_prato = jsonObject.getString("jantar_prato");
            this.almoco_prato = jsonObject.getString("jantar_sopa");
            this.almoco_prato = String.valueOf(jsonObject.getInt("dia"));
            this.almoco_prato = String.valueOf(jsonObject.getInt("mes"));

        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public String toString()
    {
        return "Prato{almoco_prato='" + almoco_prato + '\'' +
                ", jantar_prato='" + jantar_prato + '\'' +
                ", almoco_sopa='" + almoco_sopa + '\'' +
                ", jantar_sopa='" + jantar_sopa + '\'' +
                ", dia=" + dia +
                ", mes=" + mes +
                '}';
    }
}
