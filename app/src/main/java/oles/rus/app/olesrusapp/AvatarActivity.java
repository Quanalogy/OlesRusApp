package oles.rus.app.olesrusapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;


public class AvatarActivity extends Activity {

    private static User user = new User(0);
    private int avatarNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);

        Intent intent = getIntent();
        String studynumber = intent.getStringExtra(MyActivity.EXTRA_MESSAGE);

        try
        {
            user = new LoginCom().execute(studynumber).get();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        }

        TextView textView = (TextView) findViewById(R.id.name);
        textView.setText(user.getName());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.avatar, menu);
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

    public static void setUser(User user1)
    {
        user = user1;
    }

    public static User getUser()
    {
        return user;
    }

    public void selectAvatar1(View view)
    {
        avatarNumber = 0;
        ImageView view1 = (ImageView) findViewById(R.id.avatar1);
        ImageView view2 = (ImageView) findViewById(R.id.avatar2);

        view1.setImageResource(R.drawable.avatar1_selected);
        view2.setImageResource(R.drawable.avatar2);

        Log.i("OlesRusApp", avatarNumber + "");
    }

    public void selectAvatar2(View view)
    {
        avatarNumber = 1;
        ImageView view1 = (ImageView) findViewById(R.id.avatar1);
        ImageView view2 = (ImageView) findViewById(R.id.avatar2);

        view1.setImageResource(R.drawable.avatar1);
        view2.setImageResource(R.drawable.avatar2_selected);

        Log.i("OlesRusApp", avatarNumber + "");
    }

    public void selectAvatar(View view)
    {
        avatarNumber = view.getId();

        ((ImageView) findViewById(R.id.avatar1)).setAlpha(1f);
        ((ImageView) findViewById(R.id.avatar2)).setAlpha(1f);
        ((ImageView) findViewById(R.id.avatar3)).setAlpha(1f);
        ((ImageView) findViewById(R.id.avatar4)).setAlpha(1f);
        ((ImageView) findViewById(R.id.avatar5)).setAlpha(1f);

        view.setAlpha(0.5f);
    }

    public void sendSelectAvatar(View view)
    {
        //TODO: Set the avatar. Maybe send to the server?

        //TODO: Go to next activity
        Intent intent = new Intent(this, IntroductionActivity.class);
        startActivity(intent);
    }
}
