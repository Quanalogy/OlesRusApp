package oles.rus.app.olesrusapp;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by borimino on 10/8/14.
 */
public class FindBrotherThread implements Runnable
{
    private static int DELAY = 1000;

    @Override
    public void run()
    {
        LocationClient mLocationClient = FindBrotherActivity.getFindBrotherActivity().getmLocationClient();
        LatLng myPos = new LatLng(mLocationClient.getLastLocation().getLatitude(), mLocationClient.getLastLocation().getLongitude());

        boolean brotherNotFound = true;
        final LatLng brotherPos = new LatLng(56.1718517, 10.1885695);
        final ArrayList<Marker> brotherMarker = new ArrayList<Marker>(); // = mMap.addMarker(new MarkerOptions().title("Brors position").position(brotherPos).visible(false));

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable()
        {
            @Override
            public void run()
            {
                brotherMarker.add(FindBrotherActivity.getFindBrotherActivity().getMap().addMarker(new MarkerOptions().title("Brors position").position(brotherPos).visible(false)));
            }
        });

        while (brotherNotFound)
        {
            myPos = new LatLng(mLocationClient.getLastLocation().getLatitude(), mLocationClient.getLastLocation().getLongitude());
            Log.i("OlesRusApp", myPos.toString());
            if (distance(myPos.latitude, myPos.longitude, brotherPos.latitude, brotherPos.longitude) < 50)
            {
                Handler handler1 = new Handler(Looper.getMainLooper());
                handler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        brotherMarker.get(0).setVisible(true);
                    }
                });
            } else
            {
                Handler handler1 = new Handler(Looper.getMainLooper());
                handler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        brotherMarker.get(0).setVisible(false);
                    }
                });
            }

            if (distance(myPos.latitude, myPos.longitude, brotherPos.latitude, brotherPos.longitude) < 10)
            {
                FindBrotherActivity.getFindBrotherActivity().nextActivity();
            }

            try
            {
                Thread.sleep(DELAY);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
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
