package com.app.spacefighter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class high_score_Activity extends AppCompatActivity {
    TextView highText,highText2,highText3,highText4;

    int score;

    private Button btn_try;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score_);



        highText=(TextView)findViewById(R.id.high_text);
        highText2=(TextView)findViewById(R.id.high_text2);
        highText3=(TextView)findViewById(R.id.high_text3);
        highText4=(TextView)findViewById(R.id.high_text4);

        btn_try=(Button)findViewById(R.id.btn_try);

        sharedPreferences=getSharedPreferences("SCORE", Context.MODE_PRIVATE);

         highText.setText("1 - "+sharedPreferences.getInt("score1",0));
        highText2.setText("2 - "+sharedPreferences.getInt("score2",0));
        highText3.setText("3 - "+sharedPreferences.getInt("score3",0));
        highText4.setText("4 - "+sharedPreferences.getInt("score4",0));



        btn_try.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       // text_high.setText(score);
                      Intent i=new Intent("com.app.spacefighter.MainActivity");
                          startActivity(i);

                    }
                }
        );


    }
}
