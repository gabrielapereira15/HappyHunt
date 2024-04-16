package com.example.happyhunt.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.happyhunt.R;
import com.example.happyhunt.databinding.ActivityAboutBinding;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {
    
    ActivityAboutBinding aboutBinding;
    ActionBarDrawerToggle mToggle;
    Intent intentMain;
    Intent intentMap;
    Intent intentAbout;
    Intent intentAccount;
    Intent intentFavorite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aboutBinding = ActivityAboutBinding.inflate(getLayoutInflater());
        View view = aboutBinding.getRoot();
        setContentView(view);
        init();
    }

    private void init() {
        mToggle = new ActionBarDrawerToggle(this, aboutBinding.drawerLayout, aboutBinding.materialToolbar, R.string.nav_open, R.string.nav_close);
        aboutBinding.drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        setSupportActionBar(aboutBinding.materialToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SetNavigationDrawer();
        SetBottomNavigation();
    }

    private void SetNavigationDrawer() {
        aboutBinding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_account_menu) {
                    intentAccount = new Intent(AboutActivity.this, LoginActivity.class);
                    startActivity(intentAccount);
                } else if(item.getItemId()==R.id.nav_about_menu) {
                    intentAbout = new Intent(AboutActivity.this, AboutActivity.class);
                    startActivity(intentAbout);
                } else if(item.getItemId()==R.id.nav_favorite_menu) {
                    intentFavorite = new Intent(AboutActivity.this, FavoriteActivity.class);
                    startActivity(intentFavorite);
                }
                return false;
            }
        });
    }

    private void SetBottomNavigation() {
        aboutBinding.bottomNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_bottom_list) {
                    intentMain = new Intent(AboutActivity.this, MainActivity.class);
                    startActivity(intentMain);
                } else if(item.getItemId()==R.id.nav_bottom_map){
                    intentMap = new Intent(AboutActivity.this, MapActivity.class);
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