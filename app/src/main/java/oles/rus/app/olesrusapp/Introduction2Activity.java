package oles.rus.app.olesrusapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Introduction2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction2);

        Typewriter typewriter = (Typewriter) findViewById(R.id.introduction_terminal);

        typewriter.setCharacterDelay(75);
        typewriter.animateText(
                        "“For at finde din broder skal du løbe rundt i Katrinebjerg-området. Du vil kunne se ham på dit kort, når du er indenfor 50m af ham.”"
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

        Intent intent = new Intent(this, FindBrotherActivity.class);
        finish();
        startActivity(intent);
    }
}
