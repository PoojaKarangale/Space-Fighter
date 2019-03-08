package com.app.spacefighter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by om on 19/6/18.
 */

public class Friend {

    private int x,y,maxX,maxY,minX,minY,speed=1;
    private Bitmap bitmap;
    private Rect detectCollision;

    public Friend(Context context,int screenx,int screeny){

        bitmap= BitmapFactory.decodeResource(
                context.getResources(),
                R.drawable.friend1
        );

maxX=screenx; maxY=screeny; minX=0; minY=0;
        Random generator=new Random();
        speed=generator.nextInt(6)+10;
        x=screenx+75;
        y=generator.nextInt(maxY)-bitmap.getHeight()+50;

        detectCollision=new Rect(x,y,bitmap.getWidth(),bitmap.getHeight());

    }

    public void update(int playerspeed){
        x-=playerspeed;
        x-=speed;
        if(x<minX-bitmap.getWidth()) {
            Random generator = new Random();
            speed = generator.nextInt(10) + 10;
            x = maxX;
            y = generator.nextInt(maxY) - bitmap.getHeight();
        }
        detectCollision.left=x;
        detectCollision.right=x+bitmap.getWidth();
        detectCollision.top=y;
        detectCollision.bottom=y+bitmap.getHeight();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Rect getDetectCollision() {
        return detectCollision;
    }

    public void setX(int x) {
        this.x = x;
    }
}
