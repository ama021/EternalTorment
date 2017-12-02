package com.example.jisaaa3.eternaltorment;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by jisaaa3 on 10/27/2017.
 */

public class GameModel {

    ArrayList<Sprite> spriteList;

    GameModel() {
        spriteList = new ArrayList<>();
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
}
