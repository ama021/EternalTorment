package com.example.jisaaa3.eternaltorment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by jisaaa3 on 10/19/2017.
 */

public class MainMenuFragment extends Fragment {
    private Button mStartButton;
    private Button mArmoryButton;
    private Button mCreditsButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_menu_fragment, container, false);

        mStartButton = (Button) v.findViewById(R.id.start_btn);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start game
                Toast.makeText(getActivity(), "Game started", Toast.LENGTH_LONG);
            }
        });

        mArmoryButton = (Button) v.findViewById(R.id.armory_btn);
        mArmoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to Armory
                Toast.makeText(getActivity(), "Going to armory", Toast.LENGTH_LONG);
            }
        });

        mCreditsButton = (Button) v.findViewById(R.id.credits_btn);
        mCreditsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Switching fragments to display credits
                ((MainActivity)getActivity()).switchFragment();
                Toast.makeText(getActivity(), "Switching fragments", Toast.LENGTH_LONG);
            }
        });



        return v;
    }
}
