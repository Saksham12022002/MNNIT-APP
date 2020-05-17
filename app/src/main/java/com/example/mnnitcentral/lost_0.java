package com.example.mnnitcentral;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class lost_0 extends AppCompatActivity {
    TextView message;
    private static final int PICK_USER_IMAGE = 1;
    Button select,upload;
    ImageView imageView;
    EditText description,item_name;
    Uri imageurl;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference databaseReference;
    private StorageTask muploadtask;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_lost_0);

        message = findViewById(R.id.textView3);
        item_name=findViewById(R.id.editText6);
        final String phone = getIntent().getStringExtra("phone");



        final String mail = getIntent().getStringExtra("mail");

        message.setText("Hey "+getIntent().getStringExtra("name")+"\nWe are sorry for your loss 😔\nBut you can find it here very soon 😃? "+phone+"  "+mail);
        upload = findViewById(R.id.button);
        String user = getIntent().getStringExtra("mail");
        imageView=findViewById(R.id.imageView3);
        description=findViewById(R.id.editText5);

        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("users");//.child(user);
        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");






        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecttheimg();

            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (muploadtask !=null && muploadtask.isInProgress()){
                    Toast.makeText(lost_0.this, "Please wait while file is bieng uploaded", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    uploadfile();
                }





            }
        });




    }

    private String get_extension(Uri uri){
        ContentResolver conR =getContentResolver();
        MimeTypeMap mime =MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(conR.getType(uri));

    }

    private void uploadfile() {
        if (imageurl != null){
            final String item = item_name.getText().toString().trim();
            final String mail = getIntent().getStringExtra("mail");
            String key = mDatabaseRef.push().getKey();

            StorageReference storageReference = mStorageRef.child(key+"."+get_extension(imageurl));//get_extension(imageurl));
            muploadtask=storageReference.putFile(imageurl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Toast.makeText(lost_0.this, "Upload Successful", Toast.LENGTH_LONG).show();
                    String s1 = description.getText().toString().trim();
                    String s2 = item_name.getText().toString().trim();

                    final String phone = getIntent().getStringExtra("phone");

                    Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                    while (!uriTask.isSuccessful());
                    Uri downloadurl = uriTask.getResult();
                    assert downloadurl != null;
                    uploadimage extra = new uploadimage(s1,s2,downloadurl.toString());
                    String key = mDatabaseRef.push().getKey();
                    assert key != null;
                    databaseReference.child(key).setValue(extra);

//                    uploadimage extras = new uploadimage(s2,s1,taskSnapshot.getUploadSessionUri().toString());
//                    String key = mDatabaseRef.push().getKey();
//                    mDatabaseRef.child(phone).child(key).setValue(extras);
//                    databaseReference.child(key).setValue(extras);
                    Intent intent = new Intent(lost_0.this,all_lost_items.class);
                    intent.putExtra("name",getIntent().getStringExtra("name"));
                    intent.putExtra("phone",getIntent().getStringExtra("phone"));
                    intent.putExtra("mail",getIntent().getStringExtra("mail"));
                    startActivity(intent);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(lost_0.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(lost_0.this, "File is Uploading", Toast.LENGTH_SHORT).show();
                }
            });



        }
        else{
            Toast.makeText(this, "Plesase Select a File ...", Toast.LENGTH_SHORT).show();
        }

    }

    private void selecttheimg() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_USER_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_USER_IMAGE && resultCode == RESULT_OK && data!=null && data.getData() != null){
            imageurl = data.getData();
            imageView.setImageURI(imageurl);
        }
    }
}
