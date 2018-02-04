package com.example.dwp46.cants.Helpers;

import java.util.Calendar;

/**
 * Created by cruz on 04/02/2018.
 */

public class TimeMapper
{
    private String[] meses = {"Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Set", "Out", "Nov", "Dec"};


    private String getAno()
    {
        return String.valueOf(Calendar.getInstance().get(Calendar.YEAR)).substring(2);
    }
    private String getMes()
    {
        return this.meses[Calendar.MONTH - 1];
    }

    public int getDia()
    {
        return Calendar.DAY_OF_MONTH;
    }

    public String getMesAno()
    {
        return this.meses[Calendar.MONTH - 1] + getAno();
    }
}
