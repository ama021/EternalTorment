package com.example.jisaaa3.eternaltorment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by Adrian on 10/24/2017.
 */

public class CreditsFragment extends Fragment{
    private TextView mCreditsContainer;

    private int mFragmentContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.credits_fragment, container, false);

        mFragmentContainer = getArguments().getInt(getString(R.string.fragmentContainer));

        mCreditsContainer = (TextView) v.findViewById(R.id.credits_container);
        mCreditsContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();

                /*
                    Ask about increasing the code cache capacity each time fragments
                    switch
                 */
            }
        });

        return v;
    }
}
