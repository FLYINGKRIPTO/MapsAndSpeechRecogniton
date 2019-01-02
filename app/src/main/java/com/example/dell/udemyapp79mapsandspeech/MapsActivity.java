package com.example.dell.udemyapp79mapsandspeech;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    //onMapReadyCallback - public interface
    //Callback interface for when the map is ready to be used
    //Once an instance of this interface is set on a MapFragment or MapView Object
    //the onMapReady(GoogleMap) method is triggered when the map is ready to be used
    //and provides a non null instance of GoogleMap

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        //Camera zooming
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(sydney,10.0f);
        mMap.moveCamera(cameraUpdate);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(sydney);
        markerOptions.title("Welcome to sydney");
        markerOptions.snippet("Fantastic");
        mMap.addMarker(markerOptions);

        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(sydney);
        circleOptions.radius(300);
        circleOptions.strokeWidth(20.0f);
        circleOptions.strokeColor(Color.CYAN);
        mMap.addCircle(circleOptions);
    }
}
