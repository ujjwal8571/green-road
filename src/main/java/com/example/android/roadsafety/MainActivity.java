package com.example.android.roadsafety;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;


import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener,
        ResultCallback<LocationSettingsResult>{

    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();



    private GoogleMap mMap;
    boolean mapReady = false;
    private final String TAG = "MainActivity";
    private GoogleApiClient mGoogleApiClient;
    protected LocationRequest mLocationRequest;
    protected LocationSettingsRequest mLocationSettingsRequest;
    protected Boolean mRequestingLocationUpdates;
    protected static final int REQUEST_CHECK_SETTINGS = 0x1;
    public Location mCurrentLocation;
    public LatLng currentMarker;
    public Marker myCurrentLocationMarker;
    public static final int REQUEST_PERMISSION_LOCATION = 10;
    int RQS_GooglePlayServices = 0;
    DatabaseHelper dbHelper;
    private GoogleApiClient client;
    public Boolean isChecked;
    public EditText distance;
    public int val;






    @Override
    protected void onStart() {
        Log.i(TAG, "onstart");
        super.onStart();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();

        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int resultCode = googleAPI.isGooglePlayServicesAvailable(this);
        if (resultCode == ConnectionResult.SUCCESS) {
            mGoogleApiClient.connect();
            Log.i(TAG, Boolean.toString(mGoogleApiClient.isConnected()));
        } else {
            googleAPI.getErrorDialog(this, resultCode, RQS_GooglePlayServices);
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }


    @Override
    protected void onResume() {
        Log.i(TAG, "on resume");
        super.onResume();
        // Within {@code onPause()}, we pause location updates, but leave the
        // connection to GoogleApiClient intact.  Here, we resume receiving
        // location updates if the user has requested them.
        if (mGoogleApiClient.isConnected()) {
            //  Toast.makeText(FusedLocationWithSettingsDialog.this, "location was already on so detecting location now", Toast.LENGTH_SHORT).show();
            startLocationUpdates();
        }

    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onpause");
        super.onPause();
        // Stop location updates to save battery, but don't disconnect the GoogleApiClient object.

    }

    @Override
    protected void onStop() {
        Log.i(TAG, "onstop");
        super.onStop();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        mGoogleApiClient.disconnect();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
//        View header=navigationView.getHeaderView(0);
//        TextView name = (TextView)header.findViewById(R.id.username);
//        TextView email = (TextView)header.findViewById(R.id.email);
//        email.setText(user.getEmail());

        dbHelper = new DatabaseHelper(this);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        SharedPreferences mySharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        isChecked = mySharedPreferences.getBoolean("isChecked",false);
       //distance = (EditText) mySharedPreferences.getStringSet("distance",null);
//        val = Integer.parseInt( distance.getText().toString() );



        //step 1
        buildGoogleApiClient();

        //step 2
        createLocationRequest();

        //step 3
        buildLocationSettingsRequest();

        checkLocationSettings();


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    protected void checkLocationSettings() {
        Log.i(TAG, "checklocationsettings");
        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(
                        mGoogleApiClient,
                        mLocationSettingsRequest
                );
        result.setResultCallback(this);
    }

    @Override
    public void onResult(LocationSettingsResult locationSettingsResult) {

        Log.i(TAG,"onresult");
        final Status status = locationSettingsResult.getStatus();
        switch (status.getStatusCode()) {
            case LocationSettingsStatusCodes.SUCCESS:

                startLocationUpdates();
                break;
            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:


                try {

                    status.startResolutionForResult(MainActivity.this, REQUEST_CHECK_SETTINGS);
                } catch (IntentSender.SendIntentException e) {

                }
                break;
            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG,"onactivityresult");
        switch (requestCode) {
            // Check for the integer request code originally supplied to startResolutionForResult().
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        Log.i(TAG, "User agreed to make required location settings changes.");
                        startLocationUpdates();
                        break;
                    case Activity.RESULT_CANCELED:
                        Log.i(TAG, "User chose not to make required location settings changes.");
                        break;
                }
                break;
        }
    }


    protected void stopLocationUpdates() {
        Log.i(TAG,"stoplocationupdates");
        // It is a good practice to remove location requests when the activity is in a paused or
        // stopped state. Doing so helps battery performance and is especially
        // recommended in applications that request frequent location updates.
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient,
                this
        ).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(Status status) {
                mRequestingLocationUpdates = false;
                //   setButtonsEnabledState();
            }
        });
    }



    protected void createLocationRequest() {
        Log.i(TAG, "createlocationrequest");
        mLocationRequest = new LocationRequest();

        // Sets the desired interval for active location updates. This interval is
        // inexact. You may not receive updates at all if no location sources are available, or
        // you may receive them slower than requested. You may also receive updates faster than
        // requested if other applications are requesting location at a faster interval.
        mLocationRequest.setInterval(1000);

        // Sets the fastest rate for active location updates. This interval is exact, and your
        // application will never receive updates faster than this value.
        mLocationRequest.setFastestInterval(500);

        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    protected synchronized void buildGoogleApiClient() {
        Log.i(TAG, "Building GoogleApiClient");
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        Log.i(TAG, Boolean.toString(mGoogleApiClient.isConnected()));
    }


    protected void buildLocationSettingsRequest() {
        Log.i(TAG, "bulidlocationsettingrequest");
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingsRequest = builder.build();
    }


    protected void startLocationUpdates() {
        Log.i(TAG, "startlocationupdates");

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION_LOCATION);
        } else {
            goAndDetectLocation();
        }

    }

    public void goAndDetectLocation() {
        Log.i(TAG, "getanddetectlocation");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient,
                mLocationRequest,
                this
        ).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(Status status) {
                mRequestingLocationUpdates = true;

            }
        });
    }


    @Override
    public void onConnected(Bundle bundle) {

        Log.i(TAG, "Connected to GoogleApiClient");

        if (mCurrentLocation == null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //return;
            }
            mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

            if(mCurrentLocation != null)
                updateMap(mCurrentLocation);

        }

//        if (mCurrentLocation != null)
//            Log.i(TAG, Double.toString(mCurrentLocation.getLatitude()));
    }


    @Override
    public void onConnectionSuspended(int cause) {
        Log.i(TAG, "Connection suspended");
    }

    @Override
    public void onLocationChanged(Location location) {
        //Log.i(TAG, "locationchanged");

        mCurrentLocation = location;
        updateMap(mCurrentLocation);

        if(isChecked == true) {

            ArrayList arrayList = dbHelper.getAllMarkers();
            Location target = new Location("target");
            Object[] mStringArray = arrayList.toArray();

            for (int i = 0; i < mStringArray.length; i = i + 2) {
                //Log.d(TAG,(String)mStringArray[i]);
                currentMarker = new LatLng((Double) mStringArray[i], (Double) mStringArray[i + 1]);
                target.setLatitude(currentMarker.latitude);
                target.setLongitude(currentMarker.longitude);
                Log.i(TAG, Double.toString(mCurrentLocation.distanceTo(target)));
                if (mCurrentLocation.distanceTo(target) < 15) {

                    Log.i(TAG, "ek baar aa jani chahiye notification");
//                NotificationCompat.Builder mBuilder =
//                        new NotificationCompat.Builder(this)
//                                .setSmallIcon(R.mipmap.logo)
//                                .setContentTitle(getString(R.string.app_name))
//                                .setContentText("Drive Carefully")
//                                .setDefaults(Notification.DEFAULT_SOUND)
//                                .setAutoCancel(true);

                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                    r.play();
                }
            }
        }



    }


    @Override
    public void onMapReady(GoogleMap map) {
        //Log.i(TAG, "mapready");
        mapReady = true;
        mMap = map;

        ArrayList arrayList = dbHelper.getAllMarkers();

        Object[] mStringArray = arrayList.toArray();

        for(int i = 0; i < mStringArray.length ; i=i+2){
            //Log.d(TAG,(String)mStringArray[i]);
            currentMarker = new LatLng((Double)mStringArray[i], (Double)mStringArray[i+1]);
            mMap.addMarker(new MarkerOptions().position(currentMarker));


        }




    }

    public void updateMap(final Location location) {

        //Log.i(TAG, "updateMap");

        if(location != null)
             currentMarker = new LatLng(location.getLatitude(), location.getLongitude());


        if(myCurrentLocationMarker == null)
        {
            myCurrentLocationMarker = mMap.addMarker(new MarkerOptions().position(currentMarker).title("My Location"));
            myCurrentLocationMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.last_marker));

        CameraPosition target = CameraPosition.builder().target(currentMarker).zoom(14).build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(target));
        }

        else
        {
            myCurrentLocationMarker.remove();
            myCurrentLocationMarker = mMap.addMarker(new MarkerOptions().position(currentMarker).title("My Location"));
            Log.i(TAG,Double.toString(currentMarker.latitude));
            Log.i(TAG,Double.toString(currentMarker.longitude));
            myCurrentLocationMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.last_marker));

        }



        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {

            @Override
            public void onMapLongClick(LatLng latLng) {

                Intent MarkerFormIntent = new Intent(MainActivity.this, MarkerForm.class );

                startActivity(MarkerFormIntent);


            }
        });

//
//        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
//            @Override
//            public void onInfoWindowClick(Marker marker) {
//                Intent intent = new Intent(MainActivity.this,MarkedMarker.class);
//                //Log.i(TAG,"yha se ho rha hai");
//
//                intent.putExtra("latitude", marker.getPosition().latitude);
//                intent.putExtra("longitude", marker.getPosition().longitude);
//
//
//
//                startActivity(intent);
//
//
//            }
//
//        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(final Marker marker) {
                Intent intent = new Intent(MainActivity.this,MarkedMarker.class);

                if(marker.getPosition().latitude != location.getLatitude() && marker.getPosition().longitude != location.getLongitude()) {


                    //Log.i(TAG,"yha se ho rha hai");

                    intent.putExtra("latitude", marker.getPosition().latitude);
                    intent.putExtra("longitude", marker.getPosition().longitude);


                    startActivity(intent);

                }
                return true;
                }
        });





    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent settings = new Intent(this, SettingsActivity.class);
            startActivity(settings);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PrefsFragment extends PreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preferences);
            ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
//            setSupportActionBar(actionBar);



        }


        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = super.onCreateView(inflater, container, savedInstanceState);

            if (view != null) {
                view.setBackgroundColor(getResources().getColor(android.R.color.background_light));
            }

            return view;
        }



    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment frag = null;

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(this,MainActivity.class));

//
        } else if (id == R.id.nav_about) {
            frag = AboutFragment.newInstance();
            fragmentTransaction.replace(R.id.content_main, frag);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_statistics) {

            frag = StatisticsFragment.newInstance();
            fragmentTransaction.replace(R.id.content_main, frag);
            fragmentTransaction.commit();
//
        } else if (id == R.id.nav_settings) {

//            frag = new PrefsFra
            getFragmentManager().beginTransaction().replace(android.R.id.content,
                    new PrefsFragment()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG, "onconnectionfailed");
    }

//    @Override
//    public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
//        Log.i(TAG, "onresult");
//    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }
}
