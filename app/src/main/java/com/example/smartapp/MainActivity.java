package com.example.smartapp;



import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText userid;

    Button loginBtn;
    TextView btnRegister;


    FirebaseDatabase mDatabase;
    DatabaseReference mDatabaseReference ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        userid = (EditText) findViewById(R.id.userid);
        btnRegister = (TextView) findViewById(R.id.createnewac);
        loginBtn = (Button) findViewById(R.id.btnlogin);

        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference();


        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {


                String UserId = userid.getText().toString();

                if (UserId.isEmpty()) {
                    userid.setError("PLEASE Enter Your ID#");
                    userid.requestFocus();
                }
                else if (UserId.length() >8) {
                    userid.setError("Id should 8 digit");
                    userid.requestFocus();
                }

                else  {


                    mDatabaseReference = mDatabase.getReference();
                    DatabaseReference users = mDatabaseReference.child("users");
                    users.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            if (snapshot.child(UserId).exists() ) {
                              //  DataSnapshot childSnapshot = (DataSnapshot) snapshot.getValue();
                                User post = snapshot.child(UserId).getValue(User.class);


                                String value = String.valueOf( post.phone);

                                Intent i = new Intent(MainActivity.this, VerifyPhone.class);

                                i.putExtra("id",UserId);
                                i.putExtra("mobile",value);
                                startActivity(i);


                            }else{
                                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)

                                        .setTitle("Warn")

                                        .setMessage("You Aren't Register please create your account")
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {

                                                startActivity(new Intent(MainActivity.this,Register.class));
                                            }
                                        })
                                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {


                                            }
                                        })
                                        .show();





                            }
                        }


                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                    userid.setText("");



                }


            }
        });





    }


    public void onClick(View v)

    {
        startActivity(new Intent(MainActivity.this,LED_activity.class));
    }
}