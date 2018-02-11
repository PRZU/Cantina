package com.example.dwp46.cants.Helpers;


import java.util.Calendar;

/**
 * Created by cruz on 04/02/2018.
 */

public enum TimeMapper
{
    Janeiro,
    Fevereiro,
    Março,
    Abril,
    Maio,
    Junho,
    Julho,
    Agosto,
    Setembro,
    Outubro,
    Novembro,
    Dezembro;

    public int calculate_i()
    {
        switch (this) {
            case Janeiro: return 1;
            case Fevereiro: return 2;
            case Março: return 3;
            case Abril: return 4;
            case Maio: return 5;
            case Junho: return 6;
            case Julho: return 7;
            case Agosto: return 8;
            case Setembro: return 9;
            case Outubro: return 10;
            case Novembro: return 11;
            case Dezembro: return 12;
            default:
                throw new AssertionError("Unknown operations " + this);
        }
    }

}