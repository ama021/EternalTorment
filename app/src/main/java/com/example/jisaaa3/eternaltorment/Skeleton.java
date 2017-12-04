package com.example.jisaaa3.eternaltorment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by ama02 on 11/25/2017.
 */

public class Skeleton extends Sprite {

    private static final String TAG = "Skeleton";

    private Context mContext;

    private Rect skeletonBoundary;

    private Bitmap currentBitmap;

    private int screensize_x;
    private int screensize_y;

    // Randomized variables
    Random random;
    private int skeletonCoridore = 0;
    private int shieldSide = 0;

    public Skeleton(Context context, int screensize_x, int scrrensize_y) {
        this.mContext = context;
        this.screensize_x = screensize_x;
        this.screensize_y = scrrensize_y;
        random = new Random();
        random_direction();

        this.setAlive(true);

        this.skeletonBoundary = new Rect();
        setSkeletonBoundary();
    }

    public boolean update(long fps, List<Sprite> spriteList) {
        collision_detection(this, spriteList.get(0));

        if (!isAlive()) {
            return true;
        }

        moveSkeleton();

        setSkeletonBoundary();

        return false;
    }

    // Add movement to the skeleton
    private void moveSkeleton() {
        switch (this.getSpriteDirection()) {
            case "left":
                this.setLocation(this.getLocation().x - 10, this.getLocation().y);
                break;
            case "right":
                this.setLocation(this.getLocation().x + 10, this.getLocation().y);
                break;
            case "up":
                this.setLocation(this.getLocation().x, this.getLocation().y - 5);
                break;
            case "down":
                this.setLocation(this.getLocation().x, this.getLocation().y + 5);
                break;
            default:
                break;
        }
    }

    // Kill the skeleton
    public void die() {
        setAlive(false);
    }

    // Sets the requested bitmap
    public void setCurrentBitmap(Bitmap currentBitmap) {
        this.currentBitmap = currentBitmap;
    }

    // Gets the current bitmap
    public Bitmap getCurrentBitmap() {
        return currentBitmap;
    }

    // Checking the skeletons bounds for collision
    private void setSkeletonBoundary() {
        skeletonBoundary.set(
                (int) getLocation().x,
                (int) getLocation().y,
                (int) getLocation().x + currentBitmap.getWidth(),
                (int) getLocation().y + currentBitmap.getHeight()
        );

        this.setSpriteHitBox(this.skeletonBoundary);
        Log.d(TAG, "Skeleton Boundary x.left " + skeletonBoundary.left + " top: " + skeletonBoundary.top + " bottom: " + skeletonBoundary.bottom + " right: " + skeletonBoundary.right);
    }

    // Uses the random java class to choose the coridore and shield side of the skeleton
    public void random_direction(){
        int n = random.nextInt(16) / 4;
        int m = random.nextInt(2);

        Resources r = mContext.getResources();
        // Skeleton Top
        if(n == 0){
            // Shield side
            if(m == 0){
                shieldSide = 0;
                setCurrentBitmap(BitmapFactory.decodeResource(r, R.drawable.skeleton_down_shield_left));
            }
            else if (m == 1){
                shieldSide = 1;
                setCurrentBitmap(BitmapFactory.decodeResource(r, R.drawable.skeleton_down_shield_right));
            }

            skeletonCoridore = 0;
            this.setSpriteDirection("down");
            this.setLocation(screensize_x - currentBitmap.getWidth() / 2, 0 - currentBitmap.getHeight());
        }

        // Skeleton Bottom
        else if(n == 1){
            // Shield side
            if(m == 0){
                shieldSide = 0;
                setCurrentBitmap(BitmapFactory.decodeResource(r, R.drawable.skeleton_up_shield_left));
            }
            else if(m == 1){
                shieldSide = 1;
                setCurrentBitmap(BitmapFactory.decodeResource(r, R.drawable.skeleton_up_shield_right));
            }

            skeletonCoridore = 1;
            this.setSpriteDirection("up");
            this.setLocation(screensize_x - currentBitmap.getWidth() / 2, screensize_y * 2);
        }
        // Skeleton Left
        else if(n == 2){
            // Shield side
            if(m == 0){
                shieldSide = 0;
                setCurrentBitmap(BitmapFactory.decodeResource(r, R.drawable.skeleton_right_shield_left));
            }
            else if(m == 1){
                shieldSide = 1;
                setCurrentBitmap(BitmapFactory.decodeResource(r, R.drawable.skeleton_right_shield_right));
            }

            skeletonCoridore = 2;
            this.setSpriteDirection("right");
            this.setLocation(0 - currentBitmap.getWidth(), screensize_y - currentBitmap.getHeight() / 2);
        }

        // Skeleton Right
        else{
            // Shield side
            if(m == 0){
                shieldSide = 0;
                setCurrentBitmap(BitmapFactory.decodeResource(r, R.drawable.skeleton_left_shield_left));
            }
            else if(m == 1){
                shieldSide = 1;
                setCurrentBitmap(BitmapFactory.decodeResource(r, R.drawable.skeleton_left_shield_right));
            }

            skeletonCoridore = 3;
            this.setSpriteDirection("left");
            this.setLocation( screensize_x * 2, screensize_y - currentBitmap.getHeight() / 2);
        }
    }
}