package oles.rus.app.olesrusapp;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO: Test this
 */

/**
 * Created by borimino on 9/23/14.
 */
public class RecieveThread implements Runnable
{
    public static final int DELAY = 1000; // The time to sleep between each run in milliseconds
    public Map<Integer, Marker> markers;

    public RecieveThread()
    {
        markers = new HashMap<Integer, Marker>();
    }

    @Override
    public void run()
    {
        Log.e("olesApp", "Starting recieveThread");
        while (!Thread.currentThread().isInterrupted())
        {
            // TODO: Get the position and ID
            ArrayList<Integer> IDs = new ArrayList<Integer>() {{
                add(0);
                add(1);
                add(5);
            }};
            ArrayList<LatLng> locations = new ArrayList<LatLng>() {{
                add(new LatLng(0, 0));
                add(new LatLng(1, 2));
                add(new LatLng(10, 10));
            }};

            // Update the position in the Map
//            for (Map.Entry<Integer, Marker> entry : markers.entrySet())
//            {
//                int index = IDs.indexOf(entry.getKey());
//                if (index != -1)
//                {
//                    entry.getValue().setPosition(locations.get(index));
//                }
//            }
            for (int i = 0; i < IDs.size(); i++)
            {
                int id = IDs.get(i);
                Marker m = markers.get(id);
                if (m == null)
                {
                    m = MapsActivity.getMapsActivity().getMap().addMarker(new MarkerOptions().position(new LatLng(0, 0)).title(id + ""));
                    markers.put(id, m);
                }
                m.setPosition(locations.get(i));
            }

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
