package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.analytics.FirebaseAnalytics;

public class HomeActivity extends AppCompatActivity {
    LinearLayout profile, settings, alerts, analytics;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        profile=findViewById(R.id.profile);
        alerts=findViewById(R.id.alerts);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x= new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(x);
            }
        });

        alerts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x= new Intent(HomeActivity.this, AletsActivity.class);
                startActivity(x);
            }
        });


    }
}