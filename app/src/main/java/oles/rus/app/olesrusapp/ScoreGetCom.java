package oles.rus.app.olesrusapp;


import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by borimino on 10/2/14.
 */
public class ScoreGetCom extends AsyncTask<Void, Void, Integer>
{
    private static StringBuilder stringBuilder = new StringBuilder();
    private static String baseURL = "http://pvc.archan.dk/";
    private static User user;

    public static Integer login()
    {
        try
        {

            //TODO: Validate studynumber

            //Send studynumber to server
            JSONObject jsonStudynumber = new JSONObject();
//            jsonStudynumber.put("rusNumber", Integer.parseInt(studynumber));
            JSONObject jsonUser = sendToUrl("/points");

            Log.i("OlesRusApp", jsonUser.toString());

            //Return User
            user = new User(Integer.parseInt(jsonUser.get("id").toString()), jsonUser.getString("name").toString());
//            AvatarActivity.setUser(user);
            return 0;

        } catch (JSONException e)
        {
            Log.e("OlesRusApp", e.toString());
        }

        return null;
    }

    private static JSONObject sendToUrl(String addedURL)
    {
        HttpClient httpClient = new DefaultHttpClient();

        try {
            HttpPost request = new HttpPost(baseURL + addedURL);
//            StringEntity params2 = new StringEntity(params.toString());
//            request.addHeader("content-type", "application/json");
//            request.setEntity(params2);
            HttpResponse response = httpClient.execute(request);

            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                InputStream inputStream = entity.getContent();
                String result = convertStreamToString(inputStream);
                System.out.println(result); //DEBUG
                JSONObject jsonObject = new JSONObject(result);
                return jsonObject;
            }


        } catch (ClientProtocolException e)
        {
            Log.e("OlesRusApp", e.toString());
        } catch (UnsupportedEncodingException e)
        {
            Log.e("OlesRusApp", e.toString());
        } catch (IOException e)
        {
            Log.e("OlesRusApp", e.toString());
        } catch (JSONException e)
        {
            Log.e("OlesRusApp", e.toString());
        }

        return null;
    }

    private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            Log.e("OlesRusApp", e.toString());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                Log.e("OlesRusApp", e.toString());
            }
        }
        return sb.toString();
    }

    @Override
    protected Integer doInBackground(Void... voids)
    {
        return ScoreGetCom.login();
    }
}
