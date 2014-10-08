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
            if (distance(pos.getLatitude(), pos.getLongitude(), MapsActivity.ADA.latitude, MapsActivity.ADA.longitude) < 20)
            {
                MapsActivity.getMapsActivity().nextActivity();
            }
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

    public float distance (double lat_a, double lng_a, double lat_b, double lng_b )
    {
        double earthRadius = 3958.75;
        double latDiff = Math.toRadians(lat_b-lat_a);
        double lngDiff = Math.toRadians(lng_b-lng_a);
        double a = Math.sin(latDiff /2) * Math.sin(latDiff /2) +
                Math.cos(Math.toRadians(lat_a)) * Math.cos(Math.toRadians(lat_b)) *
                        Math.sin(lngDiff /2) * Math.sin(lngDiff /2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double distance = earthRadius * c;

        int meterConversion = 1609;

        return new Float(distance * meterConversion).floatValue();
    }
}
