package com.example.smartapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Home extends AppCompatActivity {
ImageView imagelamp ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //الوصول لصورة اللمبة عن طريق الid
        imagelamp =(ImageView) findViewById(R.id.imlamp);

        //لما يضغط اليوزر على الصورة بينفذ هاي الفنكشن بحيث انو يروح على صفحة التحكم بالضوء
        imagelamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // هان بنحضر وين بدنا نروح من واجهة الهوم الي واجهة التحكم باللمبة
                Intent i = new Intent(Home.this ,LED_activity.class);
                startActivity(i);
            }
        });
    }
}