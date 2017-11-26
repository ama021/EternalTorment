package com.example.jisaaa3.eternaltorment;

import android.content.Context;

/**
 * Created by ama02 on 11/25/2017.
 */

public class Player extends Sprite {

    Player(Context context, float location_x, float location_y) {
        setType("Player");

        /*
            TODO: set bitmap
         */

        setLocation(location_x, location_y);
    }

    public void update(long fps) {

    }
}
