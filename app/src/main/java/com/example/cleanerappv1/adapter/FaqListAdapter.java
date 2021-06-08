package com.example.cleanerappv1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cleanerappv1.R;
import com.example.cleanerappv1.model.Complain;
import com.example.cleanerappv1.model.FAQ;
import com.example.cleanerappv1.ui.ActivityEditFAQ;
import android.content.Intent;




import java.util.ArrayList;



public class FaqListAdapter extends RecyclerView.Adapter<FaqListAdapter.Holder> {

    private Context context;
    //array list <data class>
    public ArrayList<FAQ> faqArrayList;
    private ItemClickListener mListener;


    //constructor
    public FaqListAdapter(Context context, ArrayList<FAQ> faqs, ItemClickListener listener) {
        this.context = context;
        this.faqArrayList = faqs;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout
        View v = LayoutInflater.from(context).inflate(R.layout.faq_item, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        //get data (need to modify-based on class name-that contain set and get)
        FAQ faq = faqArrayList.get(position);

        holder.tv_faq_title.setText(String.format(context.getString(R.string.faq_question), faq.getTitle()));
        holder.tv_faq_desc.setText(String.format(context.getString(R.string.faq_ans), faq.getDetail()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClick(position);

                //context.startActivity(new Intent(context, ActivityEditFAQ.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return faqArrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tv_faq_title;
        TextView tv_faq_desc;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tv_faq_title = itemView.findViewById(R.id.tv_faq_item_title);
            tv_faq_desc = itemView.findViewById(R.id.tv_faq_item_desc);
        }
    }

    public interface ItemClickListener{
        void onItemClick(int position);
    }
}
