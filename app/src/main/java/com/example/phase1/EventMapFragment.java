package com.example.phase1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class EventMapFragment extends Fragment implements OnMapReadyCallback {

    MapView mapView;
    GoogleMap map;

    RecyclerView horizontal_recycler_view;
    ArrayList<EventModel> listEvent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        mapView =(MapView)view.findViewById(R.id.evenMap);
        mapView.onCreate(savedInstanceState);

        // Gets to GoogleMap from the MapView and does initialization stuff
        mapView.getMapAsync(this);

        listEvent = new ArrayList<>();
        listEvent.add(new EventModel(1,"Google I/O","Virtual, Online","May 18, 2021",R.drawable.event_image,-6.92982190125774, 107.60197482916824));
        listEvent.add(new EventModel(2,"Alteryx Global Inspire 2021","Online, Virtual","May 21, 2021",R.drawable.event_img2,-6.929982190125774, 107.60237482916824));
        listEvent.add(new EventModel(3,"Integrate Remote 2021","Virtual, Online","Jun 03, 2021",R.drawable.event_img3,-6.929982190125774, 107.60137482916824));
        listEvent.add(new EventModel(4,"VivaTechnology","Paris, France","Jun 19, 2021",R.drawable.event_image4,-6.929432190125774, 107.60196482916824));

        horizontal_recycler_view = (RecyclerView) view.findViewById(R.id.recycler_view);
        if (listEvent.size() > 0 & horizontal_recycler_view != null) {
            horizontal_recycler_view.setAdapter(new EventRecyclerViewAdapter(listEvent));
        }
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManager);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng MarkerPosition = new LatLng(-6.929382190125774, 107.60197482916824);
        map.addMarker(new
                MarkerOptions().position(MarkerPosition).title("MarkerPosition"));
        map.moveCamera(CameraUpdateFactory.newLatLng(MarkerPosition));
    }
}
