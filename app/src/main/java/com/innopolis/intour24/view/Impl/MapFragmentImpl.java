package com.innopolis.intour24.view.Impl;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.innopolis.intour24.R;
import com.innopolis.intour24.view.MapInterface;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ekaterina on 6/6/17.
 */

public class MapFragmentImpl extends Fragment implements MapInterface, OnMapReadyCallback {
    private LatLng placeCoord = new LatLng(0, 0);
    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;
    private static MapFragmentImpl instance = null;
    private final String TAG = "MFDebug";

    private Button fullButton;

    //@BindView(R.id.fullMapButton) Button someButton; TODO for some reason butter knife doesn't work here

    public static MapFragmentImpl getInstance() {
        if (instance == null) {
            instance = new MapFragmentImpl();
        }
        return instance;
    }

    public MapFragmentImpl() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapViewFragment);
        mapFragment.getMapAsync(this);
        //button.setOnClickListener(v -> onMapClicked());

        ButterKnife.bind(view);

        fullButton = (Button) view.findViewById(R.id.fullMapButton);
        fullButton.setOnClickListener(v -> onMapClicked());
        //someButton.setText("123");
        return view;
    }

    @Override
    public void showPlace(double lat, double lng) {
        placeCoord = new LatLng(lat, lng);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //LatLng kazan = new LatLng(55.798457, 49.105130);//Что здесь в Google Maps
        this.googleMap = googleMap;
        googleMap.addMarker(new MarkerOptions()
                .position(placeCoord)
                .title("Marker"));
        CameraUpdate camera = CameraUpdateFactory.newLatLng(placeCoord);
        CameraUpdate zoomCamera = CameraUpdateFactory.zoomTo(14.0f);
        googleMap.moveCamera(zoomCamera);
        googleMap.moveCamera(camera);
    }

    private void onMapClicked() {
        Intent intent = new Intent(getActivity().getApplicationContext(), FullMapActivity.class);
        intent.putExtra("Lat", placeCoord.latitude);
        intent.putExtra("Long", placeCoord.longitude);
        startActivity(intent);
    }
}
