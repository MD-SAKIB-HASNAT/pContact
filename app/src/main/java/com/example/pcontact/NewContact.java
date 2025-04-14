package com.example.pcontact;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NewContact extends AppCompatActivity {

    EditText edtName,edtEmail,edtPhone;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_contact);
        edtName = findViewById(R.id.edt_name);
        edtEmail = findViewById(R.id.edt_email);
        edtPhone = findViewById(R.id.edt_phone);
        btnAdd = findViewById(R.id.btn_add);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name,email,phone;
                name = edtName.getText().toString().trim();
                email = edtEmail.getText().toString();
                phone = edtPhone.getText().toString();

                if (!isValidName(name)) {
                    edtName.setError("Enter a valid name");
                    return;
                }
                if (!isValidEmail(email)) {
                    edtEmail.setError("Enter a valid email");
                    return;
                }
                if (!isValidPhone(phone)) {
                    edtPhone.setError("Enter a valid 11-digit phone number");
                    return;
                }

                ContactCrud contactCrud = new ContactCrud(NewContact.this);
                if(contactCrud.insertContact(name,email,phone)!=-1){
                    Toast.makeText(NewContact.this, "New Contact Added Successfully", Toast.LENGTH_SHORT).show();
                    edtName.setText("");
                    edtEmail.setText("");
                    edtPhone.setText("");

                    startActivity(new Intent(NewContact.this, MainActivity.class));
                }
                else{
                    Toast.makeText(NewContact.this, "Failed to Add New Contact", Toast.LENGTH_SHORT).show();
                }

            }
        private boolean isValidName(String name) {
            return name.matches("^[a-zA-Z\\s]{2,}$");
        }

        private boolean isValidEmail(String email) {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }

        private boolean isValidPhone(String phone) {
            return phone.matches("^\\d{11}$");
        }
        });


    }
}