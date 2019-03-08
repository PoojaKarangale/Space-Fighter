package com.app.spacefighter;
import java.util.Random;

/**
 * Created by om on 12/6/18.
 */

public class star {

    private int x,y,minX,minY,maxX,maxY,speed;

    public star(int screenX,int screenY){
        minX=0;
        minY=0;

        maxX=screenX;
        maxY=screenY;

        Random generator=new Random();
        speed=generator.nextInt(10);

        x=generator.nextInt(maxX);
        y=generator.nextInt(maxY);
    }

    public void update(int playerspeed){
        x-=playerspeed;
        x-=speed;

        if(x<0){
            x=maxX;
            Random generator=new Random();
            y=generator.nextInt(maxY);
            speed=generator.nextInt(15);
        }
    }

    public float getStarWidth(){
        float minX = 1.0f;
        float maxX = 4.0f;

        Random rand=new Random();
        float finalX = rand.nextFloat() * (maxX - minX) + minX;
        return finalX;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
