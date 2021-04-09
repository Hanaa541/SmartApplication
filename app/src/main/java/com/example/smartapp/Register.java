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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    EditText etname;
    EditText userid;
    EditText ephone;


    Button btnRegister;

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


                if( !validateId() | !validatePhoneNo() | !validateUsername()) {

                          return;
                }
                else {

                    User user = new User(name, Integer.parseInt(UserId) ,Integer.parseInt(Phone) );
                    mDatabase = FirebaseDatabase.getInstance();
                    mDatabaseReference = mDatabase.getReference();
                    DatabaseReference users = mDatabaseReference.child("users");




                    users.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            if (snapshot.child(UserId).exists() ) {
                                AlertDialog alertDialog = new AlertDialog.Builder(Register.this)

                                        .setTitle("Warn")

                                        .setMessage("This Id is exist try again")
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {

                                                Log.d("TAG", "okk" );
                                            }
                                        })

                                        .show();




                            }else{
                                mDatabaseReference.child("users").child(UserId).setValue(user);
                                AlertDialog alertDialog = new AlertDialog.Builder(Register.this)

                                        .setTitle("Message")

                                        .setMessage("You Are Add Succsefully")
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {

                                                String value=UserId;
//source -dest
                                                Intent page = new Intent(Register.this, SecurityCode.class);
                                                //passing data
                                               page.putExtra("id",value);
                                                startActivity(page);

                                            }
                                        }).show();




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

    private Boolean validateUsername() {
        String val = etname.getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            etname.setError("Please Enter Your Name");
            return false;
        } else if (val.length() >= 15) {
            etname.setError("Username too long");
            return false;
        }else {
            etname.setError(null);
            return true;
        }
    }
    private Boolean validatePhoneNo() {

        String val = ephone.getText().toString();
        if (val.isEmpty()) {
            ephone.setError("Please Enter Your Phone");
            return false;
        } else {
            ephone.setError(null);
            return true;
        }
    }
    private Boolean validateId() {
        String val = userid.getText().toString();

        if (val.isEmpty()) {
            userid.setError("Please Enter Your ID");
            return false;
        } else if(  val.length() != 8){
            userid.setError("Id should 8 digit");
            return false; }

        else {

            userid.setError(null);
            return true;
        }
    }



}
