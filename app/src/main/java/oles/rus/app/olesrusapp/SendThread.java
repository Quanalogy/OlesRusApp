package oles.rus.app.olesrusapp;

import android.location.Location;
import android.util.Log;

import com.google.android.gms.location.LocationClient;

/**
 * Created by markus on 9/19/14.
 */
public class SendThread implements Runnable
{
    private LocationClient locationClient;
    public static final int DELAY = 5000; // The time to sleep between each run in milliseconds

    public SendThread(LocationClient locationClient)
    {
        this.locationClient = locationClient;
    }

    @Override
    public void run()
    {
        while(true)
        {
            // Get the location
            Location pos = locationClient.getLastLocation();
            double lat = pos.getLatitude();
            double lng = pos.getLongitude();

            // TODO: Send the location

            // Sleep
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                Log.e("olesApp", e.toString());
                break;
            }
        }
    }
}
