package com.teknokrat.indekost.codereye.indekost;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    ProfileActivity profileActivityFragment;
    MapsActivity mapsFragment;
    LoginActivity loginFragment;
    ListKost listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initializeFragment();

        setupBottomNavigation();
    }


    private void initializeFragment(){
        profileActivityFragment = new ProfileActivity();
        mapsFragment = new MapsActivity();
        loginFragment = new LoginActivity();
        listFragment = new ListKost();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, listFragment);
        transaction.commit();
    }


    private void setupBottomNavigation(){
        final BottomNavigationView btmNav = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        btmNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemToSelect = 0;
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()){
                    case R.id.home_page:
                        transaction.replace(R.id.fragment_container, listFragment);
                        itemToSelect = 0;
                        break;
                    case R.id.map_page:
                        transaction.replace(R.id.fragment_container, mapsFragment);
                        itemToSelect = 1;
                        break;
                    case R.id.profil_page:
                        transaction.replace(R.id.fragment_container, profileActivityFragment);
                        itemToSelect = 2;
                        break;
                }
                btmNav.getMenu().getItem(itemToSelect).setChecked(true);
                transaction.commit();
                return false;
            }
        });
    }
}
