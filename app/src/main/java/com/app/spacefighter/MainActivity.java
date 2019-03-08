package com.app.spacefighter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Toast backToast;
    private long backPressedTime;
    ImageButton play_btn,high_btn;
    GameView gameView;
    int x,y;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        play_btn=(ImageButton)findViewById(R.id.play_button);

        high_btn=(ImageButton)findViewById(R.id.high_button);

        // gameView=new GameView((Game_Activity) context, x, y);

        play_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i=new Intent("com.app.spacefighter.Game_Activity");
                        startActivity(i);

                    }
                }
        );

        high_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i=new Intent("com.app.spacefighter.high_score_Activity");
                        // i.putExtra("SCORE",gameView.score);
                        startActivity(i);
                    }
                }
        );


    }

    @Override
    public void onBackPressed() {
        if(backPressedTime+2000>System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }else                 //getBaseContextText();
        { backToast= Toast.makeText(MainActivity.this,"To exit press back-Button again",Toast.LENGTH_SHORT);
        backToast.show();}
        backPressedTime=System.currentTimeMillis();
    }
}
