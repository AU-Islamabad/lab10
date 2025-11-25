package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    TextView tvName, tvEmail, tvPhone, tvWebsite, tvFollowers, tvPhotos, tvFollowing;
    FirebaseDatabase database;
    DatabaseReference reference;
    Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Profile2");
        // Initialize TextViews
        tvName      = findViewById(R.id.tv_name);
        tvEmail     = findViewById(R.id.email);
        tvPhone     = findViewById(R.id.phone);
        tvWebsite   = findViewById(R.id.website);
        tvFollowers = findViewById(R.id.followers);
        tvPhotos    = findViewById(R.id.photos);
        tvFollowing= findViewById(R.id.following);
        edit=findViewById(R.id.editProfile);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(i);
            }
        });

        // Read data
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {

                    String name = snapshot.child("name").getValue(String.class);
                    String email = snapshot.child("email").getValue(String.class);
                    String phone = snapshot.child("phone").getValue(String.class);
                    String website = snapshot.child("website").getValue(String.class);
                    Long followers = snapshot.child("followers").getValue(Long.class);
                    Long photoCount = snapshot.child("photos").getValue(Long.class);
                    Long following = snapshot.child("following").getValue(Long.class);

                    // Set to TextViews
                    tvName.setText(name);
                    tvEmail.setText(email);
                    tvPhone.setText(phone);
                    tvWebsite.setText(website);
                    tvFollowers.setText(String.valueOf(followers));
                    tvPhotos.setText(String.valueOf(photoCount));
                    tvFollowing.setText(String.valueOf(following));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, "Failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }
}