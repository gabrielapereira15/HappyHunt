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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, LocationHelper.LocationListener {
    private GoogleMap myMap;
    ActivityMapBinding mapBinding;
    ActionBarDrawerToggle mToggle;
    Intent intentMain;
    Intent intentFilter;
    private LocationHelper locationHelper;
    private static final int DEFAULT_RADIUS_METERS = 5000;
    private Location currentLocation;

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

        // Set an onClick listener for the search button
        mapBinding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch();
            }
        });
    }

    private void performSearch() {
        //String searchText = mapBinding.searchEdittext.getText().toString().trim();
        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
        // Perform a nearby search
        performNearbySearch(currentLocation);
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        myMap = googleMap;
        locationHelper.requestLocationUpdates(this);
    }

    @Override
    public void onLocationReceived(Location location) {
        if (location != null) {
            currentLocation = location;
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            MarkerOptions options = new MarkerOptions().position(latLng).title("Current Location");
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            myMap.addMarker(options);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationHelper.removeLocationUpdates();
    }

    private void performNearbySearch(Location location) {
        locationHelper.getNearbyPlaces(location.getLatitude(), location.getLongitude(), DEFAULT_RADIUS_METERS, new LocationHelper.PlacesCallback() {
            @Override
            public void onPlacesReceived(List<Place> places) {
                // Plot the retrieved places on the map
                if (places != null && !places.isEmpty()) {
                    for (Place place : places) {
                        LatLng placeLatLng = new LatLng(place.getLatLng().latitude, place.getLatLng().longitude);
                        myMap.addMarker(new MarkerOptions().position(placeLatLng).title(place.getName()));
                    }
                }
            }
        });
    }
}