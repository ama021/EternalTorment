package com.example.jisaaa3.eternaltorment;

import android.content.Context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by jisaaa3 on 10/27/2017.
 */

public class GameModel {
    Random random;

    private Context mContext;

    private List<Sprite> spriteList;
    private Player mPlayer;

    private int mScreensize_x;
    private int mScreensize_y;

    private boolean addSkeleton;
    private boolean hasOhShitBeenUsed;

    GameModel(Context context, int screensize_x, int screensize_y, int selectedArmor) {
        this.mContext = context;
        this.mScreensize_x = screensize_x;
        this.mScreensize_y = screensize_y;
        spriteList = new ArrayList<>();
        mPlayer = new Player(context, screensize_x/2, screensize_y/2, selectedArmor);
        spriteList.add(mPlayer);
        String test = "testing";
    }


    //returns true if the player is dead
    public boolean update(Long fps) {
        random = new Random();
        int n = random.nextInt(50);
        n -= 3;
        if(n <= 0 && !mPlayer.isAttacking){
            spriteList.add(new Skeleton(this.mContext, this.mScreensize_x / 2, this.mScreensize_y / 2));
        }


        Iterator<Sprite> iter = spriteList.iterator();




        while (iter.hasNext()) {
            Sprite sprite = iter.next();

            if (sprite.update(fps, spriteList)) {

                //Collision and game is not over, therefore we need to remove the skeleton
                iter.remove();
            }

            if (sprite instanceof Player) {
                Player p = (Player) sprite;
                if (!p.isAlive()) {
                    return true;
                }
            }
        }



        return false;
    }

    public void playerDirectionChange(String event) {
        switch (event) {
            case "look_left":
                mPlayer.changeDirection("left");
                break;
            case "look_right":
                mPlayer.changeDirection("right");
                break;
            case "look_up":
                mPlayer.changeDirection("up");
                break;
            case "look_down":
                mPlayer.changeDirection("down");
                break;
            default:
                break;
        }
    }

    public void ohShitButton() {
        if (!hasOhShitBeenUsed) {
            Iterator<Sprite> iter = spriteList.iterator();

            while (iter.hasNext()) {
                if (iter.next() instanceof Skeleton) {
                    iter.remove();
                }
            }

            hasOhShitBeenUsed = true;
        }
    }

    public void playerAttack(float velocity_x, float velocity_y) {
        mPlayer.attack(velocity_x, velocity_y);
    }

    public List<Sprite> getSpriteList() {
        return this.spriteList;
    }
}

/*
    TODO: Need to complete collision checking and UI for player
    TODO: Implement oh shit button
    TODO: Implement animations and bitmap
    TODO: Finish player game logic
 */