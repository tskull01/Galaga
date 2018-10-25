package com.tsprogramming.galaga;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;


/**
 * Created by Tskulley on 9/22/2018.
 */

public class GameView extends View implements GestureDetector.OnGestureListener{


    final int UPDATE_MILLIS = 16;
    final int UPDATE_SHOOT = 16;
    Handler handler;
    Runnable runnable;
    Bitmap background[] = new Bitmap[10];
    Display display;
    Rect rect;
    static int dWidth, dHeight;
    Point point;
    int frameNumber;
    Ship ship;
    private Alien[] aliens = new Alien[10];
    private GestureDetector detector;
    Bitmap missile;
    boolean gameState = false;
    boolean shooting = false;
    int missileX;
    Rect aHitbox;
    private ArrayList<Missile> missiles = new ArrayList<Missile>(50);
    public GameView(Context context) {
        super(context);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };

        background[0] = BitmapFactory.decodeResource(getResources(),R.drawable.game_background_1);
        background[1] = BitmapFactory.decodeResource(getResources(),R.drawable.game_background_2);
        background[2] = BitmapFactory.decodeResource(getResources(),R.drawable.game_background_3);
        background[3] = BitmapFactory.decodeResource(getResources(),R.drawable.game_background_4);
        background[4] = BitmapFactory.decodeResource(getResources(),R.drawable.game_background_5);
        background[5] = BitmapFactory.decodeResource(getResources(),R.drawable.game_background_6);
        background[6] = BitmapFactory.decodeResource(getResources(),R.drawable.game_background_7);
        background[7] = BitmapFactory.decodeResource(getResources(),R.drawable.game_background_8);
        background[8] = BitmapFactory.decodeResource(getResources(),R.drawable.game_background_9);
        background[9] = BitmapFactory.decodeResource(getResources(),R.drawable.game_background_10);
        missile = BitmapFactory.decodeResource(context.getResources(), R.drawable.missle);
        ship = new Ship(context);
        display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
        point = new Point();
        display.getSize(point);
        dWidth = point.x;
        dHeight = point.y;
        rect = new Rect(0,0,dWidth, dHeight);
        missileX = ship.getShipX() + 110;
        for(int i =0; i < aliens.length; i++){
            aliens[i] = new Alien(context, i*100+300 , 300);
           aHitbox = aliens[i].getHitbox();
        }
        frameNumber = 0;
        handler = new Handler();
        detector = new GestureDetector(context, this);
        ship.setShipX(dWidth/2 - ship.ship.getWidth()/2);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    canvas.drawBitmap(background[frameNumber],null,rect,null);
       Task task = new Task();
       task.execute();

        canvas.drawBitmap(ship.getShip(),ship.shipX,ship.shipY,null);
        //handler.postDelayed(runnable, UPDATE_MILLIS);

        for (int i = 0; i < 10; i++) {
                canvas.drawBitmap(aliens[i].getBitmap(), aliens[i].alienX, aliens[i].alienY, null);
        }
        for (int i = 0; i < missiles.size(); i++) {
            handler.postDelayed(runnable, UPDATE_SHOOT);
            canvas.drawBitmap(missiles.get(i).getBitmap(), ship.getShipX() + 110, missiles.get(i).getY(), null);
        if(missiles.get(i).getHitbox().top == aHitbox.bottom){
            missiles.remove(i);
            aliens[i].destroy();
        }
        }


    }



    @Override public boolean onTouchEvent(MotionEvent motionEvent) {
        float touchX = motionEvent.getX();
        float touchY = motionEvent.getY();
        int action = motionEvent.getAction();
        detector.onTouchEvent(motionEvent);
        if (action == MotionEvent.ACTION_DOWN) {
            if (touchX >= (dWidth / 2 - ship.getShip().getWidth() / 2) && touchX <= (dWidth / 2 + ship.getShip().getWidth() / 2) && touchY >= (dHeight - ship.getShip().getHeight())) {
                Log.i("Ship", "is tapped");
                shooting = true;
                for (int i = 0; i < 1000; i++) {
                    missiles.add(new Missile(getContext()));
                }
            }
        }
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float flingX = e1.getX();
        float flingY = e1.getY();
        float fling2X = e2.getX();
        float fling2Y = e2.getY();
        if(flingX > fling2X) {
            if (flingX > ship.getShipX()) {
                ship.shipX = ship.getShipX() + 50;
                Log.i("Ship", "++");
            }
        }else if(flingX < fling2X) {
            if (flingX < ship.getShipX()) {
                Log.i("Ship", "--");
                ship.shipX = ship.getShipX() - 50;
            }
        }
        return true;
    }
    private class Task extends AsyncTask<Integer,Integer,Integer>{
        @Override
        protected Integer doInBackground(Integer... integers) {

           frameNumber++;
            handler.postDelayed(runnable, UPDATE_MILLIS);
            if(frameNumber >= 9){
                frameNumber = 0;
            }
           return null;
        }
    }
}


