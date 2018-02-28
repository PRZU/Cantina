package com.example.dwp46.cants.Helpers;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;


/**
 * Created by cruz on 03/02/2018.
 */

public class DownloadFileFromURL extends AsyncTask<String, String, String>
{
    @SuppressLint("NewApi")
    @Override
    public String doInBackground(String... f_url)
    {
        int count;
        String name = f_url[0].substring(f_url[0].lastIndexOf('/'));

        File f = new File(android.os.Environment.getExternalStorageDirectory(), File.separator + "Cantina/");

        f.mkdirs();


        try
        {
            URL url = new URL(f_url[0]);
            URLConnection connection = url.openConnection();
            connection.connect();

            InputStream input = new BufferedInputStream(url.openStream(), 8192);

            OutputStream output = new FileOutputStream(Environment
                    .getExternalStorageDirectory().toString() + "/Cantina" + name);

            byte data[] = new byte[1024];

            while ((count = input.read(data)) != -1)
            {
                output.write(data, 0, count);
            }

            output.flush();
            output.close();
            input.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return Environment.getExternalStorageDirectory().toString() + "/Cantina" + name;
    }


}


