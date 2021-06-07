package com.example.cleanerappv1.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cleanerappv1.R;
import com.example.cleanerappv1.model.Customer;
import com.example.cleanerappv1.model.ServiceItem;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class ServiceListAdapter extends RecyclerView.Adapter<ServiceListAdapter.Holder>{

    private Context context;
    //array list <data class>

    public ArrayList<ServiceItem> serviceList;
    private ItemClickListener mListener;


    //constructor
    public ServiceListAdapter(Context context, ArrayList<ServiceItem> service) {
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

        ServiceItem service = serviceList.get(position);

        holder.tvName.setText(String.format(context.getString(R.string.customer_name), service.getCustomerName()));
        holder.tvContactNum.setText(String.format(context.getString(R.string.user_contact), service.getContactNum()));
        holder.tvEmail.setText(String.format(context.getString(R.string.user_email), service.getEmail()));
        holder.tvCleanerName.setText(String.format(context.getString(R.string.cleaner_name), service.getCleanerName()));
        holder.tvCleanerContact.setText(String.format(context.getString(R.string.cleaner_contact), service.getContactNum()));

    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }


    public class Holder extends RecyclerView.ViewHolder {
        TextView tvName, tvContactNum, tvEmail, tvCleanerName, tvCleanerContact;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvContactNum = itemView.findViewById(R.id.tv_contact_no);
            tvEmail = itemView.findViewById(R.id.tv_email);
            tvCleanerName = itemView.findViewById(R.id.tv_cleaner_name);
            tvCleanerContact = itemView.findViewById(R.id.tv_cleaner_contact_no);
        }
    }

    public interface ItemClickListener{
        void onItemClick(int position, Customer customer);
    }
}
