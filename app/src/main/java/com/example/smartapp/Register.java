package com.example.smartapp;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {
    EditText etname;
    EditText userid;
    EditText ephone;

    Button btnRegister;
    ProgressBar progressBar;
    FirebaseDatabase mDatabase;
    DatabaseReference mDatabaseReference ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etname = (EditText) findViewById(R.id.editName);
        userid = (EditText) findViewById(R.id.editid);
        ephone = (EditText) findViewById(R.id.editphone);
        btnRegister = (Button) findViewById(R.id.btnsignup);



        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                String name = etname.getText().toString();

                String UserId = userid.getText().toString();
                String Phone = ephone.getText().toString();

                if (name.isEmpty()) {
                    etname.setError("Please Enter Your Name");
                    etname.requestFocus();
                } else if (UserId.isEmpty()) {
                    userid.setError("PLEASE Enter Your ID#");
                    userid.requestFocus();
                } else if (Phone.isEmpty()) {
                    userid.setError("PLEASE Enter Your Phone");
                    userid.requestFocus();
                } else if (name.isEmpty() && UserId.isEmpty() && Phone.isEmpty()) {

                    Toast.makeText(Register.this, " BOTH FIELDS ARE EMPTY!!", Toast.LENGTH_SHORT).show();

                } else if (!(name.isEmpty() && !UserId.isEmpty() && !Phone.isEmpty()) ) {


                    User user = new User(name, Integer.parseInt(UserId) ,Integer.parseInt(Phone));
                    mDatabase = FirebaseDatabase.getInstance();
                    mDatabaseReference = mDatabase.getReference();
                    DatabaseReference users = mDatabaseReference.child("users");


                    users.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            if (snapshot.child(UserId).exists() ||  snapshot.child(UserId).child(Phone).exists()) {
                                AlertDialog alertDialog = new AlertDialog.Builder(Register.this)

                                        .setTitle("Warn")

                                        .setMessage("This Account is exist try again")
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {

                                                Toast.makeText(getApplicationContext(),"try again",Toast.LENGTH_LONG).show();
                                            }
                                        })
                                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {

                                                Toast.makeText(getApplicationContext(),"Nothing Happened",Toast.LENGTH_LONG).show();
                                            }
                                        })
                                        .show();




                            }else{
                                mDatabaseReference.child("users").child(UserId).setValue(user);
                                AlertDialog alertDialog = new AlertDialog.Builder(Register.this)

                                        .setTitle("Warn")

                                        .setMessage("You were Add Succsefully")
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {

                                                Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_LONG).show();

                                            }
                                        })
                                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {

                                                Toast.makeText(getApplicationContext(),"Nothing Happened",Toast.LENGTH_LONG).show();
                                            }
                                        })
                                        .show();

                                String value=Phone;
                                Toast.makeText(getApplicationContext(),"Done" ,Toast.LENGTH_LONG).show();
                                Intent i = new Intent(Register.this, MainActivity.class);
                                i.putExtra("Phone",value);
                                startActivity(i);


                            }
                        }


                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    etname.setText("");
                    userid.setText("");
                    ephone.setText("");


                }


            }
        });


    }



}
