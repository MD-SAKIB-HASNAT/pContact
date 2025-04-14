package com.example.pcontact;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView ibtnAdd ;
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

        ContactAdapter adapter = new ContactAdapter(allContacts);
        recView = findViewById(R.id.rec_view);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setAdapter(adapter);

    }
}