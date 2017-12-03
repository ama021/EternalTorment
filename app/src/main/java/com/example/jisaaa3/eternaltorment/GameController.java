package com.example.jisaaa3.eternaltorment;

import android.view.MotionEvent;

/**
 * Created by ama02 on 12/3/2017.
 */

public class GameController {
    private GameModel mGameModel;

    public GameController(GameModel gameModel) {
        this.mGameModel = gameModel;
    }

    public void pressedLeft() {
        mGameModel.playerDirectionChange("look_left");
    }

    public void pressedRight() {
        mGameModel.playerDirectionChange("look_right");
    }

    public void pressedUp() {
        mGameModel.playerDirectionChange("look_up");
    }

    public void pressedDown() {
        mGameModel.playerDirectionChange("look_down");
    }

    public void pressedOhShitButton() {
        mGameModel.ohShitButton();
    }

    public void playerSwipe(float velocity_x, float velocity_y) {
        mGameModel.playerAttack(velocity_x, velocity_y);
    }
}
