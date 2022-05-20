package com.soft.betrack.localiser_mon_troupeau;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.UriPermission;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.card.MaterialCardView;
import com.soft.betrack.MainActivity;
import com.soft.betrack.R;
import com.soft.betrack.localiser_mon_troupeau.directionhelpers.FetchURL;
import com.soft.betrack.localiser_mon_troupeau.directionhelpers.TaskLoadedCallback;

import java.security.Provider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapsFragment extends Fragment implements LocationListener, OnMapReadyCallback, TaskLoadedCallback {
  LocationManager locationManager;
  MarkerOptions op1,op2;
  Polyline currentPosition;
  GoogleMap map;
  FusedLocationProviderClient mLocationClient;


    private OnMapReadyCallback callback = new OnMapReadyCallback() {


        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */

        @Override
        public void onMapReady(GoogleMap googleMap){
           googleMap.getUiSettings().setMapToolbarEnabled(false);

           if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                   PackageManager.PERMISSION_GRANTED &&
                   ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                   PackageManager.PERMISSION_GRANTED) {
               googleMap.setMyLocationEnabled(true);
               googleMap.getUiSettings().setMyLocationButtonEnabled(true);
           } else {
                ActivityCompat.requestPermissions(getActivity(), new String[] {
                   Manifest.permission.ACCESS_FINE_LOCATION,
                   Manifest.permission.ACCESS_COARSE_LOCATION },
                   1);
             }


//           googleMap.setMyLocationEnabled(true);

//            locationManager= (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
//            Intent intent=new Intent();
////             locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, PendingIntent.getActivity(getContext(), 200, intent, PendingIntent.FLAG_UPDATE_CURRENT ));
////            double lat=locationManager.getLastKnownLocation("gps").getLatitude();
////            double longitude=locationManager.getLastKnownLocation("gps").getLongitude();
//
////            Toast.makeText(getContext(), String.valueOf(locationManager.getLastKnownLocation("gps").getSpeed()), Toast.LENGTH_SHORT).show();
//            LatLng sydney = new LatLng(-34,60 );
////            googleMap.setMyLocationEnabled(true);
////            Map<String , Double> m=getMyLocation();
////            double latitude=getMyLocation().get("latitude");
////            double longitude=getMyLocation().get("longitude");
////            LatLng myPosition= new LatLng(latitude, longitude);
////            googleMap.addMarker(new MarkerOptions().position(myPosition).title("abdoul"));
//
//            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//            googleMap.getUiSettings().setZoomControlsEnabled(true);
        }
    };

    private String getUrl(LatLng position, LatLng position1, String directionMode) {
        String origin="origin="+position.latitude+","+position.latitude;
        String dest="destination="+position1.latitude+","+position1.longitude;
        String mode="mode="+directionMode;
        String parameters=origin+"&"+dest+"&"+mode;
        String output="json";
        String url="https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters+"&key="+getString(R.string.key5);
        return  url;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_maps, container, false);
//        if(getContext())

        mLocationClient=new FusedLocationProviderClient(getActivity());

        Toast.makeText(getActivity(), String.valueOf(getMyLocation().size()), Toast.LENGTH_LONG).show();
        LatLng positionActuelle=new LatLng(83, -65);
        LatLng positionTarget=new LatLng(-65, 83);
        op1=new MarkerOptions().position(positionActuelle).title("op1");
        op2=new MarkerOptions().position(positionTarget).title("op2");
        String url=getUrl(op1.getPosition(), op2.getPosition(), "driving");
        if(getContext().getApplicationContext().checkSelfPermission(Manifest.permission.INTERNET)==PackageManager.PERMISSION_GRANTED){
//            new FetchURL(getActivity()).execute(url, "driving");
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {
        LocationListener.super.onLocationChanged(locations);
    }

    @Override
    public void onFlushComplete(int requestCode) {
        LocationListener.super.onFlushComplete(requestCode);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }


    @Override
    public void onTaskDone(Object... values) {
        if(currentPosition!=null)
            currentPosition.remove();
        currentPosition=map.addPolyline((PolylineOptions) values[0]);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map=googleMap;
        map.addMarker(op1);
        map.addMarker(op2);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(23,45), 10f));
    }



    @SuppressLint("MissingPermission")
    public Map<String, Double> getMyLocation(){
        Map<String, Double> mapT=new HashMap<>();
        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {

                    mLocationClient.getLastLocation().addOnCompleteListener(task -> {
                        Location location=task.getResult();

                        mapT.put("latitude", location.getLatitude());
                        mapT.put("longitude", location.getLongitude());
                    });
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION },
                    1);
        }
        return mapT;
    }
}