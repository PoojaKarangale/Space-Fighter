package com.app.spacefighter;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.Rect;
import java.util.*;

/**
 * Created by om on 12/6/18.
 */

public class Enemy {

private int x,y,maxX,maxY,minX,minY,speed=1;
private Bitmap bitmap;

private Rect detectCollision;

public Enemy(Context context,int screenX,int screenY){
    bitmap=BitmapFactory.decodeResource(context.getResources(),R.drawable.enemy);
 maxX=screenX;
 maxY=screenY;
 minX=0;
 minY=0;


 Random generator=new Random();
 speed=generator.nextInt(6)+10;
 x=screenX;
 y=generator.nextInt(maxY)-bitmap.getHeight();

 detectCollision=new Rect(x,y,bitmap.getWidth(),bitmap.getHeight());

}
public void update(int playerspeed){
    x-=playerspeed;
    x-=speed;
    if(x<minX-bitmap.getWidth()){
        Random generator=new Random();
        speed=generator.nextInt(10)+10;
        x=maxX;
        y=generator.nextInt(maxY)-bitmap.getHeight();
    }

    detectCollision.left=x;
    detectCollision.top=y;
    detectCollision.right=x+bitmap.getWidth();
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

    public void setX(int x) {
        this.x = x;
    }

    public Rect getDetectCollision() {
        return detectCollision;
    }
}
