package oles.rus.app.olesrusapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class IntroductionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        Typewriter typewriter = (Typewriter) findViewById(R.id.introduction_terminal);

        typewriter.setCharacterDelay(75);
        typewriter.animateText("klokkeslet + Dags dato + 2084:\n" +
                        "Afsender: Comrade Ivan Ivanovitch - General Red Army intelligence\n" +
                        "\n" +
                        "“Vi fra den røde hær har udvalgt dig, //navn, til at undgå din dødsstraf, mod at du fanger din bror //brors navn. Vi har i mange år har forsøgt at fange ham, da han formidler den ulovlige viden! Du udvælges til dette da det er den eneste måde at fange ham på - nu tid til at du, //navn, viser dit værd overfor den røde hær og fanger ham! Klarer du dette belønnes du med optagelse i Ministieriet for Statssikkerhed (MfS) men fejler du, straffes du med døden!”\n"
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.introduction, menu);
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
        Typewriter typewriter = (Typewriter) findViewById(R.id.introduction_terminal);
//        typewriter.setCharacterDelay(0);
        typewriter.finish();

//        int i = 1000;
//        while (i > 0)
//        {
//            try
//            {
//                Thread.sleep(1);
//            } catch (InterruptedException e)
//            {
//                e.printStackTrace();
//            }
//        }

        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        Intent intent = new Intent(this, Introduction2Activity.class);
        startActivity(intent);
    }
}
