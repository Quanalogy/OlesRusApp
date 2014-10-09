package oles.rus.app.olesrusapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class Mission1_2Activity extends FragmentActivity implements GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener
{

    private GoogleMap mMap;
    private LocationClient mLocationClient;
    //    public static final LatLng FØTEX = new LatLng(56.155454, 10.1317073);
    public static final LatLng FØTEX = new LatLng(56.1716323, 10.1882974);
    private Thread findFøtexThread;
    private static Mission1_2Activity mission1_2Activity;

    public Mission1_2Activity()
    {
        super();
        mission1_2Activity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission1_2);
        setUpMapIfNeeded();
        mLocationClient = new LocationClient(this, this, this);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        mLocationClient.connect();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setUpMapIfNeeded();
    }


    @Override
    protected void onStop()
    {
        super.onStop();
        if (null != findFøtexThread)
            findFøtexThread.interrupt();
    }


    private void setUpMapIfNeeded()
    {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null)
        {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null)
            {
                setUpMap();
            }
        }
    }


    private void setUpMap()
    {
        mMap.addMarker(new MarkerOptions().position(FØTEX).title("Føtex"));

        // Enables the blue circle at the users current location
        mMap.setMyLocationEnabled(true);

//        LatLng myPos = new LatLng(mMap.getMyLocation().getLatitude(), mMap.getMyLocation().getLongitude());
//        mMap.addMarker(new MarkerOptions().position(mLocationClient.getLastLocation()).title(("MyPos")));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mission1_2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnected(Bundle bundle)
    {
        findFøtexThread = new Thread(new FindFøtexThread());
        findFøtexThread.start();


    }

    @Override
    public void onDisconnected()
    {
        findFøtexThread.interrupt();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult)
    {

    }

    public static Mission1_2Activity getMission1_2Activity()
    {
        return mission1_2Activity;
    }

    public void nextActivity()
    {
        Intent intent = new Intent(this, Mission1_3Activity.class);
        startActivity(intent);
    }
}
