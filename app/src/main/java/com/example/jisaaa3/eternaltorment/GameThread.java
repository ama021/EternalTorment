package com.example.jisaaa3.eternaltorment;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by ama02 on 12/2/2017.
 */

public class GameThread extends Thread{
    private GameView mGameView;
    private SurfaceHolder mSurfaceHolder;
    private GameModel mGameModel;


    private boolean isRunning = false;
    private boolean mPlaying = true;

    public GameThread(GameView gameView, GameModel gameModel) {
        this.mGameView = gameView;
        this.mSurfaceHolder = gameView.getHolder();
        this.mGameModel = gameModel;
    }

    @Override
    public void run() {
        Canvas canvas = null;
        while (mPlaying) {
            //mGameModel.update((long) -1);

            if (mSurfaceHolder.getSurface().isValid()) {
                canvas = mSurfaceHolder.lockCanvas();

                mGameView.draw(canvas);

                mSurfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

}
