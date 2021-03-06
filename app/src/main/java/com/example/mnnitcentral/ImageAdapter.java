package com.example.mnnitcentral;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
//    private final String name=
//    private final String phone =
    private Context mcontext;
    private List<uploadimage> muploads;
    public ImageAdapter(Context context,List<uploadimage> uploads){

        mcontext = context;
        muploads = uploads;

    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.image_upload,parent,false);

        return new ImageViewHolder(v);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {



        uploadimage upload = muploads.get(position);
        holder.textViewname.setText(upload.getName());
        holder.textViewdesc.setText(upload.getDesc());
        holder.textViewcontact.setText("Contact "+upload.get_name()+" on "+upload.get_phone());

//        holder.textViewcontact.setText("Please Contact "+name+" at "+phone);
        Picasso.with(mcontext).load(upload.getImageurl()).placeholder(R.mipmap.ic_launcher_round).fit().centerInside().into(holder.imageView);





    }

    @Override
    public int getItemCount() {
        return muploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder  {

        public TextView textViewname,textViewdesc,textViewcontact;
        public ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewname= itemView.findViewById(R.id.item_name);
            imageView=itemView.findViewById(R.id.display);
            textViewdesc= itemView.findViewById(R.id.item_desc);
            textViewcontact=itemView.findViewById(R.id.contact);



        }
    }


}
