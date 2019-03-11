package com.shubham.abhi.manish_electronics;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

/**
 * Created by Darshan on 15-05-2017.
 */

public class OrderLocationService extends Service implements LocationListener {
    LocationManager myLocationManager;
    Location myLocation;
    Context myContext;

    OrderLocationService(Context context) {
        this.myContext = context;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public Location getLocation() {

        myLocationManager = (LocationManager) myContext.getSystemService(LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(myContext, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(myContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (myLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                myLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000 * 60 * 1, 0, this);
                // parameters : 1> PROVIDER which could be NETWORK or GPS
                // 2> time after which update should happen
                // 3> distance after which update should happen
                // remember  specify either 2 or 3
                // 4> context

                myLocation = myLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                //Toast.makeText(c, "got"+l.getLatitude(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(myContext, "Network not found", Toast.LENGTH_SHORT).show();
            }
        }
        return myLocation;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
