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
        setType("Skeleton");
        this.mContext = context;
        random = new Random();
        random_direction();

        this.setAlive(true);

        this.screensize_x = screensize_x;
        this.screensize_y = scrrensize_y;

        Resources r = context.getResources();
        setCurrentBitmap(BitmapFactory.decodeResource(r, R.drawable.skeleton_down_shield_right));
        setLocation(this.screensize_x - this.currentBitmap.getWidth() / 2, 0);
        setSpriteDirection("down");

        this.skeletonBoundary = new Rect();
        setSkeletonBoundary();

        if(skeletonCoridore == 0){
            this.getLocation().y += 5;
        }
        else if(skeletonCoridore == 1){
            this.getLocation().y -= 5;
        }
        else if(skeletonCoridore == 2){
            this.getLocation().x += 5;
        }
        else{
            this.getLocation().x -= 5;
        }

    }

    public boolean update(long fps, List<Sprite> spriteList) {
        collision_detection(this, spriteList.get(0));

        setSkeletonBoundary();

        this.setLocation(this.getLocation().x, this.getLocation().y + 5);

        return false;
    }

    public void die() {
        //play dying animation
    }

    public void setCurrentBitmap(Bitmap currentBitmap) {
        this.currentBitmap = currentBitmap;
    }

    public Bitmap getCurrentBitmap() {
        return currentBitmap;
    }

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

    public void random_direction(){
        int n = random.nextInt(4);
        int m = random.nextInt(2);

        Resources r = mContext.getResources();
        // Skeleton Top
        if(n == 0){
            // Shield side
            if(m == 0){
                shieldSide = 0;
                setCurrentBitmap(BitmapFactory.decodeResource(r, R.drawable.skeleton_up_shield_left));
            }
            else if (m == 1){
                shieldSide = 1;
                setCurrentBitmap(BitmapFactory.decodeResource(r, R.drawable.skeleton_up_shield_right));
            }

            skeletonCoridore = 0;
            this.setLocation((float) screensize_x - currentBitmap.getWidth() / 2, (float) 0 - currentBitmap.getHeight());
        }

        // Skeleton Bottom
        else if(n == 1){
            // Shield side
            if(m == 0){
                shieldSide = 0;
                setCurrentBitmap(BitmapFactory.decodeResource(r, R.drawable.skeleton_down_shield_left));
            }
            else if(m == 1){
                shieldSide = 1;
                setCurrentBitmap(BitmapFactory.decodeResource(r, R.drawable.skeleton_up_shield_right));
            }

            skeletonCoridore = 1;
            this.setLocation((float) screensize_x - currentBitmap.getWidth() / 2, (float) screensize_y * 2);
        }
        // Skeleton Left
        else if(n == 2){
            // Shield side
            if(m == 0){
                shieldSide = 0;
                setCurrentBitmap(BitmapFactory.decodeResource(r, R.drawable.skeleton_left_shield_left));
            }
            else if(m == 1){
                shieldSide = 1;
                setCurrentBitmap(BitmapFactory.decodeResource(r, R.drawable.skeleton_left_shield_right));
            }

            skeletonCoridore = 2;
            this.setLocation((float) 0 - currentBitmap.getWidth(), (float) screensize_y - currentBitmap.getHeight() / 2);
        }

        // Skeleton Right
        else{
            // Shield side
            if(m == 0){
                shieldSide = 0;
                setCurrentBitmap(BitmapFactory.decodeResource(r, R.drawable.skeleton_right_shield_left));
            }
            else if(m == 1){
                shieldSide = 1;
                setCurrentBitmap(BitmapFactory.decodeResource(r, R.drawable.skeleton_right_shield_right));
            }

            skeletonCoridore = 3;
            this.setLocation((float) screensize_x * 2, (float) screensize_y - currentBitmap.getHeight() / 2);
        }
    }
}