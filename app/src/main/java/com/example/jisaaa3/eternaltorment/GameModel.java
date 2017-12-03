package com.example.jisaaa3.eternaltorment;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by jisaaa3 on 10/27/2017.
 */

public class GameModel {

    private ArrayList<Sprite> spriteList;
    private Player mPlayer;

    GameModel(int screensize_x, int screensize_y) {
        spriteList = new ArrayList<>();
        mPlayer = new Player(screensize_x, screensize_y);
    }


    public void update(Long fps) {
        Iterator<Sprite> iter = spriteList.iterator();

        while (iter.hasNext()) {
            Sprite sprite = iter.next();

            if (sprite.update(fps, spriteList)) {
                //Collision and game is not over, therefore we need to remove the skeleton
                iter.remove();
            }
        }
    }

    public void playerDirectionChange(String event) {
        switch (event) {
            case "look_left":
                mPlayer.changeDirection("left");
                break;
            case "look_right":
                mPlayer.changeDirection("right");
                break;
            case "look up":
                mPlayer.changeDirection("up");
                break;
            case "look down":
                mPlayer.changeDirection("down");
                break;
            default:
                break;
        }
    }

    public void playerAttack(float velocity_x, float velocity_y) {
        mPlayer.attack(velocity_x, velocity_y);
    }
}
