package com.example.mnnitcentral;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class signup extends AppCompatActivity {
    Button b1,b2;
    EditText user,name,email,pass,number;
    FirebaseDatabase database;
    DatabaseReference reff;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
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

                database = FirebaseDatabase.getInstance();
                reff = database.getReference("users");
//                reff=FirebaseDatabase.getInstance().getReference("users");


                String username= user.getText().toString();
                String fullname= name.getText().toString();
                String mail = email.getText().toString();
                String phone = number.getText().toString();
                String passwd = pass.getText().toString();



                userslist users = new userslist(username,fullname,mail,phone,passwd);



              reff.child(mail).setValue(users);

                Toast.makeText(signup.this, "Login Successfull ...", Toast.LENGTH_SHORT).show();

                Intent next=new Intent(signup.this,nextactivity.class);
                startActivity(next);
                recreate();


        }



            }
        );





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
