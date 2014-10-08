package oles.rus.app.olesrusapp;

import android.location.Location;
import android.util.Log;

import com.google.android.gms.location.LocationClient;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by borimino on 9/19/14.
 */
public class SendThread implements Runnable
{
    private LocationClient locationClient;
    public static final int DELAY = 1000; // The time to sleep between each run in milliseconds

    public SendThread(LocationClient locationClient)
    {
        this.locationClient = locationClient;
    }

    @Override
    public void run()
    {
        Log.e("olesApp", "Starting sendThread");
        while(!Thread.currentThread().isInterrupted())
        {
            // Get the location
            Location pos = locationClient.getLastLocation();
            double lat = pos.getLatitude();
            double lng = pos.getLongitude();

            // TODO: Send the location
            Log.e("olesApp", lat + ", " + lng);
            try
            {
                JSONObject jsonUser = new JSONObject("{\"userId\":" + MapsActivity.getUser().getUserId() + ",\"point\":{\"lat\":" + lat + ",\"lng\":" + lng + "}}");
                System.out.println(jsonUser.toString()); //DEBUG
                new SendPosCom().execute(jsonUser);
            } catch (JSONException e)
            {
                e.printStackTrace();
            }

            // Sleep
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
//                Log.e("olesApp", e.toString());
                break;
            }
        }
    }
}
