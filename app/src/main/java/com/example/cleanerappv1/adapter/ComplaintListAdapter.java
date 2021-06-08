package com.example.cleanerappv1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cleanerappv1.R;
import com.example.cleanerappv1.model.Cleaner;
import com.example.cleanerappv1.model.Complain;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class ComplaintListAdapter extends RecyclerView.Adapter<ComplaintListAdapter.Holder> {

    private Context context;
    //array list <data class>
    public ArrayList<Complain> complainArrayList;

    //constructor
    public ComplaintListAdapter(Context context, ArrayList<Complain> complains) {
        this.context = context;
        this.complainArrayList = complains;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout
        View v = LayoutInflater.from(context).inflate(R.layout.complaint_item, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        //get data (need to modify-based on class name-that contain set and get)
        Complain complain = complainArrayList.get(position);


        holder.tv_name.setText(String.format(context.getString(R.string.complaint_username), complain.getName()));
        holder.tv_details.setText(complain.getReason());
    }

    @Override
    public int getItemCount() {
        return complainArrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_details;
        TextView tv_status;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_complaint_name);
            tv_details = itemView.findViewById(R.id.tv_details_desc);
            tv_status = itemView.findViewById(R.id.tv_complaint_status);
        }
    }
}
