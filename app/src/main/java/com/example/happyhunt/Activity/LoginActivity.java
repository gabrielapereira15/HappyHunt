package com.example.happyhunt.Activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.happyhunt.Util.DBHelper;
import com.example.happyhunt.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityLoginBinding loginBinding;
    Intent intentMain;
    Intent intentRegistration;
    DBHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = loginBinding.getRoot();
        setContentView(view);
        loginBinding.btnBack.setOnClickListener(this);
        loginBinding.btnSignUp.setOnClickListener(this);
        loginBinding.btnLogin.setOnClickListener(this);

        dbh = new DBHelper(this);
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == loginBinding.btnBack.getId()) {
            intentMain = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intentMain);
        } else if (v.getId() == loginBinding.btnSignUp.getId()) {
            intentRegistration = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(intentRegistration);
        } else if(v.getId() == loginBinding.btnLogin.getId()) {
            if(validateData()) {
                Cursor cursor1 = dbh.readProfile();
                Boolean isUserRegistered = searchAccount(cursor1);
                if (!isUserRegistered) {
                    Toast.makeText(this, "User not registered. Please, create an account!", Toast.LENGTH_LONG).show();
                    return;
                }
                Boolean isPasswordCorrect = validatePassword(cursor1);
                if (!isPasswordCorrect) {
                    Toast.makeText(this, "Password incorrect, please try again.", Toast.LENGTH_LONG).show();
                }
                Toast.makeText(this, "User logged!", Toast.LENGTH_LONG).show();
                intentMain = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intentMain);
            }
        }
    }

    private Boolean validatePassword(Cursor cursor1) {
        Boolean isPasswordValid = false;
        if (cursor1.moveToFirst()) {
            do {
                @SuppressLint("Range") String password = cursor1.getString(cursor1.getColumnIndex("password"));
                if (loginBinding.edtPassword.getText().toString().trim().equals(password)) {
                    isPasswordValid = true;
                    return isPasswordValid;
                }
            } while (cursor1.moveToNext());
        }
        cursor1.close();
        return isPasswordValid;
    }

    private Boolean searchAccount(Cursor cursor1) {
        Boolean isRegistered = false;
        if (cursor1.moveToFirst()) {
            do {
                @SuppressLint("Range") String userEmail = cursor1.getString(cursor1.getColumnIndex("email"));
                if (loginBinding.edtEmail.getText().toString().trim().equals(userEmail)) {
                    isRegistered = true;
                    return isRegistered;
                }
            } while (cursor1.moveToNext());
        }
        cursor1.close();
        return isRegistered;
    }

    private boolean validateData() {
        if (loginBinding.edtEmail.getText().toString().trim().isEmpty()) {
            loginBinding.edtEmail.setError("Email is required");
            return false;
        }
        if (loginBinding.edtPassword.getText().toString().trim().isEmpty()) {
            loginBinding.edtPassword.setError("Password is required");
            return false;
        }
        return true;
    }
}