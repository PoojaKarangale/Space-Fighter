package com.app.spacefighter;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

/**
 * Created by om on 11/6/18.
 */

public class player {

    private Bitmap bitmap;
    private int x;
    private int y;
    private static int speed=0;

    private boolean boosting;
    private final int GRAVITY=-10;
    private int miny,maxy;
    private final int MAX_SPEED=20,MIN_SPEED=1;
private Rect detectCollision;

    public player(Context context, int screenX, int screenY){
        x =75;
        y =50;
        speed=1;

        bitmap=BitmapFactory.decodeResource(context.getResources(),R.drawable.player);

        maxy=screenY-bitmap.getHeight();
        miny=0;

   boosting=false;

   detectCollision=new Rect(x,y,bitmap.getWidth(),bitmap.getHeight());
    }
public void setBoosting(){
        boosting=true;
}

public void stopBoosting(){
    boosting=false;
}


    public void update(){
        if(boosting)
            speed += 2;
        else
            speed -=5;

        if(speed>MAX_SPEED) speed=MAX_SPEED;
        if(speed<MIN_SPEED) speed=MIN_SPEED;

        y -= speed + GRAVITY;
        if(y<miny) y=miny;
        if(y>maxy) y=maxy;

        detectCollision.left=x;
        detectCollision.top=y;
        detectCollision.right=x+bitmap.getWidth();
        detectCollision.bottom=y+bitmap.getHeight();
    }




    public int getx(){
        return x;
    }
    public int gety(){
        return y;
    }
    public Bitmap getBitmap(){
        return bitmap;
    }
    public static int getSpeed(){
        return speed;
    }

    public Rect getDetectCollision() {
        return detectCollision;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
