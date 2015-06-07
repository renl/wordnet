package com.renlore.wordnet;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;


public class WordNet extends Activity {

    private final static String TAG = WordNet.class.getSimpleName();
    private float scaleX, scaleY;
    private final static int baseX = 1080;
    private final static int baseY = 1920;
    private final static float baseProportion = 1920 / 1080;
    public static Context context;
    public static MainGamePanel gamePanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        int screenWidth = display.widthPixels;
        int screenHeight = display.heightPixels;
        float screenProportion =  (float)screenHeight / (float)screenWidth;

        Log.d(TAG, "Width: " + screenWidth + ", Height: " + screenHeight + ", Proportion: " + screenProportion);
        this.context = this;
        gamePanel = new MainGamePanel(this);
        setContentView(gamePanel);
//        setContentView(R.layout.activity_word_net);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_word_net, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
