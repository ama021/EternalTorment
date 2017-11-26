package com.example.jisaaa3.eternaltorment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by ama02 on 11/25/2017.
 */

public abstract class Sprite {
    private Vector2Point location;

    private float width;
    private float height;
    private int animFrameCount = 1;

    private char type;

    private String bitmapName;

    public abstract void update(long fps);

    /*
        Setter and getters for the bitmap

     */

    public String getBitmapName() {
        return this.bitmapName;
    }

    public void setBitmapName(String bitmapName) {
        this.bitmapName = bitmapName;
    }

    /*
        Properly scaling the bitmap
     */
    public Bitmap prepareBitmap(Context context, String bitmapName, int pixelsPerMeter) {
        //Gets the resource id from context (gameActivity) associated with the bitmap name
        int resourceID = context.getResources().getIdentifier(bitmapName, "drawable", context.getPackageName());
        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), resourceID);

        //Scaling the bitmap based on the number of pixels per meter
        bm = Bitmap.createScaledBitmap(
                bm,
                (int)(width * animFrameCount * pixelsPerMeter),
                (int)(height * pixelsPerMeter),
                false);

        return bm;
    }

    /*
        Setter and getter for sprite location
     */

    public Vector2Point getLocation() {
        return this.location;
    }

    public void setLocation(float x, float y) {
        this.location = new Vector2Point();
        this.location.x = x;
        this.location.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }
}
