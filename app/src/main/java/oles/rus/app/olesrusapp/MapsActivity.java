package oles.rus.app.olesrusapp;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.concurrent.ExecutionException;

public class MapsActivity extends FragmentActivity implements GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener{

    private static MapsActivity mapsActivity;
    // The map from the layout
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    // Object used to get the current location
    private LocationClient mLocationClient;
//    public static final LatLng ADA = new LatLng(56.155454, 10.1317073);
    public static final LatLng ADA = new LatLng(56.1720615, 10.1887686);

    private Thread sendThread;
    private Thread recieveThread;

    private static User user = new User(0);

    public MapsActivity()
    {
        super();
        mapsActivity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mLocationClient = new LocationClient(this, this, this);

        Intent intent = getIntent();
        if (AvatarActivity.getUser() != null)
        {
            user = AvatarActivity.getUser();
        } else
        {
            String studynumber = intent.getStringExtra(MyActivity.EXTRA_MESSAGE);

            try
            {
                user = new LoginCom().execute(studynumber).get();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            } catch (ExecutionException e)
            {
                e.printStackTrace();
            }
        }

        setUpMapIfNeeded();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        mLocationClient.connect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        if(null != sendThread)
            sendThread.interrupt();
        if(null != recieveThread)
            recieveThread.interrupt();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("0, 0"));

        // Enables the blue circle at the users current location
        mMap.setMyLocationEnabled(true);

//        LatLng myPos = new LatLng(mMap.getMyLocation().getLatitude(), mMap.getMyLocation().getLongitude());
//        mMap.addMarker(new MarkerOptions().position(mLocationClient.getLastLocation()).title(("MyPos")));
    }

    @Override
    public void onConnected(Bundle bundle)
    {
        // Gets the position of the client
        LatLng myPos = new LatLng(mLocationClient.getLastLocation().getLatitude(), mLocationClient.getLastLocation().getLongitude());
        // Adds a marker at the position of the client
        mMap.addMarker(new MarkerOptions().position(myPos).title("My position"));

        sendThread = new Thread(new SendThread(mLocationClient));
        sendThread.start();
        recieveThread = new Thread(new RecieveThread());
        recieveThread.start();
    }

    @Override
    public void onDisconnected()
    {
        sendThread.interrupt();
        recieveThread.interrupt();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult)
    {

    }

    public GoogleMap getMap()
    {
        return mMap;
    }

    public static MapsActivity getMapsActivity()
    {
        return mapsActivity;
    }

    public static User getUser()
    {
        return user;
    }

    public void nextActivity()
    {
        sendThread.interrupt();
        recieveThread.interrupt();

        Intent intent = new Intent(this, Mission2_1Activity.class);
        startActivity(intent);
    }
}
