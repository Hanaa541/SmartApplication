package com.example.smartapp;

import android.os.AsyncTask;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


//الكلاس المسؤول عن تشغيل اللمبة

public class on1 extends AsyncTask<Void,Void,String> {


    @Override
    protected String doInBackground(Void... voids) {
        try {
//تجهيز الرابط
            URL url = new URL("http://192.168.4.1/led1on");
            //نوع الاتصال http
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//لقراءة الريسبونس
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));



        } catch (MalformedURLException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);



    }





}
