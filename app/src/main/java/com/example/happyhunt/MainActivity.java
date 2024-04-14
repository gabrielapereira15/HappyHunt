package com.example.happyhunt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.happyhunt.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;
    ActionBarDrawerToggle mToggle;
    Intent intentMap;
    Intent intentFilter;
    private LocationHelper locationHelper;
    private Location currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);
        init();

        locationHelper = new LocationHelper(this);
        locationHelper.requestLocationUpdates(new LocationHelper.LocationListener() {
            @Override
            public void onLocationReceived(Location location) {
                currentLocation = location;
            }
        });
    }

    private void init() {
        mToggle = new ActionBarDrawerToggle(this, mainBinding.drawerLayout, mainBinding.materialToolbar, R.string.nav_open, R.string.nav_close);
        mainBinding.drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        setSupportActionBar(mainBinding.materialToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SetNavigationDrawer();
        SetBottomNavigation();
    }

    private void SetNavigationDrawer() {
        mainBinding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_account_menu) {
                    // Implement page
                } else if (item.getItemId() == R.id.nav_about_menu) {
                    // Implement page
                } else if (item.getItemId() == R.id.nav_history_menu) {
                    // Implement page
                } else if (item.getItemId() == R.id.nav_favorite_menu) {
                    // Implement page
                }
                return false;
            }
        });
    }

    private void SetBottomNavigation() {
        mainBinding.bottomNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_bottom_map) {
                    intentMap = new Intent(MainActivity.this, MapActivity.class);
                    startActivity(intentMap);
                } else if (item.getItemId() == R.id.nav_bottom_filters) {
                    intentFilter = new Intent(MainActivity.this, FilterActivity.class);
                    startActivity(intentFilter);
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
