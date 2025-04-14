package com.example.pcontact;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {



    ArrayList<AllContact> allContacts = new ArrayList<>();

    public ContactAdapter(ArrayList<AllContact> allContacts) {
        this.allContacts=allContacts;
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
    }

    @Override
    public int getItemCount() {
        return allContacts.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView txtName,txtEmail,txtPhone;
        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.item_name);
            txtEmail = itemView.findViewById(R.id.item_email);
            txtPhone = itemView.findViewById(R.id.item_phone);
        }
    }
}
