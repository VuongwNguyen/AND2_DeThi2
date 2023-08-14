package com.example.and_dethi2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.and_dethi2.Database.DBhelper;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextInputEditText edtUsername = findViewById(R.id.edtUsername);
        TextInputEditText edtPassword = findViewById(R.id.edtPassword);

        DBhelper dBhelper = new DBhelper(this);


        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtUsername.getText().toString().isEmpty() ||edtPassword.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter All Fields", Toast.LENGTH_SHORT).show();
                } else if (dBhelper.Login(edtUsername.getText().toString(), edtPassword.getText().toString()) != 1) {
                    Toast.makeText(MainActivity.this, "Wrong Account Or Password", Toast.LENGTH_SHORT).show();
                } else if (dBhelper.Login(edtUsername.getText().toString(), edtPassword.getText().toString()) == 1) {
                    Toast.makeText(MainActivity.this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,ListRCVActivity.class));
                }
            }
        });
    }
}