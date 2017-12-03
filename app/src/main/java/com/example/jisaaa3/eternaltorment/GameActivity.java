package com.example.jisaaa3.eternaltorment;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.SurfaceView;
import android.widget.Button;

public class GameActivity extends Activity {

    public GameModel mGameModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);
        mGameModel = new GameModel();
        //mGameModel.spriteList.add(new Skeleton());


        //mGameView = new GameView(this, size.x, size.y, mGameModel);


        setContentView(R.layout.activity_game);

    }

    /*
        To pause the game when the activity is no longer in view
     */
    @Override
    protected void onPause() {
        super.onPause();
        //mGameView.pause();
    }

    /*
        To resume the game when the activity is back running
     */
    @Override
    protected void onResume() {
        super.onResume();
        //mGameView.resume();
    }
}
