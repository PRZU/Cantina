package com.example.dwp46.cants;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dwp46.cants.Helpers.TimeConvertion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmentaAdapter extends ArrayAdapter<String>
{
    private final Context context;
    private final String[] values;

    EmentaAdapter(Context context, String[] values)
    {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row, parent, false);


        TextView textView = rowView.findViewById(R.id.almoco);
        TextView textView1 = rowView.findViewById(R.id.jantar);
        TextView textView2 = rowView.findViewById(R.id.dia);

        String reg_ap = "almoco_prato=\'(.*?)'";
        String reg_jp = "jantar_prato=\'(.*?)'";

        String reg_as = "almoco_sopa=\'(.*?)'";
        String reg_js = "jantar_sopa=\'(.*?)'";

        String reg_d = "dia=([\\d]+)";
        String reg_m = "mes=([\\d]+)";

        Pattern r1 = Pattern.compile(reg_ap);
        Pattern r2 = Pattern.compile(reg_jp);
        Pattern r3 = Pattern.compile(reg_as);
        Pattern r4 = Pattern.compile(reg_js);
        Pattern r5 = Pattern.compile(reg_d);
        Pattern r6 = Pattern.compile(reg_m);

        Matcher m1 = r1.matcher(values[position]);
        Matcher m2 = r2.matcher(values[position]);
        Matcher m3 = r3.matcher(values[position]);
        Matcher m4 = r4.matcher(values[position]);
        Matcher m5 = r5.matcher(values[position]);
        Matcher m6 = r6.matcher(values[position]);

        String almoco_p = "null";
        String jantar_p = "null";
        String almoco_s = "null";
        String jantar_s = "null";
        String dia = "null";
        String mes = "null";
        if (m1.find())
            almoco_p = m1.group(1);
        if (m2.find())
            jantar_p = m2.group(1);
        if (m3.find())
            almoco_s = m3.group(1);
        if (m4.find())
            jantar_s = m4.group(1);
        if (m5.find())
            dia = m5.group(1);
        if (m6.find())
            mes = m6.group(1);


        textView.setText("Almoço\nPrato: " + almoco_p + "\nSopa: " + almoco_s);
        textView1.setText("Jantar\nPrato: " + jantar_p + "\nSopa: " + jantar_s);


        textView2.setText(dia + " de " + TimeConvertion.calculate_s(Integer.parseInt(mes) - 1));
        return rowView;
    }
}