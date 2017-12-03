package com.example.jisaaa3.eternaltorment;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

public class GameActivity extends Activity implements
        View.OnClickListener {

    private GestureDetector gestureDetector;

    GameModel mGameModel;
    private GameController mGameController;

    private Button mDpad_left;
    private Button mDpad_right;
    private Button mDpad_up;
    private Button mDpad_down;

    private Button mSwipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);
        mGameModel = new GameModel(this, size.x, size.y);
        mGameController = new GameController(mGameModel);

        setContentView(R.layout.activity_game);

        mDpad_left = (Button) findViewById(R.id.dpad_left);
        mDpad_left.setOnClickListener(this);

        mDpad_right = (Button) findViewById(R.id.dpad_right);
        mDpad_right.setOnClickListener(this);

        mDpad_up = (Button) findViewById(R.id.dpad_up);
        mDpad_up.setOnClickListener(this);

        mDpad_down = (Button) findViewById(R.id.dpad_down);
        mDpad_down.setOnClickListener(this);

        this.gestureDetector = new GestureDetector(this, new MyGestureListener());

        mSwipe = (Button) findViewById(R.id.swipe_input);
        mSwipe.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);

                return false;
            }
        });
    }

    /*
        To pause the game when the activity is no longer in view
     */
    @Override
    protected void onPause() {
        super.onPause();
        //mGameView.pause();
    }

    /*
        To resume the game when the activity is back running
     */
    @Override
    protected void onResume() {
        super.onResume();
        //mGameView.resume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dpad_left:
                Log.d("UserInput", "Pressed Left");
                this.mGameController.pressedLeft();
                break;
            case R.id.dpad_right:
                Log.d("UserInput", "Pressed Right");
                this.mGameController.pressedRight();
                break;
            case R.id.dpad_up:
                Log.d("UserInput", "Pressed Up");
                this.mGameController.pressedUp();
                break;
            case R.id.dpad_down:
                Log.d("UserInput", "Pressed Down");
                this.mGameController.pressedDown();
                break;
            default:
                break;
        }
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String TAG = "Gestures";

        @Override
        public boolean onDown(MotionEvent event) {
            Log.d(TAG, "onDown: " + event.toString());
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocity_x, float velocity_y) {
            Log.d(TAG, "on Fling: " + e1.toString() + e2.toString());
            Log.d(TAG, "Velocity x: " + velocity_x + " Velocity y: " + velocity_y);
            mGameController.playerSwipe(velocity_x, velocity_y);
            return true;
        }
    }

}


