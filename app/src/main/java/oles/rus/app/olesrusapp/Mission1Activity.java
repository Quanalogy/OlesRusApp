package oles.rus.app.olesrusapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Mission1Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission1);

        Typewriter typewriter = (Typewriter) findViewById(R.id.mission1intro);
        typewriter.setCharacterDelay(75);
        typewriter.animateText(
                "Du er blevet udvalgt til at være rationeringsansvarlig for Katrinebjerggruppen, du skal derfor gå til rationeringscentralen og hente drikkevare, så dit taskforce kan overleve på jeres hårde og udfordrende missioner.\n" +
                "Når du har fundet drikkevarer skal du scanne dem med telefonen, før du køber dem.\n"
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mission1, menu);
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
        Intent intent = new Intent(this, Mission1_2Activity.class);
        startActivity(intent);
    }
}
