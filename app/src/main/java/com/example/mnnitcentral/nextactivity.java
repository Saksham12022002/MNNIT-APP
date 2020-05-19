package com.example.mnnitcentral;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class nextactivity extends AppCompatActivity {

    TextView welcome;
//    final String name=getIntent().getStringExtra("name");
//    final String phone=getIntent().getStringExtra("phone");



//    public static String _utfValue = "";
//    public static String _phone="";
//
//    void sendValue(){
//        _utfValue  = name;
//        _phone= phone;
//    }
//
//    Button lost;
    ImageView lostndfound,foodmenu;
    ImageAdapter imgadapt;

    private long backpress;
    private Toast backtoast;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_nextactivity);
        final String name=getIntent().getStringExtra("name");

//        Intent intent= new Intent(nextactivity.this,ImageAdapter.class);
//        intent.putExtra("name",name);
//        intent.putExtra("phone",getIntent().getStringExtra("phone"));
//        intent.putExtra("mail",getIntent().getStringExtra("mail"));






        welcome=findViewById(R.id.textView);
        welcome.setText("Hello "+getIntent().getStringExtra("name")+"\nWelcome to Help Out \n How can we help you ?");

//        lost=findViewById(R.id.lost);
        lostndfound=findViewById(R.id.imageView2);
        foodmenu=findViewById(R.id.imageView4);
        foodmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(nextactivity.this,food_menu.class);
                intent.putExtra("name",name);
                intent.putExtra("phone",getIntent().getStringExtra("phone"));
                intent.putExtra("mail",getIntent().getStringExtra("mail"));
                startActivity(intent);
            }
        });

        lostndfound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(nextactivity.this,Lost_nd_found.class);
                intent.putExtra("name",name);
                intent.putExtra("phone",getIntent().getStringExtra("phone"));
                intent.putExtra("mail",getIntent().getStringExtra("mail"));
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
