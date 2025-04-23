package com.example.pcontact;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView ibtnAdd;
    RecyclerView recView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ibtnAdd = findViewById(R.id.ibtn_add);
        ibtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NewContact.class));
            }
        });


        ArrayList<AllContact> allContacts = new ArrayList<>();
        ContactCrud cr = new ContactCrud(MainActivity.this);
        allContacts = cr.readContacts();

        // Pass the long-click listener to the adapter
        ContactAdapter adapter = new ContactAdapter(allContacts, MainActivity.this, new OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int contactId) {
                showDeleteDialog(contactId);
            }
        });

        recView = findViewById(R.id.rec_view);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setAdapter(adapter);
    }

    // Method to show delete confirmation dialog
    private void showDeleteDialog(int contactId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Do you want to delete this contact?")
                .setTitle("Delete Contact")
                .setCancelable(false)
                .setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    // Delete the contact
                    ContactCrud cr = new ContactCrud(MainActivity.this);
                    if(cr.deleteContact(contactId)!=-1) {
                        Toast.makeText(this,"Delete Contact Successfully", Toast.LENGTH_SHORT).show();
                        refreshData(); // Reload the contact list
                    }
                    else{
                        Toast.makeText(this,"Failed to Delete Contact", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> dialog.cancel())
                .show();
    }

    // Refresh data and reload the adapter
    private void refreshData() {
        ContactCrud cr = new ContactCrud(MainActivity.this);
        ArrayList<AllContact> allContacts = cr.readContacts();

        ContactAdapter adapter = new ContactAdapter(allContacts, MainActivity.this, new OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int contactId) {
                showDeleteDialog(contactId);
            }
        });

        recView.setAdapter(adapter);
    }

}