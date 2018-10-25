package com.tsprogramming.galaga;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.tsprogramming.galaga.GameView.dWidth;

/**
 * Created by Tskulley on 9/28/2018.
 */

public class Missile {

    private int y,x;
    int missileVelocity;
    Bitmap missile;
    Rect mHitbox;


    public Missile(Context context) {
        y= 1375;
        missileVelocity = 10;
        x = this.x;
        missile = BitmapFactory.decodeResource(context.getResources(), R.drawable.missle);
        mHitbox = new Rect(x, y, missile.getWidth(), missile.getHeight());
        mHitbox.bottom = y + missile.getHeight();
        mHitbox.left = x;
        mHitbox.right = x + missile.getWidth();
        mHitbox.top = y;
    }

    public int getY() {
        return y -= missileVelocity;
    }

    public int getMissileVelocity() {
        return missileVelocity;
    }

    public Bitmap getBitmap() {
        return missile;
    }

    public Rect getHitbox() {
        return mHitbox;
    }
}
