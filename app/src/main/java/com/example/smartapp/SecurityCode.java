package com.example.smartapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Random;

public class SecurityCode extends AppCompatActivity {
    private TextView textView;
    FirebaseDatabase mDatabase;
    DatabaseReference mDatabaseReference ;
    private Button btncontinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_code);
        btncontinue = (Button) findViewById(R.id.btncont);
        textView = (TextView) findViewById(R.id.code);
        String UserCode = generateRandomcode(4);
        textView.setText(UserCode);



        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference();
// الواجهة الي جاي منها
        Intent intent = getIntent();
        //المعلومات الي في الواجهة عن طريق الكي
        String id = intent.getStringExtra("id");



        HashMap<String, Object> values = new HashMap<>();
        values.put("SecureCode", UserCode);

        mDatabaseReference.child("users").child(id).updateChildren(values);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent page = new Intent(SecurityCode.this, MainActivity.class);

                startActivity(page);
            }
        });




    }
    public static String generateRandomcode(int len)
    {
        // ASCII range – alphanumeric (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        // each iteration of the loop randomly chooses a character from the given
        // ASCII range and appends it to the `StringBuilder` instance

        for (int i = 0; i < len; i++)
        {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        return sb.toString();
    }
}