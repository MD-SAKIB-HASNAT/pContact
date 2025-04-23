package com.example.pcontact;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {



    ArrayList<AllContact> allContacts = new ArrayList<>();
    Context mContext;

    OnItemLongClickListener longClickListener;

    // Constructor updated to accept OnItemLongClickListener
    public ContactAdapter(ArrayList<AllContact> allContacts, Context mContext, OnItemLongClickListener longClickListener) {
        this.allContacts = allContacts;
        this.mContext = mContext;
        this.longClickListener = longClickListener;
    }




    @NonNull
    public ContactAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);

        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ContactViewHolder holder, int position) {
        holder.txtName.setText(allContacts.get(position).getcName());
        holder.txtEmail.setText(allContacts.get(position).getcEmail());
        holder.txtPhone.setText(allContacts.get(position).getcPhone());
        AllContact ac = allContacts.get(position);
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, NewContact.class);
                i.putExtra("con_id", ac.getId());
                //Log.e("sakib",ac.getId()+"");
                mContext.startActivity(i);
            }
        });

        // Long-click listener added here
        holder.itemView.setOnLongClickListener(v -> {
            longClickListener.onItemLongClick(ac.getId()); // Trigger long click
            return true; // Indicate that the long click was handled
        });
    }

    @Override
    public int getItemCount() {
        return allContacts.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView txtName,txtEmail,txtPhone;
        ImageView btnUpdate;
        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.item_name);
            txtEmail = itemView.findViewById(R.id.item_email);
            txtPhone = itemView.findViewById(R.id.item_phone);
            btnUpdate = itemView.findViewById(R.id.btn_update);



        }
    }
}
