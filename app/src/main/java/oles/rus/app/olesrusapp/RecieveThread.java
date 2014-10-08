package oles.rus.app.olesrusapp;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

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
            JSONArray tmp = new JSONArray();
            try
            {
                tmp = new JSONArray("[{\"userId\":1, \"type\": \"leader\", \"currentPoint\":{\"lat\":10, \"lng\":10}},{\"userId\":3, \"type\": \"leader\", \"currentPoint\":{\"lat\":10, \"lng\":10}}]");
                tmp = new RecievePosCom().execute().get();
                System.out.println(tmp);
            } catch (JSONException e)
            {
                Log.e("OlesRusApp", e.toString());
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            } catch (ExecutionException e)
            {
                e.printStackTrace();
            }

//            HashMap<Integer, LatLng> locations = new HashMap<Integer, LatLng>();
            ArrayList<LatLng> locations = new ArrayList<LatLng>();

            for (int i = 0; i < tmp.length(); i++)
            {
                try
                {
                    JSONObject jsonObject = tmp.getJSONObject(i);
//                    int id = jsonObject.getInt("id");
                    float lat = Float.parseFloat(jsonObject.get("lat").toString());
                    float lng = Float.parseFloat(jsonObject.get("lng").toString());
                    LatLng latLng = new LatLng(lat, lng);
//                    locations.put(id, latLng);
                    locations.add(latLng);
                } catch (JSONException e)
                {
                    Log.e("OlesRusApp", e.toString());
                }

            }

//            ArrayList<Integer> IDs = new ArrayList<Integer>() {{
//                add(0);
//                add(1);
//                add(5);
//            }};
//            final ArrayList<LatLng> locations = new ArrayList<LatLng>() {{
//                add(new LatLng(0, 0));
//                add(new LatLng(1, 2));
//                add(new LatLng(10, 10));
//            }};

            // Update the position in the Map
//            for (Map.Entry<Integer, Marker> entry : markers.entrySet())
//            {
//                int index = IDs.indexOf(entry.getKey());
//                if (index != -1)
//                {
//                    entry.getValue().setPosition(locations.get(index));
//                }
//            }
//            for (int i = 0; i < locations.size(); i++)
//            {
//                final int id = locations.keySet().get(i);

            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable()
            {
                @Override
                public void run()
                {
                    MapsActivity.getMapsActivity().getMap().clear();
                }
            });
            for (final LatLng latLng : locations)
            {
                Handler handler2 = new Handler(Looper.getMainLooper());
                handler2.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        MapsActivity.getMapsActivity().getMap().addMarker(new MarkerOptions().position(latLng));
                    }
                });
            }
/*            for (int id : locations.keySet())
            {
                final int Id = id;
//                final int j = i;
                final LatLng latLng = locations.get(id);
                Marker m = markers.get(id);
                if (m == null)
                {
//                    m = MapsActivity.getMapsActivity().getMap().addMarker(new MarkerOptions().position(new LatLng(0, 0)).title(id + ""));
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            markers.put(Id, MapsActivity.getMapsActivity().getMap().addMarker(new MarkerOptions().position(new LatLng(0, 0)).title(Id + "")));
                        }
                    });
//                    markers.put(id, m);
                }
                while(m == null)
                {
                    m = markers.get(id);
                }
                Handler handler =  new Handler(Looper.getMainLooper());
                handler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        markers.get(Id).setPosition(latLng);
                    }
                });
//                m.setPosition(locations.get(i));
            }*/

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
