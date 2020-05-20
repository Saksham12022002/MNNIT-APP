package com.example.mnnitcentral;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class my_found_items extends AppCompatActivity implements Image_Adapter_2.OnItemClickListener{

    private RecyclerView recyclerView;
    private FirebaseStorage storage;
    private DatabaseReference databaseReference,mdatabase;
    private ValueEventListener databaselistener;
    private Image_Adapter_2 imageAdapter;
    private List<uploadimage> muploads;
    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_found_items);

        message = findViewById(R.id.messagenoupload);

        recyclerView = findViewById(R.id.recycler_view);
        final String name=getIntent().getStringExtra("name");
        final String phone=getIntent().getStringExtra("phone");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        muploads = new ArrayList<>();
        mdatabase = FirebaseDatabase.getInstance().getReference("uploads").child("found uploads");
        storage = FirebaseStorage.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users")
                .child(phone)
                .child("found uploads");

        databaselistener= databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                    uploadimage upload = postsnapshot.getValue(uploadimage.class);
                    upload.setKey(postsnapshot.getKey());
                    muploads.add(upload);
                }

                if (muploads.size() == 0) {

                    message.setAlpha(1);
                }
//                else{
//
//                }


                imageAdapter = new Image_Adapter_2(my_found_items.this, muploads);

                recyclerView.setAdapter(imageAdapter);

                imageAdapter.setOnItemClickListener(my_found_items.this);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(my_found_items.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, "Click at position " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnEditClick(int position) {
        Toast.makeText(this, "Function In Progress.....", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(int position) {

        uploadimage selectedItem = muploads.get(position);
        final String selectedKey = selectedItem.getKey();
        final StorageReference imageref = storage.getReferenceFromUrl(selectedItem.getImageurl());
        imageref.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                databaseReference.child(selectedKey).removeValue();

                mdatabase.child(selectedKey).removeValue();
                Toast.makeText(my_found_items.this, "Item Deleted ...", Toast.LENGTH_SHORT).show();
                recreate();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(my_found_items.this,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseReference.removeEventListener(databaselistener);
    }
}
