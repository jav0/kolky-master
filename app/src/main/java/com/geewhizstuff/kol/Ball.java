package com.geewhizstuff.kol;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import java.lang.Math;

/**
 * Created by sapanbhatia on 10/28/16.
 */

public class Ball  {
    public ImageView fishImageView;
    public View v;
    public double radiusX, radiusY;
    public double velX, velY;
    private int x,y;
    private boolean active;
    public double centerX, centerY;
    public double theta;
    public double gravity;

    Activity act;
    public int orientation;

    public Ball(Context context, ViewGroup vg, int startX, int startY) {
        // this.topView = vg;
        RelativeLayout rl = (RelativeLayout) vg.findViewById(R.id.playground);
        // View fishView = View.inflate(context, R.layout.fish, rl);

        act = (Activity) context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

        View fv = inflater.inflate(R.layout.ball,null);
        for(int index=0; index<((ViewGroup)fv).getChildCount(); ++index) {
            View nextChild = ((ViewGroup)fv).getChildAt(index);
            if (nextChild instanceof ImageView) {
                fishImageView = (ImageView) nextChild;
            }
        }

        //fv.setBackgroundColor(0xffffff);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
        params.leftMargin = startX;
        params.topMargin = startY;
        rl.addView(fv,params);
        v = fv;
        centerX=startX;
        centerY=startY;
        velX = 0;
        velY = 0;
        x = (int) (centerX);
        y = (int) (centerY);
        gravity = 20000;
        active = false;

    }

    public void update_position() {

        double a, ax, ay;
        double r;
        int lx, ly;
        //lx = x - (int)centerX;
        //ly = y - (int)centerY;
        if (active) {
            theta = Math.atan(radiusY / radiusY);
            r = Math.sqrt(radiusX * radiusX + radiusY * radiusY);
            a = gravity / (r * r);
            ay = a * Math.cos(theta);
            ax = (-1) * a * Math.sin(theta);

            velX += ax;
            velY += ay;
            //Log.d("INFO", "Vx: " + velX + " Vy: " + velY + " r: " + r + " a: " + a + " ax: " + ax + " ay: " + ay + " Rx: " + radiusX + " Ry: " + radiusY + " Th: " + Math.toDegrees(theta));

            radiusX += velX;
            radiusY += velY;

            x = (int) (radiusX + centerX);
            y = (int) (radiusY + centerY);
        }
        /*velX += Math.sin(Math.toRadians(theta*gravity));
        double Xradians = Math.toRadians(theta);
        x = (int) (centerX + maxRadius * Math.sin(Xradians));
        theta += velX;
        velX *= resist;

        velY += Math.sin(Math.toRadians(alpha*gravity));
        double Yradians = Math.toRadians(alpha);
        y = (int) (centerY + maxRadius * Math.sin(Yradians));
        alpha += velY;
        velY *= resist;*/

        //y = (int) centerY;
        //x = (int) centerX;



        act.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
                params.leftMargin = (int)x;
                params.topMargin = (int)y;

                v.setLayoutParams(params);

            }
        });

    }
    public void activate() {
        active = true;
    }
    public void setCoord (int newX, int newY) {
        x = newX;
        y = newY;
        radiusX = x - centerX;
        radiusY = y - centerY;
    }
}
