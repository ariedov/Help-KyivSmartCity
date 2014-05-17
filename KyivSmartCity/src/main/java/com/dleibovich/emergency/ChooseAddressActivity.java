package com.dleibovich.emergency;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.dleibovich.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ChooseAddressActivity extends Activity {

    private GoogleMap mMap;
    private LocationListener mLocationListener;
    private LocationManager mLocationManager;

    private LatLng mLastPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);

        final Button submitLocation = (Button) findViewById(R.id.submit_location);
        submitLocation.setEnabled(false);
        submitLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mMap = ((MapFragment) getFragmentManager()
                .findFragmentById(R.id.map)).getMap();
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.clear();
                mMap.addMarker(new MarkerOptions()
                                .position(latLng)
                );
                mLastPosition = latLng;
                submitLocation.setEnabled(true);
            }
        });

        mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("Location", "Location found");
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                mLocationManager.removeUpdates(this);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.d("Location", "Location status changed.");

            }

            @Override
            public void onProviderEnabled(String provider) {
                Log.d("Location", "Provider enabled.");
            }

            @Override
            public void onProviderDisabled(String provider) {
                Log.d("Location", "Provider disabled.");
                new AlertDialog.Builder(ChooseAddressActivity.this)
                        .setMessage(R.string.location_services_disabled)
                        .setPositiveButton(R.string.enable_location_service, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                            }
                        }).show();
            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocationListener);
        } else {
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mLocationListener);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLocationManager.removeUpdates(mLocationListener);
    }
}
