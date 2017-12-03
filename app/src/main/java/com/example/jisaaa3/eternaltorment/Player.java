package com.example.jisaaa3.eternaltorment;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by ama02 on 11/25/2017.
 */

public class Player extends Sprite {
    private static final String TAG = "Player";

    private int health;

    Player(int location_x, int location_y) {
        setType("Player");

        /*
            TODO: set bitmap initially to down
         */

        setLocation(location_x, location_y);
        setSpriteDirection("down");
    }

    public boolean update(long fps, ArrayList<Sprite> spriteList) {
        return true;
    }

    public void changeDirection(String direction) {
        setSpriteDirection(direction);
    }

    public void attack(float velocity_x, float velocity_y) {
        //Means we are swiping RtL or LtR
        if (Math.abs(velocity_x) > Math.abs(velocity_y)) {
            //Make sure player is facing the correct way
            //if (getSpriteDirection().equals("up") || getSpriteDirection().equals("down")) {
                //Means we are swiping LtR
                if (velocity_x > 0) {
                    //Set animation for LtR and do collision detection
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

    private void check_collision() {
        if (isAlive) {

        }
    }
}
