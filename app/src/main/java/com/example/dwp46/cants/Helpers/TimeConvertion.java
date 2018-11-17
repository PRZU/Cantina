package com.example.dwp46.cants.Helpers;

import java.util.Calendar;

/**
 * Created by cruz on 10-02-2018.
 */

public class TimeConvertion {

    public static String calculate_s(int mes) {
        switch (mes) {
            case 0:
                return "Janeiro";
            case 1:
                return "Fevereiro";
            case 2:
                return "Março";
            case 3:
                return "Abril";
            case 4:
                return "Maio";
            case 5:
                return "Junho";
            case 6:
                return "Julho";
            case 7:
                return "Agosto";
            case 8:
                return "Setembro";
            case 9:
                return "Outubro";
            case 10:
                return "Novembro";
            case 11:
                return "Dezembro";
            default:
                throw new AssertionError("??");
        }
    }

    private static String getAno() {
        return String.valueOf(Calendar.getInstance().get(Calendar.YEAR)).substring(2);
    }

    public static int getDia() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public static String getMesAno() {
        return calculate_s(Calendar.getInstance().get(Calendar.MONTH)).substring(0, 3) + getAno();
    }

}
