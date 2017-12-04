package com.example.jisaaa3.eternaltorment;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by jisaaa3 on 10/19/2017.
 */

public class MainMenuFragment extends Fragment {
    private Button mStartButton;
    private Button mArmoryButton;
    private Button mCreditsButton;

    private int mFragmentContainer;

    private int selectedArmor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.selectedArmor = 0;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_menu_fragment, container, false);

        mFragmentContainer = getArguments().getInt(getString(R.string.fragmentContainer));

        mStartButton = (Button) v.findViewById(R.id.start_btn);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start game
                Intent intent = new Intent(getActivity(), GameActivity.class);
                intent.putExtra("selectedArmor", selectedArmor);
                startActivity(intent);
            }
        });

        Typeface myCustomFont = Typeface.createFromAsset(getActivity().getBaseContext().getAssets(),"fonts/Blade 2.ttf");
        mStartButton.setTypeface(myCustomFont);

        mArmoryButton = (Button) v.findViewById(R.id.armory_btn);
        mArmoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to Armory
                Intent intent = new Intent(getActivity(), ArmoryActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        Typeface myCustomFont2 = Typeface.createFromAsset(getActivity().getBaseContext().getAssets(),"fonts/Blade 2.ttf");
        mArmoryButton.setTypeface(myCustomFont2);

        mCreditsButton = (Button) v.findViewById(R.id.credits_btn);
        mCreditsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Switching fragments to display credits
                Fragment newFragment = new CreditsFragment();
                Bundle bundle = new Bundle();
                bundle.putInt(getString(R.string.fragmentContainer), mFragmentContainer);
                newFragment.setArguments(bundle);

                ((MainActivity)getActivity()).switchFragment(mFragmentContainer, newFragment);
            }
        });

        Typeface myCustomFont3 = Typeface.createFromAsset(getActivity().getBaseContext().getAssets(),"fonts/Blade 2.ttf");
        mCreditsButton.setTypeface(myCustomFont3);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int requestCode2, Intent data) {
        if (requestCode == 1) {
            if (requestCode2 == Activity.RESULT_OK) {
                this.selectedArmor = data.getExtras().getInt("selectedArmor");
            }
        }
    }
}
