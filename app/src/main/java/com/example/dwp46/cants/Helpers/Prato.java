package com.example.dwp46.cants.Helpers;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by cruz on 04/02/2018.
 */

public class Prato implements Serializable {
    private String almoco_prato;
    private String almoco_sopa;
    private String almoco_OVL;

    private String jantar_prato;
    private String jantar_sopa;
    private String jantar_OVL;

    private int dia;
    private int mes;


    Prato(JSONObject jsonObject) {
        try {
            this.almoco_prato = jsonObject.getString("almoco_prato");
            this.almoco_sopa = jsonObject.getString("almoco_sopa");
            this.almoco_OVL = jsonObject.getString("almoco_OVL");
            this.jantar_prato = jsonObject.getString("jantar_prato");
            this.jantar_sopa = jsonObject.getString("jantar_sopa");
            this.jantar_OVL = jsonObject.getString("jantar_OVL");
            this.dia = Integer.parseInt(String.valueOf(jsonObject.getInt("dia")));
            this.mes = Integer.parseInt(String.valueOf(jsonObject.getInt("mes")));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Prato(int day, int mes, String almoco_prato, String almoco_sopa, String almoco_OVL,
                 String jantar_prato, String jantar_sopa, String jantar_OVL) {
        this.dia = day;
        this.mes = mes;
        this.almoco_prato = almoco_prato;
        this.almoco_sopa = almoco_sopa;
        this.almoco_OVL = almoco_OVL;
        this.jantar_prato = jantar_prato;
        this.jantar_sopa = jantar_sopa;
        this.jantar_OVL = jantar_OVL;
    }

    /*void Prato(String dia, String almoco, String jantar) {
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
    }*/

    public int getDia() {
        return dia;
    }

    @Override
    public String toString() {
        return "Prato{almoco_prato='" + almoco_prato + '\'' +
                ", almoco_sopa='" + almoco_sopa + '\'' +
                ", almoco_OVL='" + almoco_OVL + '\'' +
                ", jantar_prato='" + jantar_prato + '\'' +
                ", jantar_sopa='" + jantar_sopa + '\'' +
                ", jantar_OVL='" + jantar_OVL + '\'' +
                ", dia=" + dia +
                ", mes=" + mes +
                '}';
    }
}
