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



public class Slider extends AsyncTask<Void,Void,String> {
    String value;
    public Slider(String val) {
         value =val;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            String x="5";
            URL url = new URL("http://192.168.4.1/slider?value="+value);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));




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