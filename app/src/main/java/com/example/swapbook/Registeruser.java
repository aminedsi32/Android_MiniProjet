package com.example.swapbook;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.style.TextAppearanceSpan;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Registeruser extends AppCompatActivity implements View.OnClickListener{
    private TextView banner;
    private EditText name,email,password;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private Button registerUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeruser);

        mAuth = FirebaseAuth.getInstance();

        banner = findViewById(R.id.banner);
        banner.setOnClickListener(this);

        registerUser = findViewById(R.id.register);
        registerUser.setOnClickListener(this);

        name =(EditText) findViewById(R.id.name);
        email =(EditText) findViewById(R.id.email);
        password =(EditText) findViewById(R.id.password);
        //progressBar = (ProgressBar) findViewById(R.id.progressbar);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case    R.id.banner:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.register:
                registerUser();
                break;
        }
    }
    private void registerUser() {
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();
        String Name = name.getText().toString().trim();
        if (Name.isEmpty()) {
            name.setError("Name is requered");
            name.requestFocus();
            return;
        } else if (Email.isEmpty()) {
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

        //progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(Name, Email);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Registeruser.this, "User has been register successfully", Toast.LENGTH_SHORT).show();
                                        //progressBar.setVisibility(View.VISIBLE);

                                    }
                                    else{
                                        Toast.makeText(Registeruser.this, "failed to register try again", Toast.LENGTH_SHORT).show();
                                        //progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });

                        }else{
                            Toast.makeText(Registeruser.this, "failed to register try again", Toast.LENGTH_SHORT).show();
                            //progressBar.setVisibility(View.GONE);
                        }
                    }
                });

    }

    }
