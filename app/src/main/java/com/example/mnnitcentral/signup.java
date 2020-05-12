package com.example.mnnitcentral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {
    Button b1,b2;
    EditText user,name,email,pass,number;
    FirebaseDatabase signup;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        b1=findViewById(R.id.button5);
        b2=findViewById(R.id.register);
        user=findViewById(R.id.username);
        name=findViewById(R.id.fullname);
        email=findViewById(R.id.email);
        number=findViewById(R.id.editText3);
        pass=findViewById(R.id.editText4);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup=FirebaseDatabase.getInstance();
                reference=signup.getReference().child("users");

            }
        });





        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(signup.this,login.class);
                startActivity(i);
                recreate();

            }
        });
    }
}
