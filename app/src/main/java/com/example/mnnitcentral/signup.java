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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class signup extends AppCompatActivity {
    Button b1,b2;
    ProgressBar progressBar;
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
        progressBar=findViewById(R.id.progressBar3);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setAlpha(1);
                b1.setAlpha(0);
                b2.setAlpha(0);

                database = FirebaseDatabase.getInstance();
                reff = database.getReference("users");
//                reff=FirebaseDatabase.getInstance().getReference("users");


                String username= user.getText().toString();
                String fullname= name.getText().toString();
                String mail = email.getText().toString();
                String phone = number.getText().toString();
                String passwd = pass.getText().toString();


                if (username.isEmpty() || fullname.isEmpty() || mail.isEmpty()||phone.isEmpty()||passwd.isEmpty() ){
                    progressBar.setAlpha(0);
                    b1.setAlpha(1);
                    b2.setAlpha(1);
                    Toast.makeText(signup.this, "PLEASE FILL ALL DETAILS !", Toast.LENGTH_SHORT).show();
                }


                else {
                userslist users = new userslist(username,fullname,mail,phone,passwd);



              reff.child(phone).setValue(users);

                Toast.makeText(signup.this, "Registration Successfull ...", Toast.LENGTH_SHORT).show();


                Intent next=new Intent(signup.this,nextactivity.class);
                    next.putExtra("name",fullname);
                    next.putExtra("phone",phone);
                    next.putExtra("mail",mail);
                startActivity(next);
                finish();


        }}



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
