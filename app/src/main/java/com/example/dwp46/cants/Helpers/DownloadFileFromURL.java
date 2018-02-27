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

    /**
     * Para permitir o download do ficheiro e necessario confiar num certificado.
     * Esta funcao faz com que todos os certificados sejam aceites.
     * Inseguro!
     * From:
     * https://stackoverflow.com/questions/10135074/download-file-from-https-server-using-java
     */

    /**
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

            SSLContext sc = SSLContext.getInstance("SSL");//("TLSv1.2");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());

            NetCipher.getHttpsURLConnection("https://www.sas.uminho.pt").setSSLSocketFactory(sc.getSocketFactory());

        } catch (Exception ignored)
        {
        }
    }

    private void trust()
    {
        CertificateFactory cf = null;
        InputStream caInput = null;       
        Certificate ca = null;
        InputStream is = new ByteArrayInputStream( ("-----BEGIN CERTIFICATE-----\n" +
                "MIIFMjCCBBqgAwIBAgIQD3l6V9kZWCZcnfug4jyHSDANBgkqhkiG9w0BAQsFADBk\n" +
                "MQswCQYDVQQGEwJOTDEWMBQGA1UECBMNTm9vcmQtSG9sbGFuZDESMBAGA1UEBxMJ\n" +
                "QW1zdGVyZGFtMQ8wDQYDVQQKEwZURVJFTkExGDAWBgNVBAMTD1RFUkVOQSBTU0wg\n" +
                "Q0EgMzAeFw0xNjA3MTIwMDAwMDBaFw0xOTA3MTcxMjAwMDBaMHgxCzAJBgNVBAYT\n" +
                "AlBUMQ4wDAYDVQQIEwVCcmFnYTEOMAwGA1UEBxMFQnJhZ2ExHjAcBgNVBAoTFVVu\n" +
                "aXZlcnNpZGFkZSBkbyBNaW5obzENMAsGA1UECxMEU0NPTTEaMBgGA1UEAxMRd3d3\n" +
                "LnNhcy51bWluaG8ucHQwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCo\n" +
                "Z8NUbtptHoIqODE9QjClELcKMZHKKxUQx9TIf7n/NUmLf9vo1GrmrbmPdDnvtJg8\n" +
                "2U7jX6/3nAdxtl28lHPS3Ry3IPg0CGF9JSVzOrTLS/Ntwrs41dnSgv8Mz6T6IV/9\n" +
                "TbOk3DNqgP1W71ClGEe0gx1d8fse9J5TBAfCSIp/szqD1DGr56Xhcq4/1ATCnWH1\n" +
                "RVwkn1UCdjB3yUa4x/OQVLCtW02v+IZSxnSpY3u/62w11IBO/9tLygMHKW4DpkB9\n" +
                "H42D/SKyZV6sN4ZXqfo+8Jcw/l93biVTLS8wJFJ+IQkTK3cwA5HIpBSJKm6l2Nvw\n" +
                "Yw9T7JFEh2bucUVsGbXBAgMBAAGjggHKMIIBxjAfBgNVHSMEGDAWgBRn/YggFCeY\n" +
                "xwnSJRm76VERY3VQYjAdBgNVHQ4EFgQUr1z+NnlP757eMTJuSk8bh6+UtNEwHAYD\n" +
                "VR0RBBUwE4IRd3d3LnNhcy51bWluaG8ucHQwDgYDVR0PAQH/BAQDAgWgMB0GA1Ud\n" +
                "JQQWMBQGCCsGAQUFBwMBBggrBgEFBQcDAjBrBgNVHR8EZDBiMC+gLaArhilodHRw\n" +
                "Oi8vY3JsMy5kaWdpY2VydC5jb20vVEVSRU5BU1NMQ0EzLmNybDAvoC2gK4YpaHR0\n" +
                "cDovL2NybDQuZGlnaWNlcnQuY29tL1RFUkVOQVNTTENBMy5jcmwwTAYDVR0gBEUw\n" +
                "QzA3BglghkgBhv1sAQEwKjAoBggrBgEFBQcCARYcaHR0cHM6Ly93d3cuZGlnaWNl\n" +
                "cnQuY29tL0NQUzAIBgZngQwBAgIwbgYIKwYBBQUHAQEEYjBgMCQGCCsGAQUFBzAB\n" +
                "hhhodHRwOi8vb2NzcC5kaWdpY2VydC5jb20wOAYIKwYBBQUHMAKGLGh0dHA6Ly9j\n" +
                "YWNlcnRzLmRpZ2ljZXJ0LmNvbS9URVJFTkFTU0xDQTMuY3J0MAwGA1UdEwEB/wQC\n" +
                "MAAwDQYJKoZIhvcNAQELBQADggEBAAsx++ssiqmwoiLVlUyb26eZd9CeqWsaJ8cD\n" +
                "6JqftU8BT//PyJBXjcu6EkrsTrZhC2H6R/uKK2A14ezTVyzPo1BY9hznf8/mGmsm\n" +
                "OVzvXDqvu24Gyf5t73qk6ek8gcRNIo4j7QdSzwGX0tYhFzOdv4qHnsIpKU5ZUuFm\n" +
                "owdNQ3IiQEG7Dr5YMggtASkmvfFGbPKxsWYWbaIfsbLdTlMEXwHLSbiKoXDlT+9Z\n" +
                "CDLrNEnZrhvN4D4GqyKDZIJNXPvqa8pLjng5ibp2Y5/29PawfDXbE707a/aF4WCW\n" +
                "z6bXIVvHpNFa3HZiDg6ciT4GHUGHj9tMH91XdFgTRIX4duY6EfI=\n" +
                "-----END CERTIFICATE-----").getBytes( Charset.defaultCharset()  ));


        try {
            cf = CertificateFactory.getInstance("X.509");
            //caInput = new BufferedInputStream(new FileInputStream("sas.crt"));
            caInput = new BufferedInputStream(is);
            ca = cf.generateCertificate(caInput);
            System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());

        } catch (CertificateException e) {
            e.printStackTrace();
        } finally {
            try {
                caInput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String keyStoreType = KeyStore.getDefaultType();
        KeyStore keyStore = null;
        SSLContext context = null;
        try {
            keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);
            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

            context = SSLContext.getInstance("TLS");
            context.init(null, tmf.getTrustManagers(), null);
        } catch (KeyManagementException|IOException|NoSuchAlgorithmException|CertificateException|KeyStoreException e) {
            e.printStackTrace();
        }

        URL url = null;
        try {
            url = new URL("https://www.sas.uminho.pt/");
            HttpsURLConnection urlConnection =
                    (HttpsURLConnection)url.openConnection();
            urlConnection.setSSLSocketFactory(context.getSocketFactory());
            InputStream in = urlConnection.getInputStream();
            System.out.println("### " + in.read());
            //copyInputStreamToOutputStream(in, System.out);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
*/
    @SuppressLint("NewApi")
    @Override
    public String doInBackground(String... f_url)
    {
        int count;
       //trust();
        //trust_all();

        String name = f_url[0].substring(f_url[0].lastIndexOf('/'));

        File f = new File(android.os.Environment.getExternalStorageDirectory(),File.separator+"Cantina/");

        boolean b = f.mkdirs();

        if(b)
            System.out.println("Success");
        else
        {
            System.out.println("Nao criou a diretoria no SD");
        }


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

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return Environment.getExternalStorageDirectory().toString() + "/Cantina" + name;
    }


}


