package com.example.jisaaa3.eternaltorment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ama02 on 11/25/2017.
 */

public abstract class Sprite {
    private Vector2Point location;

    private Rect spriteHitBox;

    Bitmap currentBitmap;

    private String type;
    private String bitmapName;
    private String spriteDirection;

    private boolean isAlive;

    public abstract boolean update(long fps, List<Sprite> spriteList);

    public String getBitmapName() {
        return this.bitmapName;
    }

    public void setBitmapName(String bitmapName) {
        this.bitmapName = bitmapName;
    }

    public Rect getSpriteHitBox() {
        return spriteHitBox;
    }

    public void setSpriteHitBox(Rect spriteHitBox) {
        this.spriteHitBox = spriteHitBox;
    }

    public Bitmap getCurrentBitmap() {
        return currentBitmap;
    }

    /*
        Setter and getter for sprite location
     */

    public Vector2Point getLocation() {
        return this.location;
    }

    public void setLocation(float x, float y) {
        this.location = new Vector2Point();
        this.location.x = x;
        this.location.y = y;
    }

    public String getSpriteDirection() {
        return spriteDirection;
    }

    public void setSpriteDirection(String spriteDirection) {
        this.spriteDirection = spriteDirection;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean collision_detection(Sprite a, Sprite b) {

        if (a instanceof Player) {
            //Check for player attack collision
        } else if (a instanceof Skeleton) {
            if (a.spriteHitBox.setIntersect(a.spriteHitBox, b.spriteHitBox)) {
                Log.d("Collision", "Intersection is true");
            } else {
                Log.d("Collision", "Intersection is false");
            }
        }
        //Need to detect collision here. However, due to the spear need to handle that somehow.

        /*
            member variables in player that define its collision?

            Player will check if the attack hit the skeleton

            Skeleton will check if they have hit the player within their corrected boundaries
         */

        return false;
    }
}
