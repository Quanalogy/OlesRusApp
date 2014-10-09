package oles.rus.app.olesrusapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class BrotherDialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brother_dialog);

        Typewriter typewriter = (Typewriter) findViewById(R.id.brotherTypeWriter);

        typewriter.setCharacterDelay(75);
        typewriter.animateText(
                "*Du fanger din bror Michael Jensen*\n" +
                "\n" +
                "“Jeg er tvunget til at tilfangetage dig, da jeg ellers vil blive henrettet for mine forbrydelser. “\n" +
                "\n" +
                "Bror: “Nej, Stop! Du kan tage med os, Katrinebjerggruppen, som du en gang var en del af!”\n" +
                "\n" +
                "“Men jeg har en chip i armen, så MfS altid kan finde mig igen!”\n" +
                "\n" +
                "Bror: “Vores forskningsgruppe har fundet en måde at deaktivere dem på. Hvis du slipper mig fri kan du blive en del af os igen og få deaktiveret chippen!”\n"
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.brother_dialog, menu);
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
        Intent intent = new Intent(this, BrotherDialog2Activity.class);
        startActivity(intent);
    }
}
