package com.example.smartapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VerifySecureCode extends AppCompatActivity {
    private Button btnverify;
    private EditText editcode;
    private String userid;
    FirebaseDatabase mDatabase;
    DatabaseReference mDatabaseReference ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_secure_code);
        // للوصول للبتن
        btnverify = (Button) findViewById(R.id.BtnVerify);
        //للوصول للفيلد
        editcode = (EditText) findViewById(R.id.code);
// جبنا اليوزر
        Intent intent = getIntent();
        userid  = intent.getStringExtra("id");

//للوصول للداتابيز
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference();
         //لمايضغط على البتن
        btnverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //التاكد من انو الفيلد مش فارغ
                String code =editcode.getText().toString();
                if(code.isEmpty()){
                    editcode.setError("Please Enter Your SecureCode");
                }
                else {
                    mDatabaseReference = mDatabase.getReference();
                    DatabaseReference users = mDatabaseReference.child("users");
                    users.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {

                                String value = snapshot.child(userid).child("SecureCode").getValue(String.class);

                                 if(value.equals(code)) {
                                    Intent i = new Intent(VerifySecureCode.this, Home.class);
                                    startActivity(i);
                                }
                                 else {
                                     AlertDialog alertDialog = new AlertDialog.Builder(VerifySecureCode.this)

                                             .setTitle("Warn")

                                             .setMessage("Invalid  Security Code")
                                             .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                 @Override
                                                 public void onClick(DialogInterface dialogInterface, int i) {
                                                     editcode.setText("");

                                                 }
                                             })

                                             .show();
                                 }




                        }


                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                    editcode.setText("");


                }

            }
        });
    }
}