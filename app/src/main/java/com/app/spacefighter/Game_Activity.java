package com.app.spacefighter;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;

public class Game_Activity extends AppCompatActivity {
private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_);



        Display display = getWindowManager().getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);

        gameView=new GameView(this,size.x,size.y);
        setContentView(gameView);

    }

    public void onPause(){
        super.onPause();
        gameView.pause();
    }

    public void onResume(){
        super.onResume();
        gameView.resume();
    }
}
