package oles.rus.app.olesrusapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Mission2_2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission2_2);

        Typewriter typewriter = (Typewriter) findViewById(R.id.mission2_2);
        typewriter.setCharacterDelay(75);
        typewriter.animateText(
                "“Ved printeren ved lokale 0xx(indsæt rigtig lokalenr), skal du scanne QR-koden for at for at hacke på første niveau og ved lokale 1xx (informationskontoret), skal du scanne QR-koden for at hacke på andet niveau af bygningen.” \n"
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mission2_2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void goOn(View view)
    {
        Intent intent = new Intent(this, Mission2_3Activity.class);
        startActivity(intent);
    }
}
