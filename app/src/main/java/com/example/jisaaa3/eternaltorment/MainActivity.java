package com.example.jisaaa3.eternaltorment;

import android.app.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.main_screen_fragment_container);

        Bundle bundle = new Bundle();
        bundle.putInt(getString(R.string.fragmentContainer), R.id.main_screen_fragment_container);

        if (fragment == null) {
            fragment = new MainMenuFragment();
            fragment.setArguments(bundle);
            fm.beginTransaction()
                    .add(R.id.main_screen_fragment_container, fragment)
                    .commit();
        }


    }

    public void switchFragment(int fragmentContainer, Fragment newFragment) {
        //Pass in origin fragment container and destinatation fragment
        //Switch fragments
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
                .replace(fragmentContainer, newFragment)
                .addToBackStack(null)
                .commit();
    }



}
