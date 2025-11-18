package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Button b_login;
    EditText et_email, et_pass;
    TextView tv_forget, tv_signup;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        b_login=findViewById(R.id.btnLogin);
        tv_forget=findViewById(R.id.forget);
        et_email=findViewById(R.id.etEmail);
        et_pass=findViewById(R.id.etPassword);
        tv_signup=findViewById(R.id.signup);

        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });

        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email =et_email.getText().toString().trim();
                String pass =et_pass.getText().toString().trim();

                if (email.isEmpty()) {
                    et_email.setError("Required");
                    return;
                }
                if (pass.isEmpty()) {
                    et_pass.setError("Required");
                    return;
                }
                mAuth.signInWithEmailAndPassword(email,pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    //home screen
                                    Intent i= new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(i);
                                }else {
                                    Toast.makeText(LoginActivity.this,"Incorrect Email/Password",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

    }
}