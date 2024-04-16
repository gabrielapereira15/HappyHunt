package com.example.happyhunt.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.happyhunt.R;
import com.example.happyhunt.databinding.ActivityLoginBinding;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityLoginBinding loginBinding;
    ActionBarDrawerToggle mToggle;
    Intent intentMain;
    Intent intentMap;
    Intent intentAbout;
    Intent intentAccount;
    Intent intentFavorite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = loginBinding.getRoot();
        setContentView(view);
        init();
    }

    private void init() {
        mToggle = new ActionBarDrawerToggle(this, loginBinding.drawerLayout, loginBinding.materialToolbar, R.string.nav_open, R.string.nav_close);
        loginBinding.drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        setSupportActionBar(loginBinding.materialToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SetNavigationDrawer();
        SetBottomNavigation();
    }

    private void SetNavigationDrawer() {
        loginBinding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_account_menu) {
                    intentAccount = new Intent(LoginActivity.this, LoginActivity.class);
                    startActivity(intentAccount);
                } else if(item.getItemId()==R.id.nav_about_menu) {
                    intentAbout = new Intent(LoginActivity.this, AboutActivity.class);
                    startActivity(intentAbout);
                } else if(item.getItemId()==R.id.nav_favorite_menu) {
                    intentFavorite = new Intent(LoginActivity.this, FavoriteActivity.class);
                    startActivity(intentFavorite);
                }
                return false;
            }
        });
    }

    private void SetBottomNavigation() {
        loginBinding.bottomNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_bottom_list) {
                    intentMain = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intentMain);
                } else if(item.getItemId()==R.id.nav_bottom_map){
                    intentMap = new Intent(LoginActivity.this, MapActivity.class);
                    startActivity(intentMap);
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {}
}