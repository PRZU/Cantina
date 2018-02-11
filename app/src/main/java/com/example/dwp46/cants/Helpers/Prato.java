package com.example.dwp46.cants.Helpers;

import com.example.dwp46.cants.Helpers.TimeMapper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cruz on 04/02/2018.
 */

public class Prato
{
    private String almoco;
    private String jantar;

    private String almoco_prato;
    private String jantar_prato;
    private String almoco_sopa;
    private String jantar_sopa;

    private int dia;
    private int mes;



    public Prato(String dia, String almoco, String jantar) {
        String pattern_sep = "[a-zA-Z-çãõáàÁÀéèÉÈíìÍÌóòÓÒúùÚÙâÂêÊôÔ ]+";
        String pattern_dia = "[^ ]+";
        Pattern r = Pattern.compile(pattern_sep);
        Pattern d = Pattern.compile(pattern_dia);

        System.out.println(almoco + " " +jantar);
        Matcher m1 = r.matcher(almoco);
        Matcher m2 = r.matcher(jantar);
        Matcher m3 = d.matcher(dia);
        if (m1.find())
            this.almoco_sopa = m1.group(0);
        //System.out.println("#" + m1.group(0));
        if (m1.find())
            this.almoco_prato = m1.group(0);
        //System.out.println("#" + m1.group(0));

        if (m2.find())
            this.jantar_sopa = m2.group(0);
        //System.out.println("#" + m2.group(0));
        if (m2.find())
            this.jantar_prato = m2.group(0);
        //System.out.println("#" + m2.group(0));

        if (m3.find())
            this.dia = Integer.parseInt(m3.group(0));
        //System.out.println("#" + m3.group(0));
        if (m3.find())
            this.mes = TimeMapper.valueOf(m3.group()).calculate_i();
    }

    @Override
    public String toString() {
        return "Prato{almoco_prato='" + almoco_prato + '\'' +
                ", jantar_prato='" + jantar_prato + '\'' +
                ", almoco_sopa='" + almoco_sopa + '\'' +
                ", jantar_sopa='" + jantar_sopa + '\'' +
                ", dia=" + dia +
                ", mes=" + mes +
                '}';
    }
}
