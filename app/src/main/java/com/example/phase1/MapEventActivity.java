package com.example.phase1;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapEventActivity  extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    RecyclerView horizontal_recycler_view;
    ArrayList<EventModel> listEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        listEvent = new ArrayList<>();
        listEvent.add(new EventModel(1,"Google I/O","Virtual, Online","May 18, 2021",R.drawable.event_image,-6.92982190125774, 107.60197482916824));
        listEvent.add(new EventModel(2,"Alteryx Global Inspire 2021","Online, Virtual","May 21, 2021",R.drawable.event_img2,-6.929982190125774, 107.60237482916824));
        listEvent.add(new EventModel(3,"Integrate Remote 2021","Virtual, Online","Jun 03, 2021",R.drawable.event_img3,-6.929982190125774, 107.60137482916824));
        listEvent.add(new EventModel(4,"VivaTechnology","Paris, France","Jun 19, 2021",R.drawable.event_image4,-6.929432190125774, 107.60196482916824));

        horizontal_recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        if (listEvent.size() > 0 & horizontal_recycler_view != null) {
            horizontal_recycler_view.setAdapter(new EventRecyclerViewAdapter(listEvent));
        }
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(MapEventActivity.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManager);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * In this case, we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device.
     * This method will only be triggered once the user has installed
     Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
                LatLng TutorialsPoint = new LatLng(21, 57);
        mMap.addMarker(new
                MarkerOptions().position(TutorialsPoint).title("Tutorialspoint.com"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(TutorialsPoint));
    }
}