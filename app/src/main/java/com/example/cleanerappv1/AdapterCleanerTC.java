package com.example.cleanerappv1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterCleanerTC extends RecyclerView.Adapter<AdapterCleanerTC.HolderCleanerTC> {

    private Context context;
    //array list <data class>
    public ArrayList<CleanerComplaint> CleanerTCList;

    //constructor
    public AdapterCleanerTC(Context context, ArrayList<CleanerComplaint> cleanerTCList) {
        this.context = context;
        CleanerTCList = cleanerTCList;
    }

    @NonNull
    @Override
    public HolderCleanerTC onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout
        View v = LayoutInflater.from(context).inflate(R.layout.cleaner_tc, parent, false);
        return new HolderCleanerTC(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderCleanerTC holder, int position) {

        //get data (need to modify-based on class name-that contain set and get)
        CleanerComplaint cleanerComplaint = CleanerTCList.get(position);

        //set data (need to modify)
        holder.tc_content.setText(cleanerComplaint.getReason());
    }

    @Override
    public int getItemCount() {
        return CleanerTCList.size();
    }

    public class HolderCleanerTC extends RecyclerView.ViewHolder {
        TextView tc_content;

        public HolderCleanerTC(@NonNull View itemView) {
            super(itemView);
            tc_content = itemView.findViewById(R.id.tc_content);
        }
    }
}
