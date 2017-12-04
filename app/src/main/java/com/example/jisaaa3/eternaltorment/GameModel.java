package com.example.jisaaa3.eternaltorment;

import android.content.Context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jisaaa3 on 10/27/2017.
 */

public class GameModel {

    private List<Sprite> spriteList;
    private Player mPlayer;


    private boolean hasOhShitBeenUsed;

    GameModel(Context context, int screensize_x, int screensize_y) {
        spriteList = new ArrayList<>();
        mPlayer = new Player(context, screensize_x/2, screensize_y/2);
        spriteList.add(mPlayer);
        spriteList.add(new Skeleton(context, screensize_x / 2, screensize_y / 2));
        String test = "testing";
    }


    //returns true if the player is dead
    public boolean update(Long fps) {
        Iterator<Sprite> iter = spriteList.iterator();



        while (iter.hasNext()) {
            Sprite sprite = iter.next();

            if (sprite.update(fps, spriteList)) {
                //Collision and game is not over, therefore we need to remove the skeleton
                iter.remove();
            } else if (sprite instanceof Player) {
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
                    Skeleton s = (Skeleton) iter.next();
                    s.die();
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
    TODO: Implement drawing of game objects
    TODO: Implement randomization of skeletons and skeleton game logic
    TODO: Finish player game logic
    TODO: Wrap up when game is over and ensure game goes back to main menu
    TODO: Implement some type of score, number of skeletons killed? Keep high score!

    TODO: Implement armory if have time, focus on game first
 */