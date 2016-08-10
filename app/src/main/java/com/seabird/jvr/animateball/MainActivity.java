package com.seabird.jvr.animateball;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
{
    private int width=800, height=800;
    private Canvas c;
    private float x=463,y=743,vx=1,vy=1,r=30;
    private ImageView imageview;
    private Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Timer timer=new Timer();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        c = new Canvas(b);
        c.drawColor(Color.WHITE);

        paint = new Paint();
        paint.setAntiAlias(false);
        paint.setStyle(Paint.Style.FILL);

        imageview=(ImageView) findViewById(R.id.imageView);
        imageview.setImageBitmap(b);

        timer.schedule(
                new TimerTask()
                {
                    @Override
                    public void run()
                    {
                        runOnUiThread
                        (
                            new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    update();
                                }
                            }
                        );
                    }
                },0,10 );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    void update()
    {
        paint.setColor(Color.WHITE);
        c.drawCircle(x, y, r, paint);
        x=x+vx;
        y=y+vy;
        if(x+r>=width)vx=-vx;
        if(x-r<=0)vx=-vx;
        if(y+r>=height)vy=-vy;
        if(y-r<=0)vy=-vy;
        paint.setColor(Color.RED);
        c.drawCircle(x, y, r, paint);
        imageview.invalidate();
    }
}
