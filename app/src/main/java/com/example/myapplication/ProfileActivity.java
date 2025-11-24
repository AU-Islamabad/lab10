package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    TextView tvName, tvEmail, tvPhone, tvWebsite, tvFollowers, tvPhotos;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        // Initialize TextViews
        tvName      = findViewById(R.id.tv_name);
        tvEmail     = findViewById(R.id.email);
        tvPhone     = findViewById(R.id.phone);
        tvWebsite   = findViewById(R.id.website);
        tvFollowers = findViewById(R.id.followers);
        tvPhotos    = findViewById(R.id.photos);

        // Firebase reference
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Profile");

        // Read data
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {

                    String name = snapshot.child("name").getValue(String.class);
                    String email = snapshot.child("email").getValue(String.class);
                    String phone = snapshot.child("phone").getValue(String.class);
                    String website = snapshot.child("website").getValue(String.class);

                    // Integer values (followers, photo)
                    Long followers = snapshot.child("followers").getValue(Long.class);
                    Long photoCount = snapshot.child("photo").getValue(Long.class);

                    // Set to TextViews
                    tvName.setText(name);
                    tvEmail.setText(email);
                    tvPhone.setText(phone);
                    tvWebsite.setText(website);
                    tvFollowers.setText(String.valueOf(followers));
                    tvPhotos.setText(String.valueOf(photoCount));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, "Failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}