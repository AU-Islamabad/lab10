package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {

    EditText tvName, tvEmail, tvPhone, tvWebsite, tvFollowers, tvPhotos,tvFollowing;
    FirebaseDatabase database;
    DatabaseReference reference;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_profile);

        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Profile2");
        // Initialize TextViews
        tvName      = findViewById(R.id.tv_name);
        tvEmail     = findViewById(R.id.email);
        tvPhone     = findViewById(R.id.phone);
        tvWebsite   = findViewById(R.id.website);
        tvFollowers = findViewById(R.id.followers);
        tvFollowing=findViewById(R.id.following);
        tvPhotos    = findViewById(R.id.photos);
        save=findViewById(R.id.saveProfile);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Convert to data map
                Map<String, Object> data = new HashMap<>();
                data.put("email", tvEmail.getText().toString().trim());
                data.put("name", tvName.getText().toString().trim());




                reference.updateChildren(data).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(EditProfileActivity.this, "Data Saved!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(EditProfileActivity.this, "Failed: " + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}