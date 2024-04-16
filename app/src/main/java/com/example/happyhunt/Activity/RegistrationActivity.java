package com.example.happyhunt.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.happyhunt.R;
import com.example.happyhunt.databinding.ActivityRegistrationBinding;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityRegistrationBinding registrationBinding;
    ActionBarDrawerToggle mToggle;
    Intent intentMain;
    Intent intentMap;
    Intent intentAbout;
    Intent intentAccount;
    Intent intentFavorite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registrationBinding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        View view = registrationBinding.getRoot();
        setContentView(view);
        init();
    }

    private void init() {
        mToggle = new ActionBarDrawerToggle(this, registrationBinding.drawerLayout, registrationBinding.materialToolbar, R.string.nav_open, R.string.nav_close);
        registrationBinding.drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        setSupportActionBar(registrationBinding.materialToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SetNavigationDrawer();
        SetBottomNavigation();
    }

    private void SetNavigationDrawer() {
        registrationBinding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_account_menu) {
                    intentAccount = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(intentAccount);
                } else if(item.getItemId()==R.id.nav_about_menu) {
                    intentAbout = new Intent(RegistrationActivity.this, AboutActivity.class);
                    startActivity(intentAbout);
                } else if(item.getItemId()==R.id.nav_favorite_menu) {
                    intentFavorite = new Intent(RegistrationActivity.this, FavoriteActivity.class);
                    startActivity(intentFavorite);
                }
                return false;
            }
        });
    }

    private void SetBottomNavigation() {
        registrationBinding.bottomNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_bottom_list) {
                    intentMain = new Intent(RegistrationActivity.this, MainActivity.class);
                    startActivity(intentMain);
                } else if(item.getItemId()==R.id.nav_bottom_map){
                    intentMap = new Intent(RegistrationActivity.this, MapActivity.class);
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