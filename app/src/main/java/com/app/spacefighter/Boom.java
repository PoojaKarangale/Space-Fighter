package com.app.spacefighter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Context;

/**
 * Created by om on 14/6/18.
 */

public class Boom {

    private int x,y;
    private Bitmap bitmap;

    public Boom(Context context){
        bitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.boom);

        x=-250; y=-250;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
