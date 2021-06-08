package com.example.cleanerappv1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cleanerappv1.R;
import com.example.cleanerappv1.complete_task_detail;
import com.example.cleanerappv1.model.FAQ;
import com.example.cleanerappv1.model.TNC;
import com.example.cleanerappv1.ui.ActivityEditFAQ;
import com.example.cleanerappv1.ui.ActivityEditTerm;

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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intentToTaskDetail = new Intent(context, complete_task_detail.class);
//                intentToTaskDetail.putExtra("CusName",customerBookings.getName());
//                intentToTaskDetail.putExtra("CusAdd",customerBookings.getAddress());
//                intentToTaskDetail.putExtra("CusHP",customerBookings.getPhone());
//                intentToTaskDetail.putExtra("Service",customerBookings.getService());
//                intentToTaskDetail.putExtra("ExtraService",customerBookings.getExtra());
//                intentToTaskDetail.putExtra("Date",customerBookings.getDate());
//                intentToTaskDetail.putExtra("Time",customerBookings.getTime());
//                intentToTaskDetail.putExtra("SpecialInt",customerBookings.getSpecial());
//                context.startActivity(intentToTaskDetail);

                context.startActivity(new Intent(context, ActivityEditTerm.class));

            }
        });
    }

    @Override
    public int getItemCount() {
        return tncArrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tv_tnc_title;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tv_tnc_title = itemView.findViewById(R.id.tv_tnc_item);
        }
    }
}
