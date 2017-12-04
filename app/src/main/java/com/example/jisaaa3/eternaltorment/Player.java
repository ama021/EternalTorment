package com.example.jisaaa3.eternaltorment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
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
    private String armorSet;

    //Animation frame 1-5
    private int animationFrame;
    //Animation stall before displaying next frame
    private int animationFrameTime = 25;
    private int screensize_x;
    private int screensize_y;

    private Rect playerBoundary;

    private boolean isSwipeLtoR;
    private boolean isSwipeUtoD;

    /*
        This is used to delay the animation. Once the count reaches 0 it will go to the next animation frame
     */

    private boolean isHit = false;
    private boolean isAnimating = false;

    Player(Context context, int screensize_x, int screensize_y, int selectedArmor) {
        setType("Player");
        this.mContext = context;
        this.setAlive(true);
        this.screensize_x = screensize_x;
        this.screensize_y = screensize_y;

        setArmorSet(selectedArmor);

        setLocation(this.screensize_x - this.currentBitmap.getWidth() / 2, this.screensize_y - this.currentBitmap.getHeight() / 2);
        setSpriteDirection("down");

        this.playerBoundary = new Rect();
        setPlayerBoundary();
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
            check_collision(spriteList);
        } else {
            setPlayerBoundary();
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
            if (getSpriteDirection().equals("up") || getSpriteDirection().equals("down")) {
                //Means we are swiping LtR
                if (velocity_x > 0) {
                    //Set animation for LtR and do collision detection
                    //setCurrentBitmap()
                    this.isAnimating = true;
                    this.isSwipeLtoR = true;
                    Log.d(TAG, "Swipe Left to right");
                } else {
                    this.isAnimating = true;
                    this.isSwipeLtoR = false;
                    //Set animation for RtL and do collision detection
                    Log.d(TAG, "Swipe Right to Left");
                }
           }
        } else {
            if (getSpriteDirection().equals("left") || getSpriteDirection().equals("right")) {
                //We are swiping UtD or DtU
                if (velocity_y > 0) {
                    this.isAnimating = true;
                    this.isSwipeUtoD = true;
                    //Set animation for UtD and do collision detection
                    Log.d(TAG, "Swipe Up to Down");
                } else {
                    this.isAnimating = true;
                    this.isSwipeUtoD = false;
                    //Set an
                    // imation for DtU and do collision detection
                    Log.d(TAG, "Swipe Down to Up");
                }
            }
        }
    }

    private void doAnimation() {

        if (this.animationFrameTime != 0) {
            animationFrameTime =- 1;
        } else {
            setAnimation();
        }
        //this.setcurrentBitmap = getbm.animationFrame
        //animationFrame++ % 6


        if (animationFrame == 1) {
            isAnimating = false;
        }
    }

    private void setAnimation() {

    }

    private void setBitmapDirection(String direction) {
        switch (direction) {
            case "left":
                setLeftBitmapWithArmor();
                break;
            case "right":
                setRightBitmapWithArmor();
                break;
            case "up":
                setUpBitmapWithArmor();
                break;
            case "down":
                setDownBitmapWithArmor();
                break;
            default:
                break;
        }
    }

    private void setCurrentBitmap(Bitmap bm) {
        this.currentBitmap = bm;
        this.setLocation(screensize_x - this.currentBitmap.getWidth() / 2, screensize_y - this.currentBitmap.getHeight() / 2);

    }

    public Bitmap bitmapToDraw() {
        return this.currentBitmap;
    }

    private void setArmorSet(int armorSet) {
        Resources r = mContext.getResources();

        switch (armorSet) {
            case 0:
                this.armorSet = "knight";
                this.health = 1;
                setCurrentBitmap(BitmapFactory.decodeResource(r, R.drawable.down_direction_knight));
                break;
            case 1:
                this.armorSet = "glass";
                this.health = 2;
                setCurrentBitmap(BitmapFactory.decodeResource(r, R.drawable.down_knight_glass));
                break;
            case 2:
                this.armorSet = "blizzard";
                this.health = 3;
                setCurrentBitmap(BitmapFactory.decodeResource(r, R.drawable.down_knight_arctic));
                break;
            default:
                this.armorSet = "knight";
                this.health = 1;
                setCurrentBitmap(BitmapFactory.decodeResource(r, R.drawable.down_direction_knight));
                break;
        }
    }

    private void setLeftBitmapWithArmor() {
        switch (this.armorSet) {
            case "knight":
                setCurrentBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.left_direction_knight));
                break;
            case "glass":
                setCurrentBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.left_direction_knight_glass));
                break;
            case "blizzard":
                setCurrentBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.left_direction_knight_arctic));
                break;
            default:
                setCurrentBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.left_direction_knight));
                break;
        }
    }

    private void setRightBitmapWithArmor() {
        switch (this.armorSet) {
            case "knight":
                setCurrentBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.right_direction_knight));
                break;
            case "glass":
                setCurrentBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.right_direction_knight_glass));
                break;
            case "blizzard":
                setCurrentBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.right_direction_knight_arctic));
                break;
            default:
                setCurrentBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.right_direction_knight));
                break;
        }
    }

    private void setUpBitmapWithArmor() {
        switch (this.armorSet) {
            case "knight":
                setCurrentBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.up_direction_knight));
                break;
            case "glass":
                setCurrentBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.up_direction_knight_glass));
                break;
            case "blizzard":
                setCurrentBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.up_direction_knight_arctic));
                break;
            default:
                setCurrentBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.up_direction_knight));
                break;
        }
    }

    private void setDownBitmapWithArmor() {
        switch (this.armorSet) {
            case "knight":
                setCurrentBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.down_direction_knight));
                break;
            case "glass":
                setCurrentBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.down_knight_glass));
                break;
            case "blizzard":
                setCurrentBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.down_knight_arctic));
                break;
            default:
                setCurrentBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.down_direction_knight));
                break;
        }
    }

    private void check_collision(List<Sprite> spriteList) {
        if (isAlive()) {
            //Figure how to get the sprites and player together and iterate over the skeletons
            //and do collistion detection.
        }
    }

    private void setPlayerBoundary() {
        playerBoundary.set(
                (int) getLocation().x,
                (int) getLocation().y,
                (int) getLocation().x + currentBitmap.getWidth(),
                (int) getLocation().y + currentBitmap.getHeight()
        );

        this.setSpriteHitBox(playerBoundary);

        //Log.d(TAG, "Player Boundary x.left " + playerBoundary.left + " top: " + playerBoundary.top + " bottom: " + playerBoundary.bottom + " right: " + playerBoundary.right);
    }

    private void die() {
        //setCurrentBitmap(bm.dead);
    }
}
