package com.innopolis.intour24.view.Impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.innopolis.intour24.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ekaterina on 07/06/17.
 */

public class FullMapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private LatLng placeCoord;

    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_screen_map);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back_button_selector);
        toolbar.setNavigationOnClickListener(view -> this.finish());

        Intent intent = getIntent();
        placeCoord = new LatLng(intent.getDoubleExtra("Lat", 0), intent.getDoubleExtra("Long", 0));

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.fullMap);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions()
                .position(placeCoord)
                .title("Marker"));
        CameraUpdate camera = CameraUpdateFactory.newLatLng(placeCoord);
        CameraUpdate zoomCamera = CameraUpdateFactory.zoomTo(14.0f);
        googleMap.moveCamera(zoomCamera);
        googleMap.moveCamera(camera);
    }
}
