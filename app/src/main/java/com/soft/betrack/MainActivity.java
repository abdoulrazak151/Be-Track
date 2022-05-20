package com.soft.betrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.soft.betrack.localiser_mon_troupeau.MapsFragment;

public class MainActivity extends AppCompatActivity {
   FragmentManager fragmentManager;
   FragmentTransaction fragmentTransaction;
    MaterialCardView maPositionCard, monTroupeauCard, zonePaturageCard, pointEauCard;
    MapsFragment mapsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        maPositionCard=findViewById(R.id.ma_position);
        monTroupeauCard=findViewById(R.id.mon_troupeau);
        zonePaturageCard=findViewById(R.id.zone_paturage);
        pointEauCard=findViewById(R.id.point_eau);
        mapsFragment=new MapsFragment();



        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_maps_hote, mapsFragment);
        fragmentTransaction.commit();
        maPositionCard.setOnClickListener(click->{
            Toast.makeText(this, String.valueOf(mapsFragment.getMyLocation().size()), Toast.LENGTH_LONG).show();
        });

        monTroupeauCard.setOnClickListener(click->{

        });
        pointEauCard.setOnClickListener(click->{

        });

        zonePaturageCard.setOnClickListener(click->{


        });



    }
}