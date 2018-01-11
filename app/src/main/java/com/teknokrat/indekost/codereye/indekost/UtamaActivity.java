package com.teknokrat.indekost.codereye.indekost;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class UtamaActivity extends AppCompatActivity {

    ProfileActivity profileActivityFragment;
    MapsActivity mapsFragment;
    LoginActivity loginFragment;
    ListKost listFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    transaction.replace(R.id.content_utama, listFragment);
                    break;
                case R.id.navigation_dashboard:
                    transaction.replace(R.id.content_utama, mapsFragment);
                    break;
                case R.id.navigation_notifications:
                    transaction.replace(R.id.content_utama, profileActivityFragment);
                    break;
            }
            transaction.commit();
            return false;
        }
    };

    private void initializeFragment(){
        profileActivityFragment = new ProfileActivity();
        mapsFragment = new MapsActivity();
        loginFragment = new LoginActivity();
        listFragment = new ListKost();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_utama, listFragment);
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);

        initializeFragment();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
