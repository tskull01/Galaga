package com.tsprogramming.galaga;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;



/**
 * Created by Tskulley on 9/23/2018.
 */

public class Alien {

    Bitmap alien;
    private Context context;
    int alienX, alienY, velocity;
    Rect aHitbox;

    public Alien(Context context , int x, int y) {
 alien = BitmapFactory.decodeResource(context.getResources(),R.drawable.alien);
        this.context = context;
        alienX = x;
        alienY = y;
        velocity = 10;
        aHitbox = new Rect(alienX, alienY, alien.getWidth(), alien.getHeight());
        aHitbox.bottom = y + alien.getHeight();
        aHitbox.left = x;
        aHitbox.right = x + alien.getWidth();
        aHitbox.top = y;
    }
    public int getWidth(){
        return alienX;
    }

    public int getHeight(){
        return alienY;
    }

    public void destroy(){
        alienX = alienX + 10000;
    }

    public Bitmap getBitmap(){
        return alien;
    }

    public Rect getHitbox() {
        return aHitbox;
    }
}
