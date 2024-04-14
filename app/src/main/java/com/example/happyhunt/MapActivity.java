package com.example.happyhunt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.happyhunt.databinding.ActivityMapBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, LocationHelper.LocationListener {
    private GoogleMap myMap;
    ActivityMapBinding mapBinding;
    ActionBarDrawerToggle mToggle;
    Intent intentMain;
    Intent intentFilter;
    private LocationHelper locationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapBinding = ActivityMapBinding.inflate(getLayoutInflater());
        View view = mapBinding.getRoot();
        setContentView(view);
        init();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapActivity.this);

        locationHelper = new LocationHelper(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        myMap = googleMap;
        locationHelper.requestLocationUpdates(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        mToggle = new ActionBarDrawerToggle(this, mapBinding.drawerLayout, mapBinding.materialToolbar, R.string.nav_open, R.string.nav_close);
        mapBinding.drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        setSupportActionBar(mapBinding.materialToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SetNavigationDrawer();
        SetBottomNavigation();
    }

    private void SetNavigationDrawer() {
        mapBinding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
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
        mapBinding.bottomNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_bottom_filters) {
                    intentFilter = new Intent(MapActivity.this, FilterActivity.class);
                    startActivity(intentFilter);
                } else if(item.getItemId()==R.id.nav_bottom_list) {
                    intentMain = new Intent(MapActivity.this, MainActivity.class);
                    startActivity(intentMain);
                }
                return false;
            }
        });
    }

    @Override
    public void onLocationReceived(Location location) {
        if (location != null) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
            myMap.addMarker(new MarkerOptions().position(latLng).title("Current Location"));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationHelper.removeLocationUpdates();
    }
}