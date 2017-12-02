package com.example.jisaaa3.eternaltorment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by jisaaa3 on 10/27/2017.
 */

public class GameView extends SurfaceView implements Runnable{
    volatile boolean mPlaying;
    Thread mGameThread = null;

    //Model Object
    GameModel gameModel;

    //Screen Boundaries
    private int mScreenSize_x;
    private int mScreenSize_y;

    //For Drawing
    private Paint mPaint;
    private Canvas mCanvas;
    private SurfaceHolder mSurfaceHolder;

    //For the HUD
    /*
        HUD attributes
     */

    //To help start a new game
    private Context mContext;

    private Long startFrameTime;
    private Long finishFrameTime;
    private Long fps;



    public GameView(Context context, int screenSize_x, int screenSize_y, GameModel gameModel) {
        super(context);
        mContext = context;

        mSurfaceHolder = getHolder();
        mPaint = new Paint();

        mScreenSize_x = screenSize_x;
        mScreenSize_y = screenSize_y;

        this.gameModel = gameModel;
        gameModel.spriteList.add(new Skeleton());
        gameModel.spriteList.get(0).setLocation(0, screenSize_y / 2);
    }

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

    public void draw() {
        if (mSurfaceHolder.getSurface().isValid()) {
            mCanvas = mSurfaceHolder.lockCanvas();

            mPaint.setColor(Color.argb(255, 255, 255, 255));
            Resources res = mContext.getResources();
            Bitmap bm = BitmapFactory.decodeResource(res, R.drawable.skeleton_shield_right);

            mCanvas.drawBitmap(bm, gameModel.spriteList.get(0).getLocation().x, gameModel.spriteList.get(0).getLocation().y, mPaint);


            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }


    /*
        Getting User Inputs
     */

    /*
        Set a listener on UI object DPad
        Then pass this result into the model
        DPad.setOnTouchListener
     */


    /*
        Set a listener on the swipe portion of the screen
        Then pass this result into the model

     */


    /*
        Handle cases when our thread is destroyed or interrupted
        We will need to pause the game and handle the thread
     */

    /*
        Controller will be implemented similar to the game example. We will have 2 rectangles in the
        bottom left and right hand corners which will appropiatley size with the controller input.
        Left rectangle will snag onsingletapup events and right will snag onFling events.
     */

    public void pause() {
        mPlaying = false;

        try {
            mGameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        mPlaying = true;
        mGameThread = new Thread(this);
        mGameThread.start();
    }


}
