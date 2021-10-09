package com.jcrawley.mylocation;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.EditText;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationListenerImpl implements LocationListener {

    private final MainActivity mainActivity;

    public LocationListenerImpl(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    public void onLocationChanged(Location loc) {

        mainActivity.setCoordinates(loc.getLatitude(), loc.getLongitude());

        /*------- To get city name from coordinates -------- */
        String cityName = null;
        Geocoder gcd = new Geocoder(mainActivity.getApplicationContext(),
                Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = gcd.getFromLocation(loc.getLatitude(),
                    loc.getLongitude(), 1);
            if (addresses.size() > 0) {
                System.out.println(addresses.get(0).getLocality());
                cityName = addresses.get(0).getLocality();
                System.out.println("^^^ City Name: " + cityName);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}
}