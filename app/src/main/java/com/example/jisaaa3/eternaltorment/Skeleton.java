package com.example.jisaaa3.eternaltorment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by ama02 on 11/25/2017.
 */

public class Skeleton extends Sprite {

    private static final String TAG = "Skeleton";

    private String weak_side;

    private Rect skeletonBoundary;

    private Bitmap currentBitmap;

    private int screensize_x;
    private int screensize_y;

    public Skeleton(Context context, int screensize_x, int scrrensize_y) {
        setType("Skeleton");

        this.setAlive(true);

        this.screensize_x = screensize_x;
        this.screensize_y = scrrensize_y;

        Resources r = context.getResources();
        setCurrentBitmap(BitmapFactory.decodeResource(r, R.drawable.skeleton_down_shield_right));
        setLocation(this.screensize_x - this.currentBitmap.getWidth() / 2, 0);
        setSpriteDirection("down");

        this.skeletonBoundary = new Rect();
        setSkeletonBoundary();
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
}
