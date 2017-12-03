package com.example.jisaaa3.eternaltorment;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
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
        mContext = this.getContext();
        Activity a = scanForActivity(mContext);
        GameActivity gameActivty = (GameActivity) a;
        mGameModel = gameActivty.mGameModel;
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        mPaint = new Paint();
    }

    private static Activity scanForActivity(Context context) {
        if (context == null) {
            return null;
        } else if (context instanceof Activity) {
            return (Activity) context;
        } else if (context instanceof ContextWrapper) {
            return scanForActivity(((ContextWrapper)context).getBaseContext());
        }

        return null;
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

            Resources res = mContext.getResources();
            Bitmap background = BitmapFactory.decodeResource(res, R.drawable.dungeon_background);
            mPaint.setColor(Color.argb(255, 65, 25, 255));
            canvas.drawARGB(255, 255, 65, 22);
            canvas.drawBitmap(background, -84, 0, mPaint);


            List<Sprite> gameObjects = mGameModel.getSpriteList();

            //Iterate over the objects and draw them.

            Player p = (Player) gameObjects.get(0);
            Bitmap bm = p.bitmapToDraw();
            canvas.drawBitmap(bm, p.getLocation().x, p.getLocation().y, mPaint);

            canvas.drawBitmap(BitmapFactory.decodeResource(res, R.drawable.skeleton_down_shield_right), 0, 0, mPaint);
        }
    }
}
