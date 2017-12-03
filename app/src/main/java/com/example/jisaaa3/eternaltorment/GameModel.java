package com.example.jisaaa3.eternaltorment;

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

    GameModel(int screensize_x, int screensize_y) {
        spriteList = new ArrayList<>();
        mPlayer = new Player(screensize_x, screensize_y);
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
