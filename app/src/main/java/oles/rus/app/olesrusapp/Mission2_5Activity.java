package oles.rus.app.olesrusapp;

import android.os.Bundle;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.Mission2_5ActivityInterface;
import com.mygdx.game.graphicsDemo;


public class Mission2_5Activity extends AndroidApplication implements Mission2_5ActivityInterface
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT).show();
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(new graphicsDemo(this), config);
    }

    @Override
    public void WonGame()
    {

    }

    @Override
    public void LostGame()
    {

    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.mission2_5, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
