package com.example.swapbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    EditText titre,prop,img;
    Button btnAdd,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        titre = (EditText) findViewById(R.id.addTitre);
        prop = (EditText) findViewById(R.id.addProp);
        img = (EditText) findViewById(R.id.addImg);
        btnAdd = (Button) findViewById(R.id.btnadd);
        btnBack = (Button) findViewById(R.id.btnback);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void insertData(){
        Map<String,Object> map = new HashMap<>();
        map.put("Titre",titre.getText().toString());
        map.put("Bprop",prop.getText().toString());
        map.put("Burl",img.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Books").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity.this,"Data Inserted Successfully",Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddActivity.this,"Error while insertion",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void clearAll(){
        titre.setText("");
        prop.setText("");
        img.setText("");
    }
}