package com.example.smartapp;


import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


//الكلاس المسؤول عن عملية اطفاء اللمية
public class off1 extends AsyncTask<Void,Void,String> {
    @Override
    protected String doInBackground(Void... voids) {
        try {
            //192.168.4.1 IP address esp32
            //192.186.4.1/80
            //socket => ip address + port#(80)
            URL url = new URL("http://192.168.4.1/led1off");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
         ;


        }
        catch (MalformedURLException e)
        {

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);

    }
}
