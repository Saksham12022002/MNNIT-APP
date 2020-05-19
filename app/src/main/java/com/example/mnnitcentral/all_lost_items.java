package com.example.mnnitcentral;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class all_lost_items extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageAdapter imageAdapter;
    private List<uploadimage> muploads;
    ImageButton uploadnew;
    Button myuploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_lost_items);
        uploadnew = findViewById(R.id.uploadnew);
        myuploads=findViewById(R.id.button3);

        recyclerView = findViewById(R.id.recycler_view);
        final String name=getIntent().getStringExtra("name");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        uploadnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(all_lost_items.this,lost_0.class);
                intent.putExtra("phone",getIntent().getStringExtra("phone"));
                intent.putExtra("mail",getIntent().getStringExtra("mail"));
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
        myuploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(all_lost_items.this,my_lost_items.class);
                intent.putExtra("phone",getIntent().getStringExtra("phone"));
                intent.putExtra("mail",getIntent().getStringExtra("mail"));
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });

        muploads = new ArrayList<>();

        imageAdapter = new ImageAdapter(all_lost_items.this, muploads);
        recyclerView.setAdapter(imageAdapter);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("uploads");



            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    muploads.clear();

                    for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                        uploadimage upload = postsnapshot.getValue(uploadimage.class);
                        muploads.add(upload);
                    }
                        imageAdapter.notifyDataSetChanged();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(all_lost_items.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }











    }



