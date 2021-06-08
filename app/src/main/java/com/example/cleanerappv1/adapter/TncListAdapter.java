package com.example.cleanerappv1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cleanerappv1.R;
import com.example.cleanerappv1.model.FAQ;
import com.example.cleanerappv1.model.TNC;

import java.util.ArrayList;



public class TncListAdapter extends RecyclerView.Adapter<TncListAdapter.Holder> {

    private Context context;
    public ArrayList<TNC> tncArrayList;


    //constructor
    public TncListAdapter(Context context, ArrayList<TNC> tncs) {
        this.context = context;
        this.tncArrayList = tncs;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout
        View v = LayoutInflater.from(context).inflate(R.layout.tnc_item, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        //get data (need to modify-based on class name-that contain set and get)
        TNC tnc = tncArrayList.get(position);

        //set data (need to modify)
        //holder.tc_content.setText(cleanerComplaint.getReason());
        holder.tv_tnc_title.setText(tnc.getTerm());
//        holder.tv_status.setText(cleaner.getEmailAddress());
    }

    @Override
    public int getItemCount() {
        return tncArrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tv_tnc_title;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tv_tnc_title = itemView.findViewById(R.id.tv_tnc);
        }
    }
}
