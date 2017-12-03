package com.example.jisaaa3.eternaltorment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ama02 on 11/25/2017.
 */

public class Player extends Sprite {
    private static final String TAG = "Player";

    private Context mContext;

    private int health;
    private int animationFrame;

    /*
        This is used to delay the animation. Once the count reaches 0 it will go to the next animation frame
     */
    private int animationFrameTime;

    private Bitmap currentBitmap;

    private boolean isHit = false;
    private boolean isAnimating = false;

    Player(Context context, int location_x, int location_y) {
        setType("Player");
        this.mContext = context;

        /*
            TODO: set bitmap initially to down
         */

        Resources r = mContext.getResources();
        setCurrentBitmap(BitmapFactory.decodeResource(r, R.drawable.down_direction_knight));
        this.health = 3;
        setLocation(location_x, location_y);
        setSpriteDirection("down");
    }

    public boolean update(long fps, List<Sprite> spriteList) {

        if (isHit) {
            health--;

            if (health == 0) {
                setAlive(false);
                die();
                return true;
            }
        }

        if (isAnimating) {
            doAnimation();
        }



        return false;
    }

    public void changeDirection(String direction) {
        setSpriteDirection(direction);
        setBitmapDirection(direction);
        Log.d(TAG, "Player direction: " + direction);
    }

    public void attack(float velocity_x, float velocity_y) {
        //Means we are swiping RtL or LtR
        if (Math.abs(velocity_x) > Math.abs(velocity_y)) {
            //Make sure player is facing the correct way
            //if (getSpriteDirection().equals("up") || getSpriteDirection().equals("down")) {
                //Means we are swiping LtR
                if (velocity_x > 0) {
                    //Set animation for LtR and do collision detection
                    //setCurrentBitmap()
                    check_collision();
                    Log.d(TAG, "Swipe Left to right");
                } else {
                    //Set animation for RtL and do collision detection
                    Log.d(TAG, "Swipe Right to Left");
                }
           // }
        } else {
            //We are swiping UtD or DtU
            if (velocity_y > 0) {
                //Set animation for UtD and do collision detection
                Log.d(TAG, "Swipe Up to Down");
            } else {
                //Set animation for DtU and do collision detection
                Log.d(TAG, "Swipe Down to Up");
            }
        }
    }

    public Bitmap bitmapToDraw() {
        return this.currentBitmap;
    }

    private void doAnimation() {
        //this.setcurrentBitmap = getbm.animationFrame
        //animationFrame++ % 6

        if (animationFrame == 1) {
            isAnimating = false;
        }
    }

    private void setBitmapDirection(String direction) {
        switch (direction) {
            case "left":
                setCurrentBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.left_direction_knight));
                break;
            case "right":
                setCurrentBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.right_direction_knight));
                break;
            case "up":
                setCurrentBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.up_direction_knight));
                break;
            case "down":
                setCurrentBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.down_direction_knight));
                break;
            default:
                break;
        }
    }

    private void setCurrentBitmap(Bitmap bm) {
        this.currentBitmap = bm;

    }

    private void check_collision() {
        if (isAlive()) {
            //Figure how to get the sprites and player together and iterate over the skeletons
            //and do collistion detection.
        }
    }

    private void die() {
        //setCurrentBitmap(bm.dead);
    }
}
