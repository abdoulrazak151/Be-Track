package com.soft.betrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationView;
import com.soft.betrack.localiser_mon_troupeau.MapsFragment;
import com.soft.betrack.localiser_mon_troupeau.MapsLarge;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    MaterialToolbar toolbar;
   FragmentManager fragmentManager;
   FragmentTransaction fragmentTransaction;
    MaterialCardView maPositionCard, monTroupeauCard, zonePaturageCard, pointEauCard;
    MapsFragment mapsFragment;
    NavigationView navigationView;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        maPositionCard=findViewById(R.id.ma_position);
        monTroupeauCard=findViewById(R.id.mon_troupeau);
        zonePaturageCard=findViewById(R.id.zone_paturage);
        pointEauCard=findViewById(R.id.point_eau);
        toolbar=findViewById(R.id.toolbar);
        mapsFragment=new MapsFragment();
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);

        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);



        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_maps_hote, mapsFragment);
        fragmentTransaction.commit();
        maPositionCard.setOnClickListener(click->{
            Toast.makeText(this, String.valueOf(mapsFragment.getMyLocation().size()), Toast.LENGTH_LONG).show();
        });

        monTroupeauCard.setOnClickListener(click->{
            Intent intent=new Intent(MainActivity.this, MapsLarge.class);
            startActivity(intent);
        });
        pointEauCard.setOnClickListener(click->{

        });

        zonePaturageCard.setOnClickListener(click->{


        });



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}