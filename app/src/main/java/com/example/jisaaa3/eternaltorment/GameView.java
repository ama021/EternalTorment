package com.example.jisaaa3.eternaltorment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.AttrRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.List;

/**
 * Created by jisaaa3 on 10/27/2017.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback{
    private GameThread mGameThread = null;

    //Model Object
    GameModel mGameModel;

    //For Drawing
    private Paint mPaint;
    private SurfaceHolder mSurfaceHolder;

    //For the HUD
    /*
        HUD attributes
     */

    //To help start a new game
    private Context mContext;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        GameActivity a = (GameActivity) context;
        this.mGameModel = a.mGameModel;
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        mPaint = new Paint();
    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        if (mGameThread == null) {
            mGameThread = new GameThread(this, this.mGameModel);
            mGameThread.setRunning(true);
            mGameThread.start();
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        mGameThread.setRunning(false);

        try {
            mGameThread.join();
        } catch (InterruptedException e) {
            Log.e("GameView", "Interrupted Exception: ", e);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if (canvas != null) {
            super.draw(canvas);


            List<Sprite> gameObjects = mGameModel.getSpriteList();

            //Iterate over the objects and draw them.

            mPaint.setColor(Color.argb(255, 65, 25, 255));


            Resources res = mContext.getResources();
            //Bitmap bm = BitmapFactory.decodeResource(res, R.drawable.dungeon_background);
            //canvas.drawBitmap(bm, 0, 0, mPaint);
        }


    }


/*
    @Override
    public void run() {
        //Main game loop
        while(mPlaying) {
            startFrameTime = System.currentTimeMillis();

            gameModel.spriteList.get(0).setLocation(gameModel.spriteList.get(0).getLocation().x + 1, gameModel.spriteList.get(0).getLocation().y);

            //model.update(fps);
            this.draw();


            finishFrameTime = System.currentTimeMillis() - startFrameTime;
            if (finishFrameTime >= 1) {
                fps = 1000 / finishFrameTime;
            }

        }
    }
    */

}
