package com.teknokrat.indekost.codereye.indekost;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.teknokrat.indekost.codereye.indekost.model.Kosts;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends Fragment implements OnMapReadyCallback {

    //Firebase Needed
    private static final String TAG = MainActivity.class.getSimpleName();
    private FirebaseDatabase database;
    private DatabaseReference myRef = null;
    private List<Kosts> kostsList;
    boolean isDataReady;

    private GoogleMap mMap;
    private MapView vwMap;

    public static MapsActivity newInstance() {
        MapsActivity fragment = new MapsActivity();
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        getListData();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_maps, container, false);
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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

//        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(kostsList.get(1).getLatitude(), kostsList.get(1).getLongitude());
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

        if (isDataReady = true) {
            for (int i = 0; i < kostsList.size(); i++) {
                LatLng coordinate = new LatLng(kostsList.get(i).getLatitude(), kostsList.get(i).getLongitude());
                mMap.addMarker(new MarkerOptions().position(coordinate).title(kostsList.get(i).getNama()));
            }
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        vwMap = (MapView) view.findViewById(R.id.map);
        vwMap.onCreate(savedInstanceState);
        vwMap.onResume();
        vwMap.getMapAsync(this);
    }

    private void getListData() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("kost");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long value = dataSnapshot.getChildrenCount();
                Log.d(TAG, "no of children : " + value);

                try {
                    kostsList = new ArrayList<Kosts>();
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        kostsList.add(child.getValue(Kosts.class));
                    }

                    for (int i = 0; i < kostsList.size(); i++) {
                        System.out.println("Nama Kost an : " + kostsList.get(i).getNama());
                    }
                } catch (Exception e) {

                } finally {
                    isDataReady = true;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });
    }
}