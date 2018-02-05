package com.example.dwp46.cants.Helpers;

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



    public Prato() {
        this.almoco = almoco;
        this.jantar = jantar;
        this.dia = dia;
        this.mes = mes;
    }

    public String getAlmoco() {
        return almoco;
    }

    public void setAlmoco(String almoco) {
        this.almoco = almoco;
    }

    public String getJantar() {
        return jantar;
    }

    public void setJantar(String jantar) {
        this.jantar = jantar;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }
}
