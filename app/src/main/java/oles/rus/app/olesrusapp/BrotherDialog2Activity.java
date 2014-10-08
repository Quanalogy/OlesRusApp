package oles.rus.app.olesrusapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class BrotherDialog2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brother_dialog2);

        Typewriter typewriter = (Typewriter) findViewById(R.id.brotherTypeWriter);

        typewriter.setCharacterDelay(75);
        typewriter.animateText(
                "Vælger du at tilfangetage din bror og sikre dig en plads hos MfS \n" +
                "eller \n" +
                "Vælger du at sætte din bror fri, få deaktiveret trackerchippen og igen blive en del af Katrinebjerggruppen \n"
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.brother_dialog2, menu);
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

    public void setFree(View view)
    {
        //TODO: Select side
        new SelectSideCom().execute(AvatarActivity.getUser().getUserId());

        //TODO: Go to next activity
        Intent intent = new Intent(this, Mission1Activity.class);
        startActivity(intent);
    }
}
