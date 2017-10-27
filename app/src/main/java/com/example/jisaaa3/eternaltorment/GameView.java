package com.example.jisaaa3.eternaltorment;

import android.content.Context;
import android.graphics.Canvas;
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
    GameModel model;

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



    public GameView(Context context, int screenSize_x, int screenSize_y) {
        super(context);
        mContext = context;

        mSurfaceHolder = getHolder();
        mPaint = new Paint();

        mScreenSize_x = screenSize_x;
        mScreenSize_y = screenSize_y;
    }

    @Override
    public void run() {
        //Main game loop
        while(mPlaying) {
            model.update();
            this.draw();
            this.control();
        }
    }

    private void draw() {

    }

    private void control() {

    }

    /*
        Getting User Inputs
     */

    /*
        Set a listener on UI object DPad
        Then pass this result into the model
     */


    /*
        Set a listener on the swipe portion of the screen
        Then pass this result into the model
     */


    /*
        Handle cases when our thread is destroyed or interrupted
        We will need to pause the game and handle the thread
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
