package com.example.mnnitcentral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Lost_nd_found extends AppCompatActivity {

    ImageView lost,found;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_nd_found);

        lost=findViewById(R.id.imageView5);
        found = findViewById(R.id.imageView7);
        final String name=getIntent().getStringExtra("name");


        lost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Lost_nd_found.this,all_lost_items.class);
                intent.putExtra("name",name);
                intent.putExtra("phone",getIntent().getStringExtra("phone"));
                intent.putExtra("mail",getIntent().getStringExtra("mail"));
                startActivity(intent);
            }
        });

        found.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Lost_nd_found.this,all_found_items.class);
                intent.putExtra("name",name);
                intent.putExtra("phone",getIntent().getStringExtra("phone"));
                intent.putExtra("mail",getIntent().getStringExtra("mail"));
                startActivity(intent);

            }
        });




    }
}
