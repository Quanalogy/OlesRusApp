package oles.rus.app.olesrusapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MyActivity extends Activity {

    public final static String EXTRA_MESSAGE = "oles.rus.app.olesrusapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Typewriter typewriter = (Typewriter) findViewById(R.id.typeWriter);
//        typewriter.setCharacterDelay(150);
//        typewriter.animateText("Hello World.\nHow are you?");
        typewriter.animateText("Hello World.\n" +
                "How are you?");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    public void sendStudyNumber(View view)
    {
        // TODO: Validate the studynumber. Go to the map-view.
        EditText studynumber = (EditText) findViewById(R.id.studyNumber);
        String message = studynumber.getText().toString();

        Log.i("OlesRusApp", new LoginCom().execute(message).toString());

        Intent intent = new Intent(this, ScanningActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
