package com.example.mnnitcentral;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Image_Adapter_2 extends RecyclerView.Adapter<Image_Adapter_2.ImageViewHolder> {

    private Context mcontext;
    private List<uploadimage> muploads;
    private OnItemClickListener mListener;
    public Image_Adapter_2(Context context,List<uploadimage> uploads){

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
        holder.textViewcontact.setText("Contact "+upload.get_name()+" on "+upload.get_phone());
        holder.textViewdesc.setText(upload.getDesc());
//        holder.textViewcontact.setText("Please Contact "+name+" at "+phone);
        Picasso.with(mcontext).load(upload.getImageurl()).placeholder(R.mipmap.ic_launcher_round).fit().centerInside().into(holder.imageView);



    }

    @Override
    public int getItemCount() {
        return muploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener ,
            View.OnCreateContextMenuListener , MenuItem.OnMenuItemClickListener {

        public TextView textViewname,textViewdesc,textViewcontact;
        public ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewname=itemView.findViewById(R.id.item_name);
            textViewdesc = itemView.findViewById(R.id.item_desc);
            textViewcontact=itemView.findViewById(R.id.contact);
            imageView=itemView.findViewById(R.id.display);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onClick(View view) {
            if (mListener != null){
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    mListener.onItemClick(position);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("Select What To Do");
            MenuItem EditPost = contextMenu.add(Menu.NONE,1,1,"Edit The Post");
            MenuItem DeletePost = contextMenu.add(Menu.NONE,2,2,"Delete The Post");

            EditPost.setOnMenuItemClickListener(this);
            DeletePost.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            if (mListener != null){
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    switch (menuItem.getItemId()){
                        case 1:
                            mListener.OnEditClick(position);
                            return true;
                        case 2:
                            mListener.onDeleteClick(position);
                            return true;
                    }
                }
            }
            return false;
        }
    }

public interface OnItemClickListener{
        void onItemClick(int position);
        void OnEditClick(int position);
        void onDeleteClick(int position);
}

public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
}
}
