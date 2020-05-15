package com.example.mnnitcentral;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class nextactivity extends AppCompatActivity {

    TextView welcome;
    Button lost;

    private long backpress;
    private Toast backtoast;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_nextactivity);
        final String name=getIntent().getStringExtra("name");

        welcome=findViewById(R.id.textView);
        welcome.setText("Hello "+getIntent().getStringExtra("name")+"\nWelcome to Help Out \n How can we help you ?");

        lost=findViewById(R.id.lost);
        lost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(nextactivity.this,lost_0.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {

        if (backpress +2000>System.currentTimeMillis()){
            backtoast.cancel();
            super.onBackPressed();
            return;
        }
        else {
            backtoast= Toast.makeText(this, "press back again to exit", Toast.LENGTH_SHORT);
            backtoast.show();
        }
        backpress = System.currentTimeMillis();
    }
}
