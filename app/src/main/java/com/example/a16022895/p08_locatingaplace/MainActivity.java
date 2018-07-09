package com.example.a16022895.p08_locatingaplace;

import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    Button btE, btN, btC;
    private GoogleMap map;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                LatLng poi_singapore = new LatLng(1.352832, 103.820340);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_singapore,
                        10));
                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);

                LatLng poi_north = new LatLng(1.436065, 103.786263);
                Marker np = map.addMarker(new
                        MarkerOptions()
                        .position(poi_north)
                        .title("HQ - North")
                        .snippet("Block 333, Admiralty Ave 3, 765654 Operating hours: 10am-5pm\n" +
                                "Tel:65433456\n")
                        .icon(BitmapDescriptorFactory.fromResource(android.R.drawable.star_on)));

                LatLng poi_central = new LatLng(1.2976615,103.84748560000003);
                Marker cp = map.addMarker(new
                        MarkerOptions()
                        .position(poi_central)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542 \n" +
                                "Operating hours: 11am-8pm\n" +
                                "Tel:67788652\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                LatLng poi_east = new LatLng(1.3671687,103.92805870000007);
                Marker ep = map.addMarker(new
                        MarkerOptions()
                        .position(poi_east)
                        .title("East")
                        .snippet("Block 555, Tampines Ave 3, 287788 \n" +
                                "Operating hours: 9am-5pm\n" +
                                "Tel:66776677\"\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        String title = marker.getTitle();
                        Toast.makeText(MainActivity.this, title, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });

            }
        });

        btN = (Button) findViewById(R.id.bt1North);
        btC = (Button) findViewById(R.id.bt2Central);
        btE = (Button) findViewById(R.id.bt3East);

        spinner = (Spinner) findViewById(R.id.spinnerItems);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int selectedItem = spinner.getSelectedItemPosition();
                if (selectedItem == 0){
                    LatLng poi_singapore = new LatLng(1.352832, 103.820340);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_singapore,
                            10));

                }else if(selectedItem == 1){
                    if (map != null){
                        LatLng poi_north = new LatLng(1.436065, 103.786263);
                        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(poi_north, 15);
                        map.moveCamera(update);

                    }

                }else if(selectedItem == 2){
                    if (map != null){
                        LatLng poi_central = new LatLng(1.2976615,103.84748560000003);
                        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(poi_central, 15);
                        map.moveCamera(update);

                    }

                }else{
                    if (map != null){
                        LatLng poi_east = new LatLng(1.3671687,103.92805870000007);
                        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(poi_east, 15);
                        map.moveCamera(update);



                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng poi_north = new LatLng(1.436065, 103.786263);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_north,
                        15));

            }
        });

        btC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng poi_central = new LatLng(1.2976615,103.84748560000003);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_central,
                        15));

            }
        });

        btE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng poi_east = new LatLng(1.3671687,103.92805870000007);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_east,
                        15));

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        switch (requestCode) {
            case 0: {
                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                }
            }
        }
    }
}
