package oles.rus.app.olesrusapp;

import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by borimino on 10/8/14.
 */
public class FindFøtexThread implements Runnable
{
    private static final int DELAY = 1000;

    @Override
    public void run()
    {
        LocationClient mLocationClient = FindBrotherActivity.getFindBrotherActivity().getmLocationClient();
        LatLng myPos = new LatLng(mLocationClient.getLastLocation().getLatitude(), mLocationClient.getLastLocation().getLongitude());

        boolean føtexNotReached = true;

        while (føtexNotReached)
        {
            myPos = new LatLng(mLocationClient.getLastLocation().getLatitude(), mLocationClient.getLastLocation().getLongitude());

            if (distance(Mission1_2Activity.FØTEX.latitude, Mission1_2Activity.FØTEX.longitude, myPos.latitude, myPos.longitude) < 20)
            {
                føtexNotReached = false;
                Mission1_2Activity.getMission1_2Activity().nextActivity();
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
