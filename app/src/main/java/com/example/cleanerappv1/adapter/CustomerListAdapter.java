package com.example.cleanerappv1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cleanerappv1.R;
import com.example.cleanerappv1.ui.AdminUserEditActivity;
import com.example.cleanerappv1.model.Customer;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.cleanerappv1.util.Constant.TYPE_CUSTOMER;


public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.Holder>{

    private Context context;
    //array list <data class>
    public ArrayList<Customer> customerList;
    private ItemClickListener mListener;


    //constructor
    public CustomerListAdapter(Context context, ArrayList<Customer> customers, ItemClickListener listener) {
        this.context = context;
        this.customerList = customers;
        this.mListener = listener;
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
        Customer customer = customerList.get(position);

        //set data (need to modify)
        //holder.tc_content.setText(cleanerComplaint.getReason());

        holder.tv_name.setText(customer.getUsername());
        holder.tv_contact.setText(customer.getContactNumber());
        holder.tv_email.setText(customer.getEmailAddress());

//        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(mListener != null){
//                    mListener.onItemClick(position);
//                }
//            }
//        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClick(position, TYPE_CUSTOMER);
                //context.startActivity(new Intent(context, AdminUserEditActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }


    public class Holder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_contact;
        TextView tv_email;
        ImageView imgView_profile;
       // Button btnDelete;


        public Holder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_contact = itemView.findViewById(R.id.tv_contact_no);
            tv_email = itemView.findViewById(R.id.tv_email);
            imgView_profile = itemView.findViewById(R.id.imgViewUser);
           // btnDelete = itemView.findViewById(R.id.btn_delete);


//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    mListener.onItemClick(getAdapterPosition());
//                }
//            });
        }
    }

    public interface ItemClickListener{
        void onItemClick(int position, String viewType);
    }
}
