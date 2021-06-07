package com.example.cleanerappv1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cleanerappv1.R;
import com.example.cleanerappv1.model.Cleaner;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CleanerListAdapter extends RecyclerView.Adapter<CleanerListAdapter.Holder> {

    private Context context;
    //array list <data class>
    public ArrayList<Cleaner> cleanerList;

    //constructor
    public CleanerListAdapter(Context context, ArrayList<Cleaner> cleaners) {
        this.context = context;
        this.cleanerList = cleaners;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout
        View v = LayoutInflater.from(context).inflate(R.layout.user_item_view, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        //get data (need to modify-based on class name-that contain set and get)
        Cleaner cleaner = cleanerList.get(position);

        //set data (need to modify)
        //holder.tc_content.setText(cleanerComplaint.getReason());
        Picasso.get().load(cleaner.getImage()).into(holder.imgView_profile);
        holder.tv_name.setText(cleaner.getUsername());
        holder.tv_contact.setText(cleaner.getContactNumber());
        holder.tv_email.setText(cleaner.getEmailAddress());
    }

    @Override
    public int getItemCount() {
        return cleanerList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_contact;
        TextView tv_email;
        ImageView imgView_profile;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_contact = itemView.findViewById(R.id.tv_contact_no);
            tv_email = itemView.findViewById(R.id.tv_email);
            imgView_profile = itemView.findViewById(R.id.imgViewUser);
        }
    }
}
