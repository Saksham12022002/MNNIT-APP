package com.example.mnnitcentral;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class lost_0 extends AppCompatActivity {
    TextView message;
    private static final int imagerequest = 1;
    Button select,upload;
    ImageView imageView;
    EditText description;
    Uri imageurl;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_lost_0);

        message = findViewById(R.id.textView3);
        message.setText("Hey "+getIntent().getStringExtra("name")+"\nWe are sorry for your loss 😔\nBut you can find it here very soon 😃?");
        upload = findViewById(R.id.button);
        select=findViewById(R.id.button2);
        imageView=findViewById(R.id.imageView3);
        description=findViewById(R.id.editText5);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecttheimg();
            }
        });




    }

    private void selecttheimg() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,imagerequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == imagerequest && requestCode == RESULT_OK && data!=null && data.getData() != null){
            imageurl = data.getData();
            imageView.setImageURI(imageurl);
        }
    }
}
