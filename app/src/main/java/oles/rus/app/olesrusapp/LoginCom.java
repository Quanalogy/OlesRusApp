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
public class LoginCom extends AsyncTask<String, Void, User>
{
    private static String baseURL = "http://pvc.archan.dk/";

    public static User login(String studynumber)
    {
        try
        {

            //TODO: Validate studynumber

            //Send studynumber to server
            JSONObject jsonStudynumber = new JSONObject();
            jsonStudynumber.put("rusNumber", Integer.parseInt(studynumber));
            JSONObject jsonUser = sendToUrl("login", jsonStudynumber);

            //Return User
            return new User(Integer.parseInt(jsonUser.get("userId").toString()), Integer.parseInt(jsonUser.get("groupId").toString()));

        } catch (JSONException e)
        {
            Log.e("OlesRusApp", e.toString());
        }

        return null;
    }

    private static JSONObject sendToUrl(String addedURL, JSONObject params)
    {
        HttpClient httpClient = new DefaultHttpClient();

        try
        {
            HttpPost request = new HttpPost(baseURL + addedURL);
            StringEntity params2 = new StringEntity(params.toString());
            request.addHeader("content-type", "application/json");
            request.setEntity(params2);
            HttpResponse response = httpClient.execute(request);

            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                InputStream inputStream = entity.getContent();
                String result = convertStreamToString(inputStream);
                return new JSONObject(result);
            }


        } catch (ClientProtocolException e)
        {
            Log.e("OlesRusApp", e.toString() + "\n" + Log.getStackTraceString(e));
        } catch (UnsupportedEncodingException e)
        {
            Log.e("OlesRusApp", e.toString() + "\n" + Log.getStackTraceString(e));
        } catch (IOException e)
        {
            Log.e("OlesRusApp", e.toString() + "\n" + Log.getStackTraceString(e));
        } catch (JSONException e)
        {
            Log.e("OlesRusApp", e.toString() + "\n" + Log.getStackTraceString(e));
        }

        return null;
    }

    private static String convertStreamToString(InputStream is)
    {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try
        {
            while ((line = reader.readLine()) != null)
            {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e)
        {
            Log.e("OlesRusApp", e.toString());
        } finally
        {
            try
            {
                is.close();
            } catch (IOException e)
            {
                Log.e("OlesRusApp", e.toString());
            }
        }
        return sb.toString();
    }

    @Override
    protected User doInBackground(String... strings)
    {
        return LoginCom.login(strings[0]);
    }
}
