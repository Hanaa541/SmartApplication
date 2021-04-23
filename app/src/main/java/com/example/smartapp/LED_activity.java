package com.example.smartapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

public class LED_activity extends AppCompatActivity {
    private static Button on1,off1 ;
    private static ImageView LampImage ;
    SeekBar seekBar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led_activity);

        on1=(Button) findViewById(R.id.but1on);
        off1=(Button) findViewById(R.id.but1off);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        LampImage = (ImageView) findViewById(R.id.image);

        seekBar.setMax(255);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Slider process1 = new Slider((Integer.toString(progress)));
                process1.execute();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        on1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LampImage.setBackground(ContextCompat.getDrawable(LED_activity.this, R.drawable.lampon));
                on1 process1 = new on1();
                process1.execute();

            }
        });



        off1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LampImage.setBackground(ContextCompat.getDrawable(LED_activity.this, R.drawable.lampoff));
                off1 process1 = new off1();
                process1.execute();

            }
        });



    }
}
