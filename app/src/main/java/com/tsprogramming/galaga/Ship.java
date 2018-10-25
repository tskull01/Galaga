package com.tsprogramming.galaga;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;

import static com.tsprogramming.galaga.GameView.dWidth;

/**
 * Created by Tskulley on 10/22/2018.
 */

public class Ship {
    Bitmap ship;
    int shipX, shipY;
    Point shipPosition;
    Rect hitbox;

    public Ship(Context context) {
        ship = BitmapFactory.decodeResource(context.getResources(), R.drawable.ship);
        shipX = GameView.dWidth/2 - ship.getWidth()/2;
        shipY= 1375;
        shipPosition = new Point();
        shipPosition.x = ship.getWidth()*2;
        shipPosition.y = ship.getHeight()/2;
        hitbox = new Rect(shipX, shipY, ship.getWidth(), ship.getHeight());
        hitbox.bottom = shipY + ship.getHeight();
        hitbox.left = shipX;
        hitbox.right = shipX + ship.getWidth();
        hitbox.top = shipY;
    }

    public void setShipX(int shipX) {
        this.shipX = shipX;
    }

    public Rect getHitbox() {
        return hitbox;
    }

    public Bitmap getShip() {
        return ship;
    }

    public int getShipX() {
        return shipX;
    }

    public int getShipY() {
        return shipY;
    }
}
