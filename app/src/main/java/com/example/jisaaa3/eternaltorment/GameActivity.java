package com.example.jisaaa3.eternaltorment;

import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;

public class GameActivity extends Activity {
    private GameView mGameView;
    private GameModel mGameModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);
        mGameModel = new GameModel();
        mGameView = new GameView(this, size.x, size.y, mGameModel);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            //    mGameView.setBackground(getResources().getDrawable(R.drawable.dungeon_background));
        }

        setContentView(mGameView);

        mGameView.draw();

        /*
            Inflate view with xml sheet. Canvas = mGameView
            then put buttons on top then add ongestureliseners
         */
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGameView.resume();
    }
}
