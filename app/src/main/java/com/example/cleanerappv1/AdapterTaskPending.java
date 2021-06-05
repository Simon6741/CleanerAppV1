package com.example.cleanerappv1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class AdapterTaskPending extends RecyclerView.Adapter<AdapterTaskPending.HolderTaskPending> {

    private Context context;
    //array list <data class>
    public ArrayList<CustomerBookings> pendingList;

    private FirebaseAuth auth;

    public AdapterTaskPending(Context context, ArrayList<CustomerBookings> pendingList) {
        this.context = context;
        this.pendingList = pendingList;
    }

    @NonNull
    @Override
    public AdapterTaskPending.HolderTaskPending onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout
        View v = LayoutInflater.from(context).inflate(R.layout.complete_list, parent, false);
        return new AdapterTaskPending.HolderTaskPending(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTaskPending.HolderTaskPending holder, int position) {
        //get data
        CustomerBookings customerBookings = pendingList.get(position);

        //set data
        holder.service_provide.setText(customerBookings.getService());
        holder.cus_name.setText(customerBookings.getName());
        holder.cus_address.setText(customerBookings.getAddress());
        holder.cus_date.setText(customerBookings.getDate());
        holder.cus_time.setText(customerBookings.getTime());

        holder.relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //auth = FirebaseAuth.getInstance();
                Intent intentToTaskDetail = new Intent(context,pending_task_detail.class);
                //intentToTaskDetail.putExtra("CleanerID", auth.getUid());
                intentToTaskDetail.putExtra("CusName",customerBookings.getName());
                intentToTaskDetail.putExtra("CusAdd",customerBookings.getAddress());
                intentToTaskDetail.putExtra("CusHP",customerBookings.getPhone());
                intentToTaskDetail.putExtra("Service",customerBookings.getService());
                intentToTaskDetail.putExtra("ExtraService",customerBookings.getExtra());
                intentToTaskDetail.putExtra("Date",customerBookings.getDate());
                intentToTaskDetail.putExtra("Time",customerBookings.getTime());
                intentToTaskDetail.putExtra("SpecialInt",customerBookings.getSpecial());
                intentToTaskDetail.putExtra("BookID",customerBookings.getBook_id());
                context.startActivity(intentToTaskDetail);
            }
        });

    }

    @Override
    public int getItemCount() {
        return pendingList.size();
    }

    public class HolderTaskPending extends RecyclerView.ViewHolder{

        TextView service_provide, cus_name, cus_address, cus_date, cus_time;
        RelativeLayout relative;

        public HolderTaskPending(@NonNull View itemView) {
            super(itemView);
            service_provide = itemView.findViewById(R.id.service_provide);
            cus_name = itemView.findViewById(R.id. cus_name);
            cus_address = itemView.findViewById(R.id.cus_address);
            cus_date = itemView.findViewById(R.id.cus_date);
            cus_time = itemView.findViewById(R.id.cus_time);
            relative = itemView.findViewById(R.id.relative);

        }
    }


}
