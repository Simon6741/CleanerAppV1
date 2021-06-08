package com.example.cleanerappv1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cleanerappv1.R;
import com.example.cleanerappv1.ui.AdminUserEditActivity;
import com.example.cleanerappv1.model.Customer;
import com.example.cleanerappv1.model.ServiceDetails;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class ServiceListAdapter extends RecyclerView.Adapter<ServiceListAdapter.Holder>{

    private Context context;
    //array list <data class>

    public ArrayList<ServiceDetails> serviceList;
    private ItemClickListener mListener;


    //constructor
    public ServiceListAdapter(Context context, ArrayList<ServiceDetails> service) {
        this.context = context;
        this.serviceList = service;

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout
        View v = LayoutInflater.from(context).inflate(R.layout.service_details_item, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        ServiceDetails service = serviceList.get(position);

        holder.tvBookingId.setText(String.format(context.getString(R.string.admin_service_bookingId), service.getBooking_id()));
        holder.tvDate.setText(String.format(context.getString(R.string.admin_service_date), service.getDate()));
        holder.tvTime.setText(String.format(context.getString(R.string.admin_service_time), service.getTime()));
        holder.tvPhone.setText(String.format(context.getString(R.string.admin_service_phone), service.getPhone()));
        holder.tvTotalAmount.setText(String.format(context.getString(R.string.admin_service_totalamount), service.getTotal_amount()));
        holder.tvStatus.setText(String.format(context.getString(R.string.admin_service_status), service.getStatus()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, AdminUserEditActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }


    public class Holder extends RecyclerView.ViewHolder {
        TextView tvBookingId, tvDate, tvTime, tvPhone, tvTotalAmount, tvStatus;

        public Holder(@NonNull View itemView) {
            super(itemView);

            tvBookingId = itemView.findViewById(R.id.tv_booking_id);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvPhone = itemView.findViewById(R.id.tv_phone);
            tvTotalAmount = itemView.findViewById(R.id.tv_total_amount);
            tvStatus = itemView.findViewById(R.id.tv_status);

        }
    }

    public interface ItemClickListener{
        void onItemClick(int position, Customer customer);
    }
}
