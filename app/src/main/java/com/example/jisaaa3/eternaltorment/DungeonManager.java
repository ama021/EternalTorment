package com.example.jisaaa3.eternaltorment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by ama02 on 11/25/2017.
 */

public class DungeonManager {
    private String dungeon;

    Player player;
    int playerIndex;

    private boolean isPlaying;

    DungeonData dungeonData;
    ArrayList<Sprite> sprites;

    ArrayList<Rect> userInput;
    ArrayList<Bitmap> bitmapArray;

    public DungeonManager(Context context,
                          int pixelsPerMeter,
                          int screenSize_x,
                          String dungeon,
                          float px,
                          float py) {

        this.dungeon = dungeon.substring(7);

        switch (dungeon) {
            case "1":
                dungeonData = new Dungeon1();
                break;

            default:
                dungeonData = new Dungeon1();
        }

        sprites = new ArrayList<>();
        bitmapArray = new ArrayList<>();

        /*
            TODO: Do we need px or py?
         */
        loadDungeonData(context, pixelsPerMeter, px, py);

        isPlaying = true;

    }

    public boolean isPlaying() {
        return this.isPlaying;
    }

    public Bitmap getBitmap(String type) {

        int index;

        switch (type) {
            case "player":
                index = 0;
                break;

            case "skeleton":
                index = 1;
                break;

            default:
                index = 1;
                break;
        }

        return this.bitmapArray.get(index);
    }

    private void loadMapData(Context context, float px, float py) {
        sprites.add(new Player(context, px, py));


    }


}
