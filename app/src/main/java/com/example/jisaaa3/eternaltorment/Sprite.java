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

    // Sets the collision hitbox for player
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

    // Sets the location for any of the sprites x and y values
    public void setLocation(float x, float y) {
        this.location = new Vector2Point();
        this.location.x = x;
        this.location.y = y;
    }

    // Gets the direction all sprites are facing
    public String getSpriteDirection() {
        return spriteDirection;
    }

    // Sets the direction all sprites are facing
    public void setSpriteDirection(String spriteDirection) {
        this.spriteDirection = spriteDirection;
    }

    public String getType() {
        return type;
    }

    // Sets type of Sprite
    public void setType(String type) {
        this.type = type;
    }

    // Returns that the sprite isAlive
    public boolean isAlive() {
        return isAlive;
    }

    // Sets the sprite to alive
    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    // Collision method for any sprite collision
    public boolean collision_detection(Sprite a, Sprite b) {

        if (a instanceof Player) {
            Player p = (Player) a;
            if (p.getAttackBoundary().setIntersect(p.getAttackBoundary(), b.spriteHitBox)) {
                if (p.getSpriteDirection() == "up" && b.getSpriteDirection() == "down") {
                    if (!p.isSwipeUtoD) {
                        ((Skeleton) b).die();
                    }
                } else if (p.getSpriteDirection() == "down" && b.getSpriteDirection() == "up") {
                    if (!p.isSwipeUtoD) {
                        ((Skeleton) b).die();
                    }
                } else if (p.getSpriteDirection() == "left" && b.getSpriteDirection() == "right") {
                    if (!p.isSwipeLtoR) {
                        ((Skeleton) b).die();
                    }
                } else if (p.getSpriteDirection() == "right" && b.getSpriteDirection() == "left") {
                    if (!p.isSwipeLtoR) {
                        ((Skeleton) b).die();
                    }
                }
            }
        } else if (a instanceof Skeleton) {
            if (a.spriteHitBox.setIntersect(a.spriteHitBox, b.spriteHitBox)) {
                ((Skeleton) a).die();
                ((Player) b).decreaseHealth();
                Log.d("Collision", "Intersection is true");
            } else {
                Log.d("Collision", "Intersection is false");
            }
        }

        return false;
    }
}
