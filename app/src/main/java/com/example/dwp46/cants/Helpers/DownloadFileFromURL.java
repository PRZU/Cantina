package com.example.dwp46.cants.Helpers;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by cruz on 03/02/2018.
 */

public class DownloadFileFromURL extends AsyncTask<String, String, String>
{

    /**
     * Para permitir o download do ficheiro e necessario confiar num certificado.
     * Esta funcao faz com que todos os certificados sejam aceites.
     * Inseguro!
     * From:
     * https://stackoverflow.com/questions/10135074/download-file-from-https-server-using-java
     */
    private void trust_all()
    {
        TrustManager[] trustAllCerts = new TrustManager[]
                {
                        new X509TrustManager()
                        {
                            public java.security.cert.X509Certificate[] getAcceptedIssuers()
                            {
                                return null;
                            }

                            public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
                            {
                            }

                            public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
                            {
                            }
                        }
                };
        try
        {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception ignored)
        {
        }
    }

    @Override
    protected String doInBackground(String... f_url)
    {
        int count;
        trust_all();

        String name = f_url[0].substring(f_url[0].lastIndexOf('/'));
        System.out.println(name);

        File f = new File(android.os.Environment.getExternalStorageDirectory(), File.separator + "/Cantina/");
        f.mkdirs();

        try
        {
            URL url = new URL(f_url[0]);
            URLConnection connection = url.openConnection();
            connection.connect();

            InputStream input = new BufferedInputStream(url.openStream(), 8192);

            OutputStream output = new FileOutputStream(Environment
                    .getExternalStorageDirectory().toString() + "/Cantina" + name);

            System.out.println(Environment
                    .getExternalStorageDirectory().toString());
            byte data[] = new byte[1024];

            while ((count = input.read(data)) != -1)
            {
                output.write(data, 0, count);
            }

            output.flush();
            output.close();
            input.close();

        } catch (Exception e)
        {
            Log.e("Error: ", e.getMessage());
        }
        return Environment.getExternalStorageDirectory().toString() + "/Cantina" + name;
    }
}


