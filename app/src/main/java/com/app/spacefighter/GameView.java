package com.app.spacefighter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by om on 11/6/18.
 */

public class GameView extends SurfaceView implements Runnable {

    private Thread game_thread=null;
    volatile boolean playing;
    private Paint paint;
    private player player1;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private Boom boom;

    private Friend friend;
    private Enemy[] enemies;
    private int enemyCount=3;

    int screenX,countMissed;
    int score,life;
    private boolean isGameOver;
    boolean flag;

    int highScore[] = new int[4];
    private SharedPreferences sharedPreferences;

    static MediaPlayer gameOnSound;
    MediaPlayer gameOver;
    MediaPlayer killedEnemy;

    Context context;

    private ArrayList<star> stars = new
            ArrayList<star>();

    public GameView(Game_Activity context, int screenX, int screenY){
        super(context);
        score = 0;
        life=3;
        this.screenX=screenX;
        countMissed=0;
        isGameOver=false;
        this.context=context;

        gameOnSound=MediaPlayer.create(context,R.raw.gameon);
        gameOver=MediaPlayer.create(context,R.raw.gameover);
        killedEnemy=MediaPlayer.create(context,R.raw.killedenemy);
gameOnSound.start();

        player1 = new player(context,screenX,screenY);

        surfaceHolder=getHolder();
        paint=new Paint();

        int starNums = 100;
        for (int i = 0; i < starNums; i++) {
            star s  = new star(screenX, screenY);
            stars.add(s);
        }

        enemies = new Enemy[enemyCount];

        for(int i=0; i<enemyCount; i++){
            enemies[i] = new Enemy(context, screenX, screenY);
        }

        boom=new Boom(context);


        friend=new Friend(context,screenX,screenY);

        sharedPreferences = context.getSharedPreferences("SCORE", Context.MODE_PRIVATE);

        highScore[0] = sharedPreferences.getInt("score1",0);
        highScore[1] = sharedPreferences.getInt("score2",0);
        highScore[2] = sharedPreferences.getInt("score3",0);
        highScore[3] = sharedPreferences.getInt("score4",0);


    }



    @Override
    public void run() {

        while (playing){
            update();
            draw();
            control();
        }

    }

    public void update(){

        boom.setX(-250); boom.setY(-250);

        player1.update();
        for (star s : stars) {
            s.update(player.getSpeed());}



        for(int i=0; i<enemyCount; i++){

            if(enemies[i].getX()==screenX)
                flag=true;

            enemies[i].update(player.getSpeed());

            if(Rect.intersects(player1.getDetectCollision(),enemies[i].getDetectCollision())){
                boom.setX(enemies[i].getX());
                boom.setY(enemies[i].getY());
killedEnemy.start();
                //countMissed++;
                score++;
                for(int ij=0;ij<4;ij++){
                    if(highScore[ij]<score){

                        final int finalI = ij;
                        highScore[ij] = score;
                        break;
                    }
                }
                SharedPreferences.Editor e = sharedPreferences.edit();
                for(int ij=0;ij<4;ij++){
                    int j = ij+1;
                    e.putInt("score"+j,highScore[ij]);
                }
                e.apply();

                // flag=false;

                      /*  if(countMissed==3){
                            playing=false;
                            isGameOver=true;
                        } */


                enemies[i].setX(-200);

            }

        }

        friend.update(player.getSpeed());

        if(Rect.intersects(player1.getDetectCollision(),friend.getDetectCollision())){
            boom.setX(friend.getX());
            boom.setY(friend.getY());

            if(flag){
                countMissed++; life--;
                // score++;
                flag=false;

                if(countMissed==3){
                    gameOnSound.stop();
                    gameOver.start();
                    playing=false;
                    isGameOver=true;
                }

            }
            // playing=false;
            //isGameOver=true;
            friend.setX(-200);
        }


    }
    public void draw(){


        if(surfaceHolder.getSurface().isValid()){
            canvas=surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);

            paint.setColor(Color.WHITE);

            //drawing all stars
            for (star s : stars) {
                paint.setStrokeWidth(s.getStarWidth());
                canvas.drawPoint(
                        s.getX(),
                        s.getY(),
                        paint
                );
            }

            canvas.drawBitmap(
                    player1.getBitmap(),
                    player1.getx(),
                    player1.gety(),
                    paint
            );

            for (int i = 0; i < enemyCount; i++) {
                canvas.drawBitmap(
                        enemies[i].getBitmap(),
                        enemies[i].getX(),
                        enemies[i].getY(),
                        paint
                );
            }
            canvas.drawBitmap(
                    friend.getBitmap(),
                    friend.getX(),
                    friend.getY(),
                    paint
            );

            canvas.drawBitmap(
                    boom.getBitmap(),
                    boom.getX(),
                    boom.getY(),
                    paint
            );

            paint.setTextSize(40);
            paint.setColor(Color.YELLOW);
            canvas.drawText("SCORE : "+score+"  LIFE : "+life,100,50,paint);

            if(isGameOver){
                paint.setTextSize(150);
                paint.setTextAlign(Paint.Align.CENTER);

                int yPos=(int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2));
                canvas.drawText("Game Over",canvas.getWidth()/2,yPos,paint);

                paint.setTextSize(70);

                paint.setColor(Color.WHITE);
                canvas.drawText("Your Score is "+score,canvas.getWidth()/2,yPos+90,paint);

                paint.setTextSize(50);

                paint.setColor(Color.CYAN);
                if(highScore[0]>score)
                canvas.drawText("High Score "+highScore[0],canvas.getWidth()/2,yPos+150,paint);
                else
                    canvas.drawText("High Score "+score,canvas.getWidth()/2,yPos+150,paint);
            }
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void control(){
        try {
            game_thread.sleep(17);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void pause(){
        playing=false;
        try {
            game_thread.join();
        }catch (InterruptedException e){
            // e.printStackTrace();
        }
    }

    public void resume(){
        playing=true;
        game_thread=new Thread(this);
        game_thread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_UP :
                //  player1.setX(player1.getx());
                //  player1.setY(player1.gety());
                player1.stopBoosting();
                break;

            case MotionEvent.ACTION_MOVE:
                // player1.stopBoosting();
                player1.setY((int) motionEvent.getY());
                break;

            case MotionEvent.ACTION_DOWN:

                //  player1.setX(player1.getx());
                //  player1.setY(player1.gety());
                player1.setBoosting();
                break;

        }

        if(isGameOver){
            if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                context.startActivity(new Intent(context,MainActivity.class));
            }
        }
        return true;
    }

    public static void stopMusic(){
        gameOnSound.stop();
    }
}
