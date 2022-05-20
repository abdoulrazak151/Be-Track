package com.soft.betrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;

public class AuthentificationActivity extends AppCompatActivity {
    MaterialAutoCompleteTextView identifiantCollier,numeroTelephone;
    MaterialButton btnConnexion;
    LinearProgressIndicator indicateurSynchronisation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);
        identifiantCollier=findViewById(R.id.identifiant_collier);
        numeroTelephone=findViewById(R.id.num_telephone);
        btnConnexion=findViewById(R.id.btn_connexion);
        indicateurSynchronisation=findViewById(R.id.indicateur_synchronisation);
        btnConnexion.setOnClickListener(click->{

            Intent intent=new Intent(AuthentificationActivity.this, MainActivity.class);
            startActivity(intent);
            this.finish();
        });




    }
}