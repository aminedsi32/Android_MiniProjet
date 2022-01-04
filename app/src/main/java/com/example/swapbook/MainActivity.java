package com.example.swapbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView register;
    private EditText email,password;
    private Button login;
    private FirebaseAuth mAuth;
    private ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = findViewById(R.id.register);
        register.setOnClickListener(this);

        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
        email = (EditText) findViewById(R.id.email);
        password = (EditText)  findViewById(R.id.password);
        //progressbar = (ProgressBar) findViewById(R.id.progressbar);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                startActivity(new Intent(this,Registeruser.class));
                break;
            case R.id.login:
                UserLogin();
                break;
        }
    }

    private void UserLogin() {
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();

        if (Email.isEmpty()) {
            email.setError("email is requered");
            email.requestFocus();
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            email.setError("email not valid");
            email.requestFocus();
            return;
        } else if (Password.isEmpty()) {
            password.setError("password is requered");
            password.requestFocus();
            return;
        }

        //progressbar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this,Profil.class));
                }
                else {
                    Toast.makeText(MainActivity.this, "failed to login", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}