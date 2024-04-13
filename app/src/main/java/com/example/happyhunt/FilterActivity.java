package com.example.happyhunt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.happyhunt.databinding.ActivityFilterBinding;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class FilterActivity extends AppCompatActivity {
    ActivityFilterBinding filterBinding;
    ActionBarDrawerToggle mToggle;
    Intent intentMap;
    Intent intentMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        filterBinding = ActivityFilterBinding.inflate(getLayoutInflater());
        View view = filterBinding.getRoot();
        setContentView(view);
        init();
    }

    private void init() {
        mToggle = new ActionBarDrawerToggle(this, filterBinding.drawerLayout, filterBinding.materialToolbar, R.string.nav_open,R.string.nav_close);
        filterBinding.drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        setSupportActionBar(filterBinding.materialToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SetNavigationDrawer();
        SetBottomNavigation();
    }

    private void SetNavigationDrawer() {
        filterBinding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_account_menu) {
                    // Implement page
                } else if(item.getItemId()==R.id.nav_about_menu) {
                    // Implement page
                } else if(item.getItemId()==R.id.nav_history_menu) {
                    // Implement page
                } else if(item.getItemId()==R.id.nav_favorite_menu) {
                    // Implement page
                }
                return false;
            }
        });
    }

    private void SetBottomNavigation() {
        filterBinding.bottomNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_bottom_map) {
                    intentMap = new Intent(FilterActivity.this, MapActivity.class);
                    startActivity(intentMap);
                } else if(item.getItemId()==R.id.nav_bottom_list) {
                    intentMain = new Intent(FilterActivity.this, MainActivity.class);
                    startActivity(intentMain);
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
}