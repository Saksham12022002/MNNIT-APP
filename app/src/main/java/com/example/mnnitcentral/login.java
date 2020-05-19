package com.example.mnnitcentral;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    Button b1,login,forget;
    private DatabaseReference ref;
    EditText user,pass;
    ProgressBar progressBar;
    private FirebaseAuth mauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);



        login=findViewById(R.id.login);
        forget=findViewById(R.id.forget);
        progressBar=findViewById(R.id.progressBar2);
        user=findViewById(R.id.editText);
        pass=findViewById(R.id.editText2);
        mauth=FirebaseAuth.getInstance();

        b1=findViewById(R.id.signup);
        ref = FirebaseDatabase.getInstance().getReference("users");




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String number = user.getText().toString().trim();
                final String passwd = pass.getText().toString().trim();

                if (number.equals("") ) {
                    Toast.makeText(login.this, "Please Fill All Details !", Toast.LENGTH_SHORT).show();
                }

                if (passwd.equals("")){
                    Toast.makeText(login.this, "Password Should Contain Some Characters", Toast.LENGTH_SHORT).show();
                }

                else {

                    progressBar.setAlpha(1);
                    login.setAlpha(0);
                    forget.setAlpha(0);
                    b1.setAlpha(0);
                    Query check = ref.orderByChild("number").equalTo(number);

                    check.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if (dataSnapshot.exists()) {
                                String storedpasswd = (dataSnapshot.child(number).child("passwd").getValue(String.class));
                                String name = (dataSnapshot.child(number).child("fullname").getValue(String.class));

                                String phone = (dataSnapshot.child(number).child("number").getValue(String.class));
                                String mail = (dataSnapshot.child(number).child("email").getValue(String.class));
                                if (storedpasswd.equals(passwd)) {
                                    Toast.makeText(login.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(login.this, nextactivity.class);
                                    intent.putExtra("name", name);
                                    intent.putExtra("phone", phone);
                                    intent.putExtra("passwd", storedpasswd);
                                    intent.putExtra("mail", mail);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    progressBar.setAlpha(0);
                                    login.setAlpha(1);
                                    forget.setAlpha(1);
                                    b1.setAlpha(1);
                                    Toast.makeText(login.this, "Wrong Password entered.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                progressBar.setAlpha(0);
                                login.setAlpha(1);
                                forget.setAlpha(1);
                                b1.setAlpha(1);
                                Toast.makeText(login.this, "User don't exists !   Register Please ...", Toast.LENGTH_SHORT).show();
                            }
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            progressBar.setAlpha(0);
                            login.setAlpha(1);
                            forget.setAlpha(1);
                            b1.setAlpha(1);
                        }
                    });

                }
            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(login.this,signup.class);
                startActivity(i);
                finish();

            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String number=user.getText().toString().trim();
                final String passwd = pass.getText().toString().trim();
                Query check =ref.orderByChild("number").equalTo(number);

                check.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.exists()) {
                            String storedpasswd = (dataSnapshot.child(number).child("passwd").getValue(String.class));
                            String name = (dataSnapshot.child(number).child("fullname").getValue(String.class));

                            String phone = (dataSnapshot.child(number).child("number").getValue(String.class));
                            String mail = (dataSnapshot.child(number).child("email").getValue(String.class));

                            Intent intent = new Intent(login.this, forget_pass.class);
                            intent.putExtra("name", name);
                            intent.putExtra("phone", phone);
                            intent.putExtra("mail", mail);
                            startActivity(intent);


                        } else {
                            Toast.makeText(login.this, "User don't exists !   Register Please ...", Toast.LENGTH_SHORT).show();
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });

    }
}
